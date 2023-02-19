package fpt.sep490.exception;

import org.springframework.http.HttpStatus;

public class FoodifyAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public FoodifyAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public FoodifyAPIException(HttpStatus status, String message, String message1) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
