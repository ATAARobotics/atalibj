package edu.gordian.values.gordian;

import edu.gordian.values.Value;

public class GordianBoolean implements Value {

    private final boolean value;
    
    public static boolean is(String s) {
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false");
    }

    public GordianBoolean(boolean value) {
        this.value = value;
    }

    public static GordianBoolean valueOf(boolean v) {
        return new GordianBoolean(v);
    }

    public static GordianBoolean valueOf(String v) {
        return new GordianBoolean(v.equalsIgnoreCase("true"));
    }
    
    public final boolean booleanValue() {
        return value;
    }

    public final Object getValue() {
        return Boolean.valueOf(value);
    }

    public final String toString() {
        return value ? "true" : "false";
    }
}
