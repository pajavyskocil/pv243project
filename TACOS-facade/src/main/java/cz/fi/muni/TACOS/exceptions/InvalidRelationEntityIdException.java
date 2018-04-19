package cz.fi.muni.TACOS.exceptions;

/**
 * Exception specifying that entity for given id was not found
 * @author Vojtech Sassmann
 */
public class InvalidRelationEntityIdException extends Exception {

    public InvalidRelationEntityIdException() {
    }

    public InvalidRelationEntityIdException(String message) {
        super(message);
    }

    public InvalidRelationEntityIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRelationEntityIdException(Throwable cause) {
        super(cause);
    }

    public InvalidRelationEntityIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
