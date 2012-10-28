package org.scriptreader.instruction;

import org.scriptreader.Instruction;
import org.scriptreader.InvalidStatementException;
import org.scriptreader.Statement;
import org.scriptreader.StringUtils;
import org.scriptreader.Value;
import org.scriptreader.Variables;
import org.scriptreader.blocks.IfStatement;
import org.scriptreader.blocks.WhileLoop;

/**
 * The declaration of variables.
 *
 * @author joel
 */
public class Declaration extends Instruction {

    /**
     * Hide this method to use the class. <i><b>This method is meant to be
     * hidden by it's subclasses. It is called in a tree-like structure down the
     * children of {@link Statement}.</i></b>
     *
     * @param statement the statement to analyze
     * @return whether it is a valid instruction
     */
    public static boolean isValid(String statement) {
        // Ensures first = sign is not == sign
        return StringUtils.contains(statement, "=") && statement.endsWith(";") && statement.charAt(statement.indexOf("=") + 1) != '='
                // Cannot be a conditional instruction
                && !IfStatement.isValid(statement) && !WhileLoop.isValid(statement);
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
     * @throws InvalidStatementException thrown when statement is unrecognizable
     */
    public static Statement getStatementFrom(String statement) throws InvalidStatementException {
        return new Declaration(statement.substring(0, statement.indexOf("=")).trim(),
                new Value(StringUtils.replace(statement.substring(statement.indexOf("=") + 1), ';', "").trim()));
    }
    private final String varName;
    private final Value value;

    /**
     * Creates the declaration by it's value and name.
     *
     * @param varName name of the variable
     * @param value value of the variable
     */
    public Declaration(String varName, Value value) {
        super(varName + " = " + value);
        this.varName = varName;
        this.value = value;
    }

    public void run() {
        if (Variables.contains(varName)) {
            Variables.set(varName, value);
        } else {
            Variables.add(varName, value);
        }
    }
}
