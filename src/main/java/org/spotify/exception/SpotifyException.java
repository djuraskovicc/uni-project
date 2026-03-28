package org.spotify.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpotifyException extends Exception {
    public SpotifyException(String message) {
	super(message);
    }
}
