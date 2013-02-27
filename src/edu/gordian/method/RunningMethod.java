package edu.gordian.method;

import edu.gordian.Instruction;
import edu.gordian.Variable;

public abstract class RunningMethod extends Method implements Instruction {

    private final Variable[] args;

    public static RunningMethod createMethod(final RunningMethod method, Variable[] args) {
        return new ComposedMethod(method, args);
    }

    public RunningMethod(String methodName, Variable[] args) {
        super(methodName);
        this.args = args;
    }

    public RunningMethod(String methodName) {
        super(methodName);
        this.args = new Variable[0];
    }
    
    public final void run() {
        run(args);
    }

    public abstract void run(Variable[] args);

    private static final class ComposedMethod extends RunningMethod {

        private final RunningMethod method;
        private final Variable[] arguments;

        public ComposedMethod(RunningMethod method, Variable[] arguments) {
            super(method.getMethodName());
            this.method = method;
            this.arguments = arguments;
        }

        public void run(Variable[] args) {
            method.run(this.arguments);
        }
    }
}
