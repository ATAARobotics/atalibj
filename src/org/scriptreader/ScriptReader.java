package org.scriptreader;

/**
 * Main reader of the script. Uses {@code runScript} as a class method to run a
 * string.
 *
 * @author joel
 */
public class ScriptReader {

    public static final char LINE_SEPARATOR = '\n';

    /**
     * Runs a script. Instructions are split by line separators.
     *
     * @param fullScript string of the full script
     */
    public static void runScript(String fullScript) {
        int line = 0;
        String[] s = StringUtils.split(fullScript, LINE_SEPARATOR);
        while (line < s.length) {
            if (StringUtils.isEmpty(s[line].trim()) || s[line].trim().startsWith("**")) {
                // This eleminates errors / comments being processed
                line++;
                continue;
            }
            try {
                if (StringUtils.contains(s[line], "**")) {
                    s[line] = s[line].substring(0, s[line].indexOf("**"));
                }
                Statement currentLine = Statement.getStatementFrom(s[line].trim());
                currentLine.line = line;
                currentLine.fullScript = fullScript;
                if (currentLine instanceof Instruction) {
                    ((Instruction) currentLine).run();
                }
                line += currentLine.linesToSkip;
            } catch (InvalidStatementException ex) {
                System.err.println(ex.getMessage());
                line++;
            }
        }
    }
}