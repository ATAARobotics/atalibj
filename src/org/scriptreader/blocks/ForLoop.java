package org.scriptreader.blocks;

import org.scriptreader.Instruction;
import org.scriptreader.InvalidStatementException;
import org.scriptreader.ScriptReader;
import org.scriptreader.Statement;
import org.scriptreader.StringUtils;
import org.scriptreader.Value;

/**
 * FOR loop class. Pretty self-explanatory.
 *
 * @author joel
 */
public class ForLoop extends Instruction {

    /**
     * Hide this method to use the class. <i><b>This method is meant to be
     * hidden by it's subclasses. It is called in a tree-like structure down the
     * children of {@link Statement}.</i></b>
     *
     * @param statement the statement to analyze
     * @return whether it is a valid instruction
     */
    public static boolean isValid(String statement) {
        if (!(StringUtils.contains(statement, "(") && StringUtils.contains(statement, ")"))) {
            return false;
        }
        return statement.startsWith("FOR(") && (statement.endsWith(")") || statement.endsWith("{"));
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
        return new ForLoop(new Value(statement.substring(statement.indexOf("(") + 1, statement.indexOf(")"))));
    }
    /**
     * The amount of times to repeat the loop.
     */
    public final int repititions;

    /**
     * Creates a for loop based on one value. Has to be an integer.
     *
     * @param value amount of times to loop
     */
    public ForLoop(Value value) {
        this(Integer.parseInt(value.getValue().toString()));
    }

    /**
     * Creates a for loop that loops a certain amount of time.
     *
     * @param times amount of times to loop
     */
    public ForLoop(int times) {
        super("FOR(" + ")");
        this.repititions = times;
    }

    public void run() {
        this.linesToSkip = linesUntilBreak(fullScript, line);
        String[] instructions = StringUtils.split(fullScript, ScriptReader.LINE_SEPARATOR);
        String forLoop = "";
        int currentLine = line;
        for (int x = 0; x < linesUntilBreak(fullScript, line); x++) {
            forLoop += instructions[++currentLine] + ScriptReader.LINE_SEPARATOR;
        }
        for (int x = 0; x < repititions; x++) {
            ScriptReader.runFullScript(forLoop);
        }
    }
}
