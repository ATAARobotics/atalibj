package edu.gordian.scopes;

import edu.gordian.values.gordian.GordianBoolean;

class While extends Scope {

    private final String condition;

    public While(String condition, Scope scope) {
        super(scope);
        this.condition = condition;
    }

    public void run(String script) throws Exception {
        while (((GordianBoolean) toValue(condition)).booleanValue()) {
            super.run(script);
        }
    }
}
