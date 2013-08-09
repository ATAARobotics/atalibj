package edu.gordian.values.expressions;

import edu.gordian.Strings;
import edu.gordian.scopes.Scope;
import edu.gordian.values.gordian.GordianBoolean;

public final class And extends GordianBoolean {

    public static boolean is(Scope s, String v) {
        int[] i = Strings.allIndexesOf(v, "&&");
        for (int x = 0; x < i.length; x++) {
            int index = i[x];
            try {
                return index > 0 && index < v.length() - 2
                        && (s.toValue(v.substring(0, index)) instanceof GordianBoolean)
                        && (s.toValue(v.substring(index + 2)) instanceof GordianBoolean);
            } catch (Scope.IsNotValue e) {
                // toValue didn't work
            }
        }
        return false;
    }

    public static And valueOf(Scope s, String v) {
        int[] i = Strings.allIndexesOf(v, "&&");
        for (int x = 0; x < i.length; x++) {
            int index = i[x];
            try {
                return new And(((GordianBoolean) s.toValue(v.substring(0, index))).booleanValue()
                        && ((GordianBoolean) s.toValue(v.substring(index + 2))).booleanValue());
            } catch (Exception e) {
                // toValue or casting didn't work
            }
        }
        throw new Scope.IsNotValue(v);
    }

    public And(boolean value) {
        super(value);
    }
}
