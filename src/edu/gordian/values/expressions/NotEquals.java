package edu.gordian.values.expressions;

import edu.gordian.Strings;
import edu.gordian.scopes.Scope;
import edu.gordian.values.gordian.GordianBoolean;

public final class NotEquals extends GordianBoolean {

    public static boolean is(Scope s, String v) {
        int[] i = Strings.allIndexesOf(v, "!=");
        for (int x = 0; x < i.length; x++) {
            int index = i[x];
            try {
                if (index > 0 && index < v.length() - 2) {
                    Class c1 = s.toValue(v.substring(0, index)).getValue().getClass();
                    Class c2 = s.toValue(v.substring(index + 2)).getValue().getClass();
                    if((c1.equals(Double.class) || c1.equals(Integer.class))
                            && (c2.equals(Double.class) || c2.equals(Integer.class))) {
                        // Both numbers
                        return true;
                    }
                    return c1.equals(c2);
                }
            } catch (Scope.IsNotValue e) {
                // toValue didn't work
            }
        }
        return false;
    }

    public static NotEquals valueOf(Scope s, String v) {
        int[] i = Strings.allIndexesOf(v, "!=");
        for (int x = 0; x < i.length; x++) {
            int index = i[x];
            try {
                Object val1 = s.toValue(v.substring(0, index)).getValue();
                Object val2 = s.toValue(v.substring(index + 2)).getValue();
                if(number(val1) != null && number(val2) != null) {
                    // Both numbers
                    return new NotEquals(number(val1).equals(number(val2)));
                }
                return new NotEquals(val1.equals(val2));
            } catch (Exception e) {
                // toValue or casting didn't work
            }
        }
        throw new Scope.IsNotValue(v);
    }

    public NotEquals(boolean value) {
        super(!value);
    }
    
    private static Double number(Object o) {
        if(o instanceof Double) {
            return (Double) o;
        } else if (o instanceof Integer) {
            return Double.valueOf(((Integer) o).doubleValue());
        } else {
            return null;
        }
    }
}
