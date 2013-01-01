package ATA.gordian.instructions;

import ATA.gordian.Instruction;
import ATA.gordian.instructions.flow.ConditionedFlow;
import ATA.gordian.instructions.flow.IntegerFlow;

/**
 * Instruction that changes flow of the program. Is identified with '{' and '}'.
 *
 * @author Joel Gallant
 */
public abstract class FlowControl extends Instruction {

    /**
     * Checks to see if a string is convertible into the instruction type.
     *
     * @param instruction string to convert
     * @return whether it is an instruction
     */
    public static boolean isType(String instruction) {
        if (instruction.indexOf("{") < 0 || !instruction.endsWith("};")) {
            return false;
        }
        return ConditionedFlow.isType(instruction)
                || IntegerFlow.isType(instruction);
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
        if (ConditionedFlow.isType(instruction)) {
            return ConditionedFlow.get(instruction);
        } else if (IntegerFlow.isType(instruction)) {
            return IntegerFlow.get(instruction);
        } else {
            throw new RuntimeException("Not flow control - " + instruction);
        }
    }
}
