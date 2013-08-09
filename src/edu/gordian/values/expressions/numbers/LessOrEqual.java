package edu.gordian.values.expressions.numbers;

import edu.gordian.scopes.Scope;
import edu.gordian.values.gordian.GordianBoolean;

public final class LessOrEqual extends GordianBoolean {

    public static boolean is(Scope s, String v) {
        try {
            if (v.indexOf("<=") > 0 && v.indexOf("<=") < v.length() - 2) {
                Object v1 = s.toValue(v.substring(0, v.indexOf("<="))).getValue();
                Object v2 = s.toValue(v.substring(v.indexOf("<=") + 2));
                return (v1 instanceof Double || v1 instanceof Integer)
                        && (v2 instanceof Double || v2 instanceof Integer);
            }
        } catch (Scope.IsNotValue e) {
            // toValue didn't work
        }
        return false;
    }

    public static LessOrEqual valueOf(Scope s, String v) {
        return new LessOrEqual(number(s.toValue(v.substring(0, v.indexOf("<="))).getValue()),
                number(s.toValue(v.substring(v.indexOf("<=") + 2)).getValue()));
    }

    public LessOrEqual(Double first, Double second) {
        super(first.doubleValue() <= second.doubleValue());
    }

    private static Double number(Object o) {
        if (o instanceof Double) {
            return (Double) o;
        } else {
            return Double.valueOf(((Integer) o).doubleValue());
        }
    }
}
