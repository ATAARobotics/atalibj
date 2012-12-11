package edu.ata.gordian.instructions.flow;

import edu.ata.gordian.Data;
import edu.ata.gordian.Instruction;
import edu.ata.gordian.data.BooleanData;

/**
 * Instruction that runs conditionally once depending on the argument.
 *
 * @author Joel Gallant
 */
public class IfStatement extends ConditionedFlow {

    /**
     * Checks to see if a string is convertible into the instruction type.
     *
     * @param instruction string to convert
     * @return whether it is an instruction
     */
    public static boolean isType(String instruction) {
        return instruction.trim().startsWith("if(");
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
        return new IfStatement(instruction);
    }
    private boolean ran = false;

    /**
     * Creates the instruction based on the string in the code.
     *
     * @param full string in code
     */
    public IfStatement(String full) {
        super(full);
    }

    /**
     * Whether or not to run again. Will run if the condition is true and the
     * statement has not been run already.
     *
     * @param args arguments of statement
     * @return whether the block should run
     */
    protected boolean runAgain(String args) {
        Data arg = Data.get(args);
        if (arg instanceof BooleanData) {
            if (((BooleanData) arg).getValue().equals(Boolean.TRUE)) {
                if (!ran) {
                    ran = true;
                    return true;
                }
            }
        }
        return false;
    }
}
