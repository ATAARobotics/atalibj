package edu.first.util;

/**
 * Class to represent files to better organize them.
 *
 * @since June 07 13
 * @author Joel Gallant
 */
public final class File {

    private final String fullPath;

    /**
     * Constructs the file with the direct path. Most formats for files should
     * work here, but use "/" for path separators.
     *
     * @param path the path to the file
     */
    public File(String path) {
        path = Strings.replace(path, "file:", "");
        while (path.startsWith("/")) {
            path = path.substring(1);
        }
        this.fullPath = "file:///" + path;
    }

    /**
     * Returns the full path of the file that can be used by IO.
     *
     * @return complete path of the file
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * Returns the path of the file as a user would usually see.
     *
     * @return the path of the file
     */
    public String getPath() {
        return Strings.replace(fullPath, "file//", "");
    }

    public String toString() {
        return getFullPath();
    }
}
