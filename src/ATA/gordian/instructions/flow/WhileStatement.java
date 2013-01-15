package ATA.gordian.instructions.flow;

import ATA.gordian.Data;
import ATA.gordian.Instruction;
import ATA.gordian.data.BooleanData;

/**
 * Conditional flow that continues to run until the argument is false.
 *
 * @author Joel Gallant
 */
public class WhileStatement extends ConditionedFlow {

    /**
     * Checks to see if a string is convertible into the instruction type.
     *
     * @param instruction string to convert
     * @return whether it is an instruction
     */
    public static boolean isType(String instruction) {
        return instruction.trim().startsWith("while(");
    }

    /**
     * Converts a string into the appropriate subclass of {@code Instruction}.
     * Use {@link Instruction#isType(java.lang.String)} to ensure this method
     * returns something.
     *
     * @param instruction string to convert
     * @return instruction object representing the string
     */
    public static Instruction get(String instruction) {
        return new WhileStatement(instruction);
    }

    /**
     * Creates instruction based on its literal string in the code.
     *
     * @param full string in code
     */
    public WhileStatement(String full) {
        super(full);
    }

    /**
     * Determines whether or not to run the loop again. Will always run if the
     * argument == true.
     *
     * @param args argument between '(' and ')'
     * @return whether to run
     */
    protected boolean runAgain(String args) {
        Data arg = Data.get(args);
        if (arg instanceof BooleanData) {
            if (((BooleanData) arg).getValue().equals(Boolean.TRUE)) {
                return true;
            }
        }
        return false;
    }
}
