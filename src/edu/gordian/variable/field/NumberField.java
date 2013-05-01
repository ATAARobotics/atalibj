package edu.gordian.variable.field;

import edu.gordian.Field;
import edu.gordian.variable.NumberInterface;
import edu.gordian.variable.NumberVariable;

public final class NumberField extends NumberVariable implements Field {

    private final String name;

    public NumberField(String name, double value) {
        super(value);
        this.name = name;
    }

    public NumberField(String name, NumberInterface value) {
        super(value.doubleValue());
        this.name = name;
    }

    public String fieldName() {
        return name;
    }

    public String getLiteralString() {
        return name;
    }
}
