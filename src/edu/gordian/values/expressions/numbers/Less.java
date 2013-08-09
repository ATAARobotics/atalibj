package edu.gordian.values.expressions.numbers;

import edu.gordian.scopes.Scope;
import edu.gordian.values.gordian.GordianBoolean;

public final class Less extends GordianBoolean {

    public static boolean is(Scope s, String v) {
        try {
            if (v.indexOf('<') > 0 && v.indexOf('<') < v.length() - 1) {
                Object v1 = s.toValue(v.substring(0, v.indexOf('<'))).getValue();
                Object v2 = s.toValue(v.substring(v.indexOf('<') + 1)).getValue();
                return (v1 instanceof Double || v1 instanceof Integer)
                        && (v2 instanceof Double || v2 instanceof Integer);
            }
        } catch (Scope.IsNotValue e) {
            // toValue didn't work
        }
        return false;
    }

    public static Less valueOf(Scope s, String v) {
        return new Less(number(s.toValue(v.substring(0, v.indexOf('<'))).getValue()),
                number(s.toValue(v.substring(v.indexOf('<') + 1)).getValue()));
    }

    public Less(Double first, Double second) {
        super(first.doubleValue() < second.doubleValue());
    }

    private static Double number(Object o) {
        if (o instanceof Double) {
            return (Double) o;
        } else {
            return Double.valueOf(((Integer) o).doubleValue());
        }
    }
}
