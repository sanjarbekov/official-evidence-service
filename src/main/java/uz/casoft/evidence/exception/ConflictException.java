package uz.casoft.evidence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }

    public static ConflictException of(String entity, String reason) {
        return new ConflictException(entity + " conflict: " + reason);
    }
}
