package edu.gordian.variable.field;

import edu.gordian.Field;
import edu.gordian.variable.BooleanVariable;

public final class BooleanField extends BooleanVariable implements Field {

    private final String name;

    public BooleanField(String name, boolean value) {
        super(value);
        this.name = name;
    }

    public BooleanField(String name, BooleanVariable value) {
        super(value.booleanValue());
        this.name = name;
    }

    public String fieldName() {
        return name;
    }

    public String getLiteralString() {
        return name;
    }
}
