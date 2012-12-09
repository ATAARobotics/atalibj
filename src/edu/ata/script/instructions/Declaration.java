package edu.ata.script.instructions;

import edu.ata.script.Data;
import edu.ata.script.Instruction;
import edu.ata.script.StringUtils;

/**
 * Instruction that assigns a variable name to a specified value (in
 * {@link Data} form). Interfaces with {@link Data#DATA_STORAGE}.
 *
 * @author Joel Gallant
 */
public class Declaration extends Instruction {

    /**
     * Checks to see if a string is convertible into the instruction type.
     *
     * @param instruction string to convert
     * @return whether it is an instruction
     */
    public static boolean isType(String instruction) {
        return StringUtils.contains(instruction, "=");
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
        return new Declaration(instruction);
    }
    private final String instruction;

    /**
     * Creates declaration using the literal string as it appears in the code.
     *
     * @param instruction string in code
     */
    public Declaration(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Assigns the variable name to the value after "=". Stores as {@link Data}.
     */
    public void run() {
        Data.DATA_STORAGE.addData(instruction.substring(0, instruction.indexOf('=')).trim(),
                Data.get(instruction.substring(instruction.indexOf('=') + 1).trim()));
    }
}
