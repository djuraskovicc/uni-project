package org.spotify.resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.spotify.dto.FileUploadInput;
import org.spotify.model.Song;
import org.spotify.model.UploadedFile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import java.nio.file.Files;
import java.nio.file.Paths;

@Path("/songs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SongResourse {
    @Inject
    private EntityManager em;

    private static final String UPLOAD_DIR = "/home/short/quarkus_uploads/";
    
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA) // Primamo fajl
    @Transactional // Potrebno jer mijenjamo podatke u db
    public Response uploadFileToSong(@QueryParam("songId") Long songId, FileUploadInput input) {
	Song song = em.find(Song.class, songId);
	if (song == null) {
	    return Response.status(Response.Status.NOT_FOUND)
		.entity("Pjesma sa tim ID-jem ne postoji")
		.build();
	}
	
	File uploadDir = new File(UPLOAD_DIR);
	if (!uploadDir.exists()) {
	    uploadDir.mkdirs();
	}

	String finalPathOnDisk = UPLOAD_DIR + input.filename;
	File targetFile = new File(finalPathOnDisk);

	UploadedFile uploadedFileEntity;

	if (targetFile.exists()) {
	    List<UploadedFile> existingsFiles = 
		em.createQuery("SELECT u FROM UploadedFile u WHERE u.filename = :path", UploadedFile.class)
		.setParameter("path", finalPathOnDisk)
		.getResultList();

	    if (!existingsFiles.isEmpty()) {
		uploadedFileEntity = existingsFiles.get(0);
	    } else {
		uploadedFileEntity = new UploadedFile();
		uploadedFileEntity.filename = finalPathOnDisk;
		em.persist(uploadedFileEntity);
	    }

	    if (!song.uploadedFiles.contains(uploadedFileEntity)) {
		song.uploadedFiles.add(uploadedFileEntity);
		em.merge(song);
	    }

	    return Response.status(Response.Status.OK)
		.entity("File exists and it's connected with song. Path: " + finalPathOnDisk)
		.build();
	}

	try {
	    Files.copy(input.file.filePath(), Paths.get(finalPathOnDisk), StandardCopyOption.REPLACE_EXISTING);
	} catch (IOException e) {
	    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
		.entity("ERROR while saving file on disk" + e.getMessage())
		.build();
	}

        uploadedFileEntity = new UploadedFile();
        uploadedFileEntity.filename = finalPathOnDisk;
        em.persist(uploadedFileEntity);

        song.uploadedFiles.add(uploadedFileEntity);
        em.merge(song); // Hibernate ovdje sam puni onu spojnu tabelu song_uploaded_files

        return Response.status(Response.Status.CREATED).entity(song).build();
    }

    @GET
    @Path("/{id}")
    public Response getSontWithFiles(@PathParam("id") Long id) {
	Song song = em.find(Song.class, id);

	if (song == null) {
	    return Response.status(Response.Status.NOT_FOUND)
		.entity("Song with ID: " + id + " is not found")
		.build();
	}

	if (song.uploadedFiles == null) {
	    return Response.status(Response.Status.NOT_FOUND)
		.entity("No files where uploaded!")
		.build();	    
	}

	for (UploadedFile uploadedFile : song.uploadedFiles) {
	    String pathOnDisk = uploadedFile.filename;
	    File realFile = new File(pathOnDisk);		
	    uploadedFile.file = realFile.exists() ? realFile : null;
	}
	
	return Response.ok(song).build();
    }
}
