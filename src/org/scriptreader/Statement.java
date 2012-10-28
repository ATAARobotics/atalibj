package org.scriptreader;

/**
 * Basic statement. Every line of code is a statement of some kind.
 *
 * @author joel
 */
public class Statement {

    /**
     * How many lines will be skipped after statement is run.
     */
    protected int linesToSkip = 1;
    /**
     * Line of code. <p> Set by script reader. Is guaranteed to be the line of
     * code (By line number / split("\n")). </p>
     */
    protected int line;
    /**
     * Current script the statement is being run in. <p>Set by script
     * reader.</p>
     */
    protected String fullScript;

    /**
     * Hide this method to use the class. <i><b>This method is meant to be
     * hidden by it's subclasses. It is called in a tree-like structure down the
     * children of {@link Statement}.</i></b>
     *
     * @param statement the statement to analyze
     * @return whether it is a valid instruction
     */
    public static boolean isValid(String statement) {
        return true;
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
        // Get rid of errors when tabbing / 4 spacing
        statement = statement.trim();
        if (Instruction.isValid(statement)) {
            return Instruction.getStatementFrom(statement);
        } else {
            throw new InvalidStatementException(statement + " is not a statement type.");
        }
    }

    /**
     * Class utility method to determine how many lines exist between the
     * current line of code and the next break.
     *
     * <p>Breaks are defined by '}'</p>
     *
     * @param script full script
     * @param currentLine the line to start from
     * @return how many lines it is from the current line to the next break
     * statement ('}')
     */
    public static int linesUntilBreak(String script, int currentLine) {
        String[] s = StringUtils.split(script, ScriptReader.LINE_SEPARATOR);
        for (int x = currentLine; x < s.length; x++) {
            if (StringUtils.isEmpty(s[x]) || s[x].startsWith("**")) {
                // This eleminates errors / comments being processed
                continue;
            }

            if (s[x].startsWith("}")) {
                return x - currentLine;
            }
        }
        return -1;
    }
}
