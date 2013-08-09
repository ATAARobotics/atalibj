package edu.gordian.values.adjustments;

import edu.gordian.scopes.Scope;
import edu.gordian.values.gordian.GordianNumber;

public final class Positive extends GordianNumber {

    public static boolean is(Scope s, String v) {
        try {
            return v.startsWith("+") && (s.toValue(v.substring(1)) instanceof GordianNumber);
        } catch (Scope.IsNotValue e) {
            // toValue didn't work
            return false;
        }
    }

    public static Positive valueOf(Scope s, String v) {
        return new Positive(((GordianNumber) s.toValue(v.substring(1))).doubleValue());
    }

    public Positive(double value) {
        super(value);
    }
}
