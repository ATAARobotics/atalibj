package edu.gordian.values.calculations;

import edu.gordian.scopes.Scope;
import edu.gordian.values.gordian.GordianNumber;

public final class Modulus extends GordianNumber {

    public static boolean is(Scope s, String v) {
        try {
            return (v.lastIndexOf('%') > 0 && v.lastIndexOf('%') < v.length() - 1)
                    && (s.toValue(v.substring(0, v.lastIndexOf('%'))) instanceof GordianNumber)
                    && (s.toValue(v.substring(v.lastIndexOf('%') + 1)) instanceof GordianNumber);
        } catch (Scope.IsNotValue e) {
            // toValue didn't work
            return false;
        }
    }

    public static Modulus valueOf(Scope s, String v) {
        return new Modulus(((GordianNumber) s.toValue(v.substring(0, v.lastIndexOf('%')))),
                ((GordianNumber) s.toValue(v.substring(v.lastIndexOf('%') + 1))));
    }

    public Modulus(GordianNumber first, GordianNumber second) {
        super(first.doubleValue() % second.doubleValue());
    }
}
