package ATA.gordian;

/**
 * A basic utility library for manipulating strings.
 *
 * @author joel
 */
public class StringUtils {

    private StringUtils() {
    }

    /**
     * Determines if the string does not contain anything.
     *
     * @param string string to test
     * @return whether the string is empty
     */
    public static boolean isEmpty(String string) {
        return string.length() == 0;
    }

    /**
     * Splits the string into different parts by a character.
     *
     * @param string string to test
     * @param split where to split the string
     * @return array of strings split by the char
     */
    public static String[] split(String string, char split) {
        return _split(new String[0], string, split);
    }

    private static String[] _split(String[] current, String string, char split) {
        if (isEmpty(string)) {
            return current;
        }
        String[] tmp = current;
        current = new String[tmp.length + 1];
        System.arraycopy(tmp, 0, current, 0, tmp.length);
        if (contains(string, split + "")) {
            current[current.length - 1] = string.substring(0, string.indexOf(split));
            string = string.substring(string.indexOf(split) + 1);
        } else {
            current[current.length - 1] = string;
            string = "";
        }
        return _split(current, string, split);
    }

    /**
     * Determines whether a string contains a different string.
     *
     * @param string string to test
     * @param contains the string it could contain
     * @return whether the string contains the other
     */
    public static boolean contains(String string, String contains) {
        return string.indexOf(contains) > -1;
    }

    /**
     * Replaces all instances of a character with a string
     *
     * @param string string to test
     * @param replace character to replace
     * @param replacement string of replacement
     * @return string with all characters replaced
     */
    public static String replace(String string, char replace, String replacement) {
        String[] s = split(string, replace);
        if (s.length == 0) {
            return string;
        }

        String tmp = "";
        for (int x = 1; x < s.length; x++) {
            tmp += replacement + s[x];
        }
        tmp = s[0] + tmp;
        if (string.lastIndexOf(replace) == string.length() - 1) {
            tmp += replacement;
        }
        return tmp;
    }
}
