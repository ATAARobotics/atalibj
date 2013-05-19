package edu.first.util;

import edu.first.util.list.ArrayList;

/**
 * A set of utility methods to manipulate and test strings. Contains many of the
 * methods that {@code String} is missing in Java ME.
 *
 * @since May 14 13
 * @author Joel Gallant
 */
public class Strings {

    // cannot be subclassed or instantiated
    private Strings() throws IllegalAccessException {
        throw new IllegalAccessException();
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
     * Splits the string into separate parts that happen in between the split.
     * <p>
     * Ex. {@code spilt("Hello world, my name is Tim.", ",")}
     *
     * Would return {@code ["Hello World", " my name is Tim."]}
     *
     * @param string original string to split
     * @param split spliting artifact in the string
     * @return parts of the string in between the splits
     * @throws NullPointerException when {@code split} is null
     */
    public static String[] split(String string, String split) {
        if (string == null) {
            return null;
        }
        if (split == null) {
            throw new NullPointerException();
        }
        ArrayList node = new ArrayList();
        int index = string.indexOf(split);
        while (index >= 0) {
            node.add(string.substring(0, index));
            string = string.substring(index + split.length());
            index = string.indexOf(split);
        }
        node.add(string);

        String[] a = new String[node.size()];
        for (int x = 0; x < a.length; x++) {
            a[x] = node.get(x).toString();
        }

        return a;
    }

    /**
     * Splits the string into separate parts that happen in between the split.
     * <p>
     * Ex. {@code spilt("Hello world, my name is Tim.", ',')}
     *
     * Would return {@code ["Hello World", " my name is Tim."]}
     *
     * @param string original string to split
     * @param split spliting character in the string
     * @return parts of the string in between the splits
     */
    public static String[] split(String string, char split) {
        return split(string, String.valueOf(split));
    }

    /**
     * Determines whether a string contains a different string.
     *
     * @param string string to test
     * @param contains the string it could contain
     * @return whether the string contains the other
     * @throws NullPointerException when {@code string} or {@code contains} are
     * null
     */
    public static boolean contains(String string, String contains) {
        if (string == null || contains == null) {
            throw new NullPointerException();
        }
        return string.indexOf(contains) > -1;
    }

    /**
     * Determines whether a string contains a specific character.
     *
     * @param string string to test
     * @param contains the character it could contain
     * @return whether the string contains the other
     * @throws NullPointerException when {@code string} is null
     */
    public static boolean contains(String string, char contains) {
        return contains(string, String.valueOf(contains));
    }

    /**
     * Determines whether a string contains a different string, regardless of
     * case.
     *
     * @param string string to test
     * @param contains the string it could contain
     * @return whether the string contains the other
     * @throws NullPointerException when {@code string} or {@code contains} are
     * null
     */
    public static boolean containsIgnoreCase(String string, String contains) {
        return contains(string.toLowerCase(), contains.toLowerCase());
    }

    /**
     * Determines whether a string contains a specific character, regardless of
     * case.
     *
     * @param string string to test
     * @param contains the character it could contain
     * @return whether the string contains the other
     * @throws NullPointerException when {@code string} is null
     */
    public static boolean containsIgnoreCase(String string, char contains) {
        return containsIgnoreCase(string, String.valueOf(contains));
    }

    /**
     * Replaces the selected section with the replacement.
     *
     * <p>
     * Ex: {@code replace("ABC", "B", "D")} returns {@code "ADC"}.
     *
     * @param string main string to do replacement on
     * @param replace what section to replace
     * @param replacement the replacement for the section
     * @return string with {@code replace} replaced with {@code replacement}
     * @throws NullPointerException when replace string or replacement string
     * are null
     */
    public static String replace(String string, String replace, String replacement) {
        if (string == null) {
            return null;
        }
        if (replace == null || replacement == null) {
            throw new NullPointerException();
        }
        return string.substring(0, string.indexOf(replace)) + replacement
                + string.substring(string.indexOf(replace) + replace.length(), string.length());
    }

    /**
     * Replaces the selected section with the replacement.
     *
     * <p>
     * Ex: {@code replace("ABC", 'B', "D")} returns {@code "ADC"}.
     *
     * @param string main string to do replacement on
     * @param replace what section to replace
     * @param replacement the replacement for the section
     * @return string with {@code replace} replaced with {@code replacement}
     * @throws NullPointerException when replacement string is null
     */
    public static String replace(String string, char replace, String replacement) {
        return replace(string, String.valueOf(replace), replacement);
    }

    /**
     * Replaces the selected section with the replacement.
     *
     * <p>
     * Ex: {@code replace("ABC", "B", 'D')} returns {@code "ADC"}.
     *
     * @param string main string to do replacement on
     * @param replace what section to replace
     * @param replacement the replacement for the section
     * @return string with {@code replace} replaced with {@code replacement}
     * @throws NullPointerException when replace string is null
     */
    public static String replace(String string, String replace, char replacement) {
        return replace(string, replace, String.valueOf(replacement));
    }

    /**
     * Replaces the selected section with the replacement.
     *
     * <p>
     * Ex: {@code replace("ABC", 'B', 'D')} returns {@code "ADC"}.
     *
     * @param string main string to do replacement on
     * @param replace what section to replace
     * @param replacement the replacement for the section
     * @return string with {@code replace} replaced with {@code replacement}
     */
    public static String replace(String string, char replace, char replacement) {
        return replace(string, String.valueOf(replace), String.valueOf(replacement));
    }

    /**
     * Replaces all instances of {@code replace} with {@code replacement}.
     *
     * <p>
     * Ex: {@code replaceAll("ABCABC", "B", "D")} returns {@code "ADCADC"}.
     *
     * @param string main string to do replacement on
     * @param replace what section to replace
     * @param replacement the replacement for the section
     * @return string with all instances of {@code replace} replaced with
     * {@code replacement}
     * @throws NullPointerException when {@code replace} string or
     * {@code replacement} string are null
     */
    public static String replaceAll(String string, String replace, String replacement) {
        if (string == null) {
            return null;
        }
        if (replace == null || replacement == null) {
            throw new NullPointerException();
        }
        String[] s = split(string, replace);
        if (s.length == 0) {
            return string;
        }

        StringBuffer tmp = new StringBuffer(s[0]);
        for (int x = 1; x < s.length; x++) {
            tmp.append(replacement).append(s[x]);
        }
        return tmp.toString();
    }

    /**
     * Replaces all instances of {@code replace} with {@code replacement}.
     *
     * <p>
     * Ex: {@code replaceAll("ABCABC", 'B', "D")} returns {@code "ADCADC"}.
     *
     * @param string main string to do replacement on
     * @param replace what section to replace
     * @param replacement the replacement for the section
     * @return string with all instances of {@code replace} replaced with
     * {@code replacement}
     * @throws NullPointerException when {@code replacement} string is null
     */
    public static String replaceAll(String string, char replace, String replacement) {
        return replaceAll(string, String.valueOf(replace), replacement);
    }

    /**
     * Replaces all instances of {@code replace} with {@code replacement}.
     *
     * <p>
     * Ex: {@code replaceAll("ABCABC", "B", 'D')} returns {@code "ADCADC"}.
     *
     * @param string main string to do replacement on
     * @param replace what section to replace
     * @param replacement the replacement for the section
     * @return string with all instances of {@code replace} replaced with
     * {@code replacement}
     * @throws NullPointerException when {@code replace} string is null
     */
    public static String replaceAll(String string, String replace, char replacement) {
        return replaceAll(string, replace, String.valueOf(replacement));
    }

    /**
     * Replaces all instances of {@code replace} with {@code replacement}.
     *
     * <p>
     * Ex: {@code replaceAll("ABCABC", 'B', 'D')} returns {@code "ADCADC"}.
     *
     * @param string main string to do replacement on
     * @param replace what section to replace
     * @param replacement the replacement for the section
     * @return string with all instances of {@code replace} replaced with
     * {@code replacement}
     */
    public static String replaceAll(String string, char replace, char replacement) {
        return replaceAll(string, String.valueOf(replace), String.valueOf(replacement));
    }
}
