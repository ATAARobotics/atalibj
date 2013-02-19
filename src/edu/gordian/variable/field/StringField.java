package edu.gordian.variable.field;

import edu.gordian.Field;
import edu.gordian.variable.StringVariable;

public final class StringField extends StringVariable implements Field {

    private final String name;

    public StringField(String name, String value) {
        super(value);
        this.name = name;
    }

    public StringField(String name, StringVariable value) {
        super(value.stringValue());
        this.name = name;
    }

    public String fieldName() {
        return name;
    }

    public String getLiteralString() {
        return name;
    }
}
