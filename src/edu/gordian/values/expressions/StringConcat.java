package edu.gordian.values.expressions;

import edu.gordian.Strings;
import edu.gordian.scopes.Scope;
import edu.gordian.values.gordian.GordianString;

public final class StringConcat extends GordianString {

    public static boolean is(Scope s, String v) {
        int[] i = Strings.allIndexesOf(v, '+');
        for (int x = 0; x < i.length; x++) {
            int index = i[x];
            try {
                return (index > 0 && index < v.length() - 1)
                        && ((s.toValue(v.substring(0, index)) instanceof GordianString)
                        || (s.toValue(v.substring(index + 1)) instanceof GordianString));
            } catch (Scope.IsNotValue e) {
                // toValue didn't work
            }
        }
        return false;
    }

    public static StringConcat valueOf(Scope s, String v) {
        int[] i = Strings.allIndexesOf(v, '+');
        for (int x = 0; x < i.length; x++) {
            int index = i[x];
            try {
                return new StringConcat((s.toValue(v.substring(0, index))).getValue().toString(),
                        (s.toValue(v.substring(index + 1))).getValue().toString());
            } catch (Exception e) {
                // toValue or casting didn't work
            }
        }
        throw new Scope.IsNotValue(v);
    }

    public StringConcat(String v1, String v2) {
        super(v1 + v2);
    }
}
