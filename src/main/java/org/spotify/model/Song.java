package org.spotify.model;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "songs")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQuery(name = Song.GET_ALL_SONGS, query = "Select s from Song s")
public class Song {
    public static final String GET_ALL_SONGS = "GetAllSongs";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    public Long id;

    @Column(nullable = false)
    public String title;
    public Integer duration;

    @Column(name = "album_id")
    public Long albumId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
	       name = "song_uploaded_files", // Spojna tabela koju hibernate sam pravi
	       joinColumns = @JoinColumn(name = "song_id"),
	       inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    public List<UploadedFile> uploadedFiles = new ArrayList<>();
}
