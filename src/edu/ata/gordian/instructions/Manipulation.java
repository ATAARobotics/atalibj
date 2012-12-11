package edu.ata.gordian.instructions;

import edu.ata.gordian.Instruction;

/**
 * Instruction that can change the value of a variable without using var = new
 * value.
 *
 * @author Joel Gallant
 */
public class Manipulation extends Instruction {

    /**
     * Checks to see if a string is convertible into the instruction type.
     *
     * @param instruction string to convert
     * @return whether it is an instruction
     */
    public static boolean isType(String instruction) {
        return instruction.indexOf("++") >= 0 || instruction.indexOf("--") >= 0;
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
        return new Manipulation(instruction);
    }
    private final Declaration declaration;

    /**
     * Creates manipulation and converts it into {@link Declaration} form.
     *
     * @param instruction literal string of manipulation
     */
    public Manipulation(String instruction) {
        if (instruction.indexOf("++") >= 0) {
            String name = instruction.substring(0, instruction.indexOf("++"));
            instruction = name + "=" + name + "+1";
        } else if (instruction.indexOf("--") >= 0) {
            String name = instruction.substring(0, instruction.indexOf("--"));
            instruction = name + "=" + name + "-1";
        }
        this.declaration = (Declaration) Declaration.get(instruction);
    }

    /**
     * Manipulates the variable.
     */
    public void run() {
        declaration.run();
    }
}