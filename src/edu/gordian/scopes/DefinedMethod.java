package edu.gordian.scopes;

import com.sun.squawk.util.StringTokenizer;
import edu.gordian.elements.methods.MethodBase;
import edu.gordian.values.ReturningMethodBase;
import edu.gordian.values.Value;

final class DefinedMethod extends Scope implements MethodBase, ReturningMethodBase {

    private final String[] args;
    private final String script;
    private Object value;

    DefinedMethod(String[] args, String script, Scope scope) {
        super(scope);
        if (args == null) {
            throw new NullPointerException("Args given was null");
        }
        if (script == null) {
            throw new NullPointerException("Script given was null");
        }
        this.args = args;
        this.script = script;
    }

    public void run(Value[] arguments) {
        if (arguments == null || arguments.length < args.length) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        for (int x = 0; x < args.length; x++) {
            setPrivateVariable(args[x], arguments[x]);
        }

        try {
            RunningEnvironment environment = new RunningEnvironment();
            StringTokenizer tokenizer = preRun(script);
            while (tokenizer.hasMoreElements()) {
                try {
                    environment.next(tokenizer.nextToken());
                } catch (ValueReturned ex) {
                    this.value = ex.value;
                    break;
                }
            }
            if (environment.scopes != 0) {
                throw new RuntimeException("Scope was never completed. Use 'end' to complete scopes.");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void returnValue(Object value) {
        throw new ValueReturned(value);
    }

    public Object runFor(Value[] arguments) {
        run(arguments);
        if (value == null) {
            throw new RuntimeException("No value was returned");
        }
        return value;
    }

    private static class ValueReturned extends RuntimeException {

        private final Object value;

        public ValueReturned(Object value) {
            this.value = value;
        }
    }
}
