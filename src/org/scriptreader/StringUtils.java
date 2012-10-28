package org.scriptreader;

public class StringUtils {

    public static boolean isEmpty(String string) {
        return string.length() == 0;
    }

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

    public static boolean contains(String string, String contains) {
        return string.indexOf(contains) > -1;
    }

    public static String replace(String string, char replace, String replacement) {
        String[] s = split(string, replace);

        String tmp = "";
        for (int x = 0; x < s.length; x++) {
            if (contains(s[x], replace + "")) {
                tmp += s[x].substring(1);
            } else {
                tmp += s[x];
            }
        }
        return tmp;
    }
}
