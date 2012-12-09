package edu.ata.script.instructions;

import edu.ata.script.Data;
import edu.ata.script.Instruction;
import edu.ata.script.StringUtils;
import edu.ata.script.storage.Methods;

/**
 * Instruction callable with a '(' and ')' with some or no arguments inside.
 * Uses {@link Methods#METHODS_STORAGE} to store methods.
 *
 * @author Joel Gallant
 */
public class Method extends Instruction {

    /**
     * Checks to see if a string is convertible into the instruction type.
     *
     * @param instruction string to convert
     * @return whether it is an instruction
     */
    public static boolean isType(String instruction) {
        if (!StringUtils.contains(instruction, "(") || !StringUtils.contains(instruction, ")")) {
            return false;
        }
        return Methods.METHODS_STORAGE.contains(instruction.substring(0, instruction.indexOf('(')));
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
        return new Method(instruction);
    }
    private final String method;
    private final String[] args;

    /**
     * Creates method based on its literal string in the code. Separates
     * arguments by ','.
     *
     * @param method string as is in the code
     */
    public Method(String method) {
        this.method = method.substring(0, method.indexOf('('));
        this.args = StringUtils.split(method.substring(method.indexOf('(') + 1,
                method.lastIndexOf(')')), ',');
    }

    /**
     * Runs the method. Evaluates the values on the arguments here.
     */
    public void run() {
        Data[] arguments = new Data[this.args.length];
        for (int x = 0; x < arguments.length; x++) {
            arguments[x] = Data.get(args[x]);
        }
        ((MethodBody) Methods.METHODS_STORAGE.get(method)).run(arguments);
    }
}
