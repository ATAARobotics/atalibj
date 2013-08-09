package edu.gordian.elements.declarations;

import edu.gordian.scopes.Scope;
import edu.gordian.values.gordian.GordianNumber;

/**
 * Changes the value of a variable.
 *
 * @author Joel Gallant
 */
public final class ValueAdjustment implements Runnable {

    private final Scope scope;
    private final String key;
    private final double i;

    /**
     * Constructs the adjustment.
     *
     * @param scope scope that adjustment is being performed in
     * @param key variable name
     * @param i change to apply
     */
    public ValueAdjustment(Scope scope, String key, double i) {
        if (scope == null) {
            throw new NullPointerException("Scope is null");
        }
        if (key == null) {
            throw new NullPointerException("Key is null");
        }
        this.scope = scope;
        this.key = key;
        this.i = i;
    }

    /**
     * Changes the value.
     */
    public void run() {
        double value;
        if (scope.isVariable(key)) {
            GordianNumber v = (GordianNumber) scope.getVariable(key);
            value = v.doubleValue();
        } else {
            value = 0;
        }
        value += i;
        scope.setVariable(key, GordianNumber.valueOf(value));
    }
}
