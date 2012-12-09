package edu.ata.script.instructions.flow;

import edu.ata.script.Instruction;

/**
 * Instruction that runs a certain amount of times.
 *
 * @author Joel Gallant
 */
public class ForStatement extends IntegerFlow {

    /**
     * Checks to see if a string is convertible into the instruction type.
     *
     * @param instruction string to convert
     * @return whether it is an instruction
     */
    public static boolean isType(String instruction) {
        return instruction.trim().startsWith("for(");
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
        return new ForStatement(instruction);
    }
    private int count = 0;

    /**
     * Creates statement based on the string found in the code.
     *
     * @param full string in code
     */
    public ForStatement(String full) {
        super(full);
    }

    /**
     * Checks to see whether the loop should be run again.
     *
     * @param argument argument between '(' and ')'.
     * @return whether it has been run as many times as the argument
     */
    protected boolean runAgain(int argument) {
        return ++count <= argument;
    }
}
