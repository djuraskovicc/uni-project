package org.spotify.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserException extends Exception {
    public UserException(String message) {
	super(message);
    }
}
