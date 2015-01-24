package edu.first.lang;

/**
 * Exception that tells the user that the call could not be completed because
 * something was out of sync.
 *
 * @since June 15 13
 * @author Joel Gallant
 */
public class OutOfSyncException extends IllegalStateException {
	private static final long serialVersionUID = -847453090534920352L;

	/**
     * Constructs an OutOfSyncException with no detail message. A detail message
     * is a String that describes this particular exception.
     */
    public OutOfSyncException() {
        super();
    }

    /**
     * Constructs an OutOfSyncException with the specified detail message. A
     * detail message is a String that describes this particular exception.
     *
     * @param s the String that contains a detailed message
     */
    public OutOfSyncException(String s) {
        super(s);
    }
}
