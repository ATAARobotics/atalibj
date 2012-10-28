package org.scriptreader;

import org.scriptreader.blocks.ForLoop;
import org.scriptreader.blocks.IfStatement;
import org.scriptreader.blocks.WhileLoop;
import org.scriptreader.instruction.Declaration;
import org.scriptreader.instruction.Method;

/**
 * Basic statement that does something when used.
 *
 * @author joel
 */
public abstract class Instruction extends Statement {

    /**
     * Hide this method to use the class. <i><b>This method is meant to be
     * hidden by it's subclasses. It is called in a tree-like structure down the
     * children of {@link Statement}.</i></b>
     *
     * @param statement the statement to analyze
     * @return whether it is a valid instruction
     */
    public static boolean isValid(String statement) {
        return statement.endsWith(";") || statement.endsWith("{") || statement.equals("}");
    }

    /**
     * Hide this method to use the class. <i><b>This method is meant to be
     * hidden by it's subclasses. It is called in a tree-like structure down the
     * children of {@link Statement}.</i></b>
     *
     * <p> It is very important to verify that the statement is valid before
     * using this method. If it is not valid, it will throw
     * {@link InvalidStatementException}. </p>
     *
     * @param statement the statement to analyze
     * @return a {@link Statement} object of the type
     * @throws org.reader.Statement.InvalidStatementException
     */
    public static Statement getStatementFrom(String statement) throws InvalidStatementException {
        if (Method.isValid(statement)) {
            return Method.getStatementFrom(statement);
        } else if (IfStatement.isValid(statement)) {
            return IfStatement.getStatementFrom(statement);
        } else if (ForLoop.isValid(statement)) {
            return ForLoop.getStatementFrom(statement);
        } else if (WhileLoop.isValid(statement)) {
            return WhileLoop.getStatementFrom(statement);
        } else if (Declaration.isValid(statement)) {
            return Declaration.getStatementFrom(statement);
        } else if (statement.equals("}")) {
            // Does nothing
            return new Statement();
        } else {
            throw new InvalidStatementException(statement + " is not a recognized instruction.");
        }
    }
    private final String instruction;

    /**
     * Creates instruction by a string.
     *
     * @param instruction full code snippet
     */
    public Instruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * The instruction in the text file.
     *
     * @return instruction as a string
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Method to run the instruction.
     */
    public abstract void run();
}
