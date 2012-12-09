package edu.ata.script.instructions.flow;

import edu.ata.script.Data;
import edu.ata.script.Instruction;
import edu.ata.script.Script;
import edu.ata.script.data.BooleanData;
import edu.ata.script.instructions.FlowControl;

/**
 * Block that runs conditionally. Will run based on the condition between '('
 * and ')'.
 *
 * @author Joel Gallant
 */
public abstract class ConditionedFlow extends FlowControl {

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
        return (Data.get(condition) instanceof BooleanData)
                && (IfStatement.isType(instruction)
                || WhileStatement.isType(instruction));
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
        if (IfStatement.isType(instruction)) {
            return IfStatement.get(instruction);
        } else if (WhileStatement.isType(instruction)) {
            return WhileStatement.get(instruction);
        } else {
            throw new RuntimeException("Invalid conditioned flow - " + instruction);
        }
    }
    private final String full;

    /**
     * Creates instruction based on the string in the code.
     *
     * @param full instruction in code
     */
    protected ConditionedFlow(String full) {
        this.full = full;
    }

    /**
     * Abstract method that checks whether or not to run the block again,
     * depending on the argument.
     *
     * @param args literal argument between '(' and ')'.
     * @return whether loop should continue
     */
    protected abstract boolean runAgain(String args);

    /**
     * Runs the block conditionally depending on
     * {@link ConditionedFlow#runAgain(java.lang.String)}.
     */
    public void run() {
        String flowStatement = full.substring(0, full.indexOf('{'));
        String condition = flowStatement.substring(flowStatement.indexOf("(") + 1,
                flowStatement.lastIndexOf(')'));
        while (runAgain(condition)) {
            new Script(full.substring(full.indexOf('{') + 1, full.lastIndexOf('}'))).run();
        }
    }
}
