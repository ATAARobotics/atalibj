package edu.first.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

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
     * @throws java.io.IOException when reading causes an error
     * @throws NullPointerException when file name is null
     */
    public static String getTextFromFile(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException();
        }
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException ex) {
            throw ex;
        }
    }

    /**
     * Writes the text as the full text file. Everything else is deleted.
     *
     * @param file file to write to
     * @param msg text to set the file to
     * @throws java.io.IOException when writing causes an error
     */
    public static void writeAsFile(File file, String msg) throws IOException {
        write(file, msg, false);
    }

    /**
     * Writes the text at the very end of the file.
     *
     * @param file file to write to
     * @param msg text to add to the end of the file
     * @throws java.io.IOException when writing causes an error
     */
    public static void appendToFile(File file, String msg) throws IOException {
        write(file, msg, true);
    }

    /**
     * Writes the text to a new line at the end of the file.
     *
     * @param file file to write to
     * @param msg text to add to the end of the file
     * @throws java.io.IOException when writing causes an error
     */
    public static void appendToNewLine(File file, String msg) throws IOException {
        appendToFile(file, "\n" + msg);
    }

    private static void write(File f, String m, boolean append) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(f, append);
            fw.write(m);
            fw.close();
        } catch (IOException ex) {
            throw ex;
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                throw ex;
            }
        }
    }
}
