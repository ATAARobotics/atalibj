package edu.ata.script.instructions.flow;

import edu.ata.script.Data;
import edu.ata.script.Instruction;
import edu.ata.script.Script;
import edu.ata.script.data.IntegerData;
import edu.ata.script.data.NumberData;
import edu.ata.script.instructions.FlowControl;

/**
 * Basic flow control that runs based on an integer value.
 *
 * @author Joel Gallant
 */
public abstract class IntegerFlow extends FlowControl {

    /**
     * Checks to see if a string is convertible into the instruction type.
     *
     * @param instruction string to convert
     * @return whether it is an instruction
     */
    public static boolean isType(String instruction) {
        String flowStatement = instruction.substring(0, instruction.indexOf('{'));
        String condition = flowStatement.substring(flowStatement.indexOf("(") + 1,
                flowStatement.lastIndexOf(')'));
        return (Data.get(condition) instanceof IntegerData)
                && (ForStatement.isType(instruction));
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
        if (ForStatement.isType(instruction)) {
            return ForStatement.get(instruction);
        } else {
            throw new RuntimeException("Invalid conditioned flow - " + instruction);
        }
    }
    private final String full;

    /**
     * Creates instruction based on its literal string in the code.
     *
     * @param full string in code
     */
    public IntegerFlow(String full) {
        this.full = full;
    }

    /**
     * Runs the statement conditionally relying on
     * {@link IntegerFlow#runAgain(int)}.
     */
    public void run() {
        String flowStatement = full.substring(0, full.indexOf('{'));
        String condition = flowStatement.substring(flowStatement.indexOf("(") + 1,
                flowStatement.lastIndexOf(')'));
        String instructions = full.substring(full.indexOf('{') + 1, full.lastIndexOf('}'));
        while (runAgain(NumberData.intValue(condition))) {
            new Script(instructions).run();
        }
    }

    /**
     * Whether or not the loop should continue.
     *
     * @param argument argument between '(' and ')'
     * @return whether block should run
     */
    protected abstract boolean runAgain(int argument);
}
