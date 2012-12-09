package edu.ata.script;

import edu.ata.script.instructions.Declaration;
import edu.ata.script.instructions.FlowControl;
import edu.ata.script.instructions.Manipulation;
import edu.ata.script.instructions.Method;

/**
 * Basic framework for code that "does something". No real qualifications apart
 * from doing something.
 *
 * @author Joel Gallant
 */
public abstract class Instruction {

    /**
     * Checks to see if a string is convertible into the instruction type.
     *
     * @param instruction string to convert
     * @return whether it is an instruction
     */
    public static boolean isType(String instruction) {
        instruction = instruction.trim();
        return FlowControl.isType(instruction)
                || Method.isType(instruction)
                || Declaration.isType(instruction)
                || Manipulation.isType(instruction);
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
        instruction = instruction.trim();
        if (FlowControl.isType(instruction)) {
            return FlowControl.get(instruction);
        } else if (Method.isType(instruction)) {
            return Method.get(instruction);
        } else if (Declaration.isType(instruction)) {
            return Declaration.get(instruction);
        } else if (Manipulation.isType(instruction)) {
            return Manipulation.get(instruction);
        } else {
            throw new RuntimeException("Invalid instruction - " + instruction);
        }
    }

    /**
     * Does whatever the instruction does. Has no qualifications.
     */
    public abstract void run();
}
