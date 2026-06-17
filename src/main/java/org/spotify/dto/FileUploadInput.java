package org.spotify.dto;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public class FileUploadInput {
    @RestForm("file")
    public FileUpload file;

    @RestForm("filename")
    public String filename;
}
