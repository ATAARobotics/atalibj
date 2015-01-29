package edu.first.util;

import java.io.File;

/**
 * A set of utility methods to manipulate text files. Behavior of IO is
 * documented in Javadocs.
 *
 * @since May 14 13
 * @author Joel Gallant
 */
public final class TextFiles {

    // cannot be subclassed or instantiated
    private TextFiles() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Returns the text that is currently in a text file. Does not assume ".txt"
     * ending, so file type must be added manually.
     *
     * All IO error are caught and logged <i>inside</i> of this method, as they
     * are assumed to be things that are unaccountable for (hardware issues).
     *
     * If an IO error occurs, this method returns null. That is the only
     * occasion that this method will return null, so it is safe to assume that
     * null means an error occurred.
     *
     * @param file file to get text from
     * @return contents of that file
     * @throws NullPointerException when file name is null
     */
    public static @Deprecated
    String getTextFromFile(File file) {
        // TO DO
        return null;
    }

    /**
     * Writes the text as the full text file. Everything else is deleted.
     *
     * @param file file to write to
     * @param msg text to set the file to
     */
    public static @Deprecated
    void writeAsFile(File file, String msg) {
        // TO DO
    }

    /**
     * Writes the text at the very end of the file.
     *
     * @param file file to write to
     * @param msg text to add to the end of the file
     */
    public static @Deprecated
    void appendToFile(File file, String msg) {
        // TO DO
    }

    /**
     * Writes the text to a new line at the end of the file.
     *
     * @param file file to write to
     * @param msg text to add to the end of the file
     */
    public static @Deprecated
    void appendToNewLine(File file, String msg) {
        // TO DO
    }
}
