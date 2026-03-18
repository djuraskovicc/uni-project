CREATE TABLE users (
       id SERIAL PRIMARY KEY NOT NULL,
       email VARCHAR(255) NOT NULL,
       password_hash VARCHAR(100) NOT NULL,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE profiles (
       user_id INT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
       display_name VARCHAR(100),
       bio TEXT,
       profile_pic_url TEXT
);

CREATE TABLE artists (
       id SERIAL PRIMARY KEY NOT NULL,
       name VARCHAR(255) NOT NULL,
       genre VARCHAR(100)
);

CREATE TABLE albums (
       id SERIAL PRIMARY KEY NOT NULL,
       title VARCHAR(255) NOT NULL,
       release_date DATE NOT NULL,
       artist_id INT REFERENCES artists(id) ON DELETE SET NULL
);

CREATE TABLE songs (
       id SERIAL PRIMARY KEY NOT NULL,
       title VARCHAR(255) NOT NULL,
       duration INT, -- in seconds
       album_id INT REFERENCES albums(id) ON DELETE SET NULL
);

CREATE TABLE playlists (
       id SERIAL PRIMARY KEY NOT NULL,
       name VARCHAR(255),
       user_id INT REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE artist_song (
    artist_id INT REFERENCES artists(id) ON DELETE CASCADE,
    song_id INT REFERENCES songs(id) ON DELETE CASCADE,
    PRIMARY KEY (artist_id, song_id)
);

CREATE TABLE playlist_song (
    playlist_id INT REFERENCES playlists(id) ON DELETE CASCADE,
    song_id INT REFERENCES songs(id) ON DELETE CASCADE,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (playlist_id, song_id)
);
