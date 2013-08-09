package edu.gordian.elements.declarations;

import edu.gordian.scopes.Scope;
import edu.gordian.values.Value;

/**
 * A declaration of a variable.
 *
 * @author Joel Gallant
 */
public final class Declaration implements Runnable {

    private final Scope scope;
    private final String key;
    private final String value;

    /**
     * Constructs the declaration.
     *
     * @param scope scope that declaration is being performed in
     * @param key key of the variable
     * @param value value in string form in script
     */
    public Declaration(Scope scope, String key, String value) {
        if (scope == null) {
            throw new NullPointerException("Scope is null");
        }
        if (key == null) {
            throw new NullPointerException("Key is null");
        }
        if (value == null) {
            throw new NullPointerException("Value is null");
        }
        this.scope = scope;
        this.key = key;
        this.value = value;
    }

    /**
     * Sets the variable to the given value.
     */
    public void run() {
        Value val = scope.toValue(value);
        scope.setVariable(key, val);
    }
}
