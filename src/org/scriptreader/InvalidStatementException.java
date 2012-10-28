package org.scriptreader;

/**
 * Exception thrown when the statement does not fit the criterion.
 *
 * @author Joel Gallant
 */
public class InvalidStatementException extends Exception {

    /**
     * Creates the exception with a message.
     *
     * @param message message for the user
     */
    public InvalidStatementException(String message) {
        super(message);
    }
}
