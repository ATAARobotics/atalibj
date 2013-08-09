package edu.gordian.scopes;

import edu.gordian.values.gordian.GordianNumber;

final class For extends Scope {

    private final int condition;

    For(String condition, Scope scope) {
        super(scope);
        this.condition = ((GordianNumber) toValue(condition)).intValue();
    }

    public void run(String script) throws Exception {
        for (int x = 0; x < condition; x++) {
            super.run(script);
        }
    }
}
