package edu.ata.script;

import com.sun.squawk.util.StringTokenizer;
import java.util.Vector;

/**
 * Main class that converts a string (usually taken from a file) into a runnable
 * script.
 *
 * Details on how to write code are available on github.
 *
 * @author Joel Gallant
 */
public class Script {

    static {
        // Add all robot specific methods, returning methods, etc. HERE!
    }
    
    /**
     * Should be used every time you want to run a script that is separate from
     * all other scripts. Removes all elements of data storage before running
     * (does not remove methods / returning methods).
     *
     * @param full full script
     */
    public static void run(String full) {
        Data.DATA_STORAGE.clear();
        new Script(full).run();
    }
    private final String[] parts;
    private String block = "";

    /**
     * Creates a script object that is ready to run. <i> Does not </i> compile
     * the program. Compiling is done dynamically (while running). The
     * constructor is used to separate parts of the script.
     *
     * @param full full script
     */
    public Script(String full) {
        StringTokenizer tokenizer = new StringTokenizer(full, ";\n\r");
        Vector parts = new Vector();
        int bracesCount = 0;
        while (tokenizer.hasMoreTokens()) {
            String next = tokenizer.nextToken().trim();
            if (next.startsWith("#")) {
                continue;
            }
            if (StringUtils.contains(next, "{")) {
                bracesCount++;
                addToBlock(next);
            } else if (StringUtils.contains(next, "}")) {
                if (--bracesCount == 0) {
                    parts.addElement(block + next + ";");
                    block = "";
                } else {
                    addToBlock(next);
                }
            } else if (block.length() > 0) {
                addToBlock(next);
            } else {
                parts.addElement(next);
            }
        }
        this.parts = new String[parts.size()];
        for (int x = 0; x < parts.size(); x++) {
            this.parts[x] = ((String) parts.elementAt(x)).trim();
        }
    }

    /**
     * Returns all of the separate parts of the script. Parts are either methods
     * or blocks.
     *
     * @return array of parts
     */
    public String[] getParts() {
        return parts;
    }

    /**
     * Runs the script.
     * 
     * @see Script#run(java.lang.String)
     */
    public void run() {
        for (int x = 0; x < parts.length; x++) {
            try {
                if (Instruction.isType(parts[x])) {
                    Instruction.get(parts[x]).run();
                } else {
                    System.err.println("Part " + (x + 1) + " is not an instruction - "
                            + parts[x]);
                }
            } catch (Throwable ex) {
                System.err.println("Error occured in part " + (x + 1) + " - "
                        + parts[x] + "\n\t" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private void addToBlock(String instruction) {
        block += (instruction + ";");
    }
}
