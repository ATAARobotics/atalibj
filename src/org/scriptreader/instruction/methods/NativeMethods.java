package org.scriptreader.instruction.methods;

import org.scriptreader.Value;
import org.scriptreader.instruction.Method;

/**
 * Cache for methods that are default to the language.
 *
 * @author joel
 */
public class NativeMethods {

    /**
     * Gets the method based on it's name and includes it's arguments.
     *
     * @param method method name
     * @param args arguments
     * @return method based on method name
     * @throws Exception thrown when method does not exist
     */
    public static Method getMethod(String method, Value[] args) throws Exception {
        if (method.equals("PRINT")) {
            return new PRINT(args);
        } else if (method.equals("PRINTLITERAL")) {
            return new PRINTLITERAL(args);
        } else if (method.equals("WAIT")) {
            return new WAIT(args);
        } else {
            throw new Exception("No native method " + method);
        }
    }

    /**
     * Printing method. Can use variables and single argument methods. (See spec
     * for details about multiple argument methods inside methods)
     */
    public static class PRINT extends Method {

        /**
         * Creates the method based on it's arguments.
         *
         * @param args message to print
         */
        public PRINT(Value[] args) {
            super("PRINT", args);
        }

        public void run(Value[] args) {
            for (int x = 0; x < args.length; x++) {
                System.out.print(args[x]);
            }
            System.out.print("\n");
        }
    }

    /**
     * Clone of printing method that prints exactly what it is given, rather
     * than it's programmatically equivalent value.
     */
    public static class PRINTLITERAL extends Method {

        /**
         * Creates the method based on it's arguments.
         *
         * @param values message to print literally
         */
        public PRINTLITERAL(Value[] values) {
            super("PRINTLITERAL", values);
        }

        public void run(Value[] args) {
            String full = "";
            for (int x = 0; x < args.length; x++) {
                full += args[x].getLiteralValue() + (x == args.length - 1 ? "" : ",");
            }
            System.out.println(full);
        }
    }

    /**
     * Basic sleeping method. Uses internal {@link Thread#sleep(long)} to wait.
     */
    public static class WAIT extends Method {

        /**
         * Creates method based on it's arguments. Requires 1 number argument.
         *
         * @param args arguments.
         */
        public WAIT(Value[] args) {
            super("WAIT", args);
        }

        public void run(Value[] args) {
            try {
                Thread.sleep(Long.parseLong(args[0].getValue().toString()));
            } catch (InterruptedException ex) {
                System.err.println("Error occured while waiting : " + ex.getMessage());
            }
        }
    }
}
