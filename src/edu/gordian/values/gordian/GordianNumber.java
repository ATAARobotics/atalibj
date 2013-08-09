package edu.gordian.values.gordian;

import edu.first.util.MathUtils;
import edu.gordian.values.Value;

public class GordianNumber implements Value {

    private final double value;

    public static boolean is(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public GordianNumber(double value) {
        this.value = value;
    }

    public static GordianNumber valueOf(double d) {
        return new GordianNumber(d);
    }

    public static GordianNumber valueOf(String d) throws NumberFormatException {
        return new GordianNumber(Double.parseDouble(d));
    }

    public final double doubleValue() {
        return value;
    }

    public final int intValue() {
        return (int) MathUtils.round(value);
    }

    public final boolean isInt() {
        return value % 1.0 == 0;
    }

    public final Object getValue() {
        if (isInt()) {
            return Integer.valueOf(intValue());
        } else {
            return Double.valueOf(doubleValue());
        }
    }

    public final String toString() {
        if (isInt()) {
            return String.valueOf(intValue());
        } else {
            return String.valueOf(value);
        }
    }
}
