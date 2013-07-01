package edu.first.util;

import edu.first.util.list.ArrayList;

/**
 * A set of utility methods to manipulate and test strings. Contains many of the
 * methods that {@code String} is missing in Java ME.
 *
 * @since May 14 13
 * @author Joel Gallant
 */
public final class Strings {

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
     * Uses {@link #isBeside(java.lang.String, int, int, java.lang.String)} to
     * test if the first instance of {@code is} is beside {@code beside}.
     *
     * <p> Basically equivalent to calling
     * <pre>
     * isBeside(string, string.indexOf(is), is.length(), beside)
     * </pre>
     *
     * @param string original string to check in
     * @param is string to check beside
     * @param beside string that could be beside {@code is}
     * @return if {@code beside} is directly beside the first instance of
     * {@code is}
     */
    public static boolean isFirstInstanceBeside(String string, String is, String beside) {
        return isBeside(string, string.indexOf(is), is.length(), beside);
    }

    /**
     * Uses {@link #isBeside(java.lang.String, int, int, java.lang.String)} to
     * test if the first instance of {@code is} is beside {@code beside}.
     *
     * <p> Basically equivalent to calling
     * <pre>
     * isBeside(string, string.indexOf(is), is.length(), beside)
     * </pre>
     *
     * @param string original string to check in
     * @param is string to check beside
     * @param beside character that could be beside {@code is}
     * @return if {@code beside} is directly beside the first instance of
     * {@code is}
     */
    public static boolean isFirstInstanceBeside(String string, String is, char beside) {
        return isFirstInstanceBeside(string, is, String.valueOf(beside));
    }

    /**
     * Uses {@link #isBeside(java.lang.String, int, int, java.lang.String)} to
     * test if the first instance of {@code is} is beside {@code beside}.
     *
     * <p> Basically equivalent to calling
     * <pre>
     * isBeside(string, string.indexOf(is), 1, beside)
     * </pre>
     *
     * @param string original string to check in
     * @param is string to check beside
     * @param beside string that could be beside {@code is}
     * @return if {@code beside} is directly beside the first instance of
     * {@code is}
     */
    public static boolean isFirstInstanceBeside(String string, char is, String beside) {
        return isBeside(string, string.indexOf(is), 1, beside);
    }

    /**
     * Uses {@link #isBeside(java.lang.String, int, int, java.lang.String)} to
     * test if the first instance of {@code is} is beside {@code beside}.
     *
     * <p> Basically equivalent to calling
     * <pre>
     * isBeside(string, string.indexOf(is), 1, beside)
     * </pre>
     *
     * @param string original string to check in
     * @param is string to check beside
     * @param beside character that could be beside {@code is}
     * @return if {@code beside} is directly beside the first instance of
     * {@code is}
     */
    public static boolean isFirstInstanceBeside(String string, char is, char beside) {
        return isFirstInstanceBeside(string, is, String.valueOf(beside));
    }

    /**
     * Returns whether the element at {@code c} index in {@code string} with
     * {@code length} length is directly beside {@code beside}.
     *
     * @param string original string to check in
     * @param c index of the element to check beside
     * @param length the length of {@code c}
     * @param beside string that could be beside {@code c}
     * @return if {@code beside} is directly beside {@code c}, in the context of
     * {@code c}'s length
     */
    public static boolean isBeside(String string, int c, int length, String beside) {
        if (c < 0 || c + length >= string.length()) {
            return false;
        }
        int startFront = c - beside.length();
        int endback = c + length + beside.length();
        boolean f = true, b = true;
        if (startFront < 0 || startFront >= string.length()) {
            f = false;
        }
        if (endback < 0 || endback >= string.length()) {
            b = false;
        }

        return f || b ? ((f ? (string.substring(startFront, c).equals(beside)) : false)
                || (b ? string.substring(c + length, endback).equals(beside) : false)) : false;
    }

    /**
     * Returns whether <b>any</b> of the instances of {@code is} are directly
     * beside {@code beside}. Use
     * {@link #isBeside(java.lang.String, int, int, java.lang.String)} to check
     * a specific instance of the string.
     *
     * @param string original string to check in
     * @param is string to check beside
     * @param beside string that could be beside {@code is}
     * @return if {@code beside} is directly beside any instances of {@code is}
     */
    public static boolean isBeside(String string, String is, String beside) {
        String[] s = split(string, beside);
        for (int x = 0; x < s.length; x++) {
            if (s[x].startsWith(is) || s[x].endsWith(is)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether any instances of {@code c} are directly beside
     * {@code beside} in {@code string}.
     *
     * @param string original string to check in
     * @param c character to check beside
     * @param beside string that could be beside {@code c}
     * @return if {@code beside} is directly beside any instances of {@code c}
     */
    public static boolean isBeside(String string, char c, String beside) {
        return isBeside(string, String.valueOf(c), beside);
    }

    /**
     * Returns whether any instances of {@code c} are directly beside
     * {@code beside} in {@code string}.
     *
     * @param string original string to check in
     * @param c character to check beside
     * @param beside character that could be beside {@code c}
     * @return if {@code beside} is directly beside any instances of {@code c}
     */
    public static boolean isBeside(String string, char c, char beside) {
        return isBeside(string, c, String.valueOf(beside));
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
        if (string.indexOf(split) < 0) {
            return new String[]{string};
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
        if (string.indexOf(replace) < 0) {
            return string;
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
