package org.scriptreader.instruction;

import java.util.Hashtable;
import org.scriptreader.Value;

/**
 * Static class that stores all returnable methods including native methods.
 *
 * @author joel
 */
public class Returnables {

    private static final Hashtable returnables = new Hashtable();

    static {
        add(new MathMethod("SUM") {
            public double doCalc(double num1, double num2) {
                return num1 + num2;
            }
        });
        add(new MathMethod("SUBTRACT") {
            public double doCalc(double num1, double num2) {
                return num1 - num2;
            }
        });
        add(new MathMethod("MULTIPLY") {
            public double doCalc(double num1, double num2) {
                return num1 * num2;
            }
        });
        add(new MathMethod("DIVIDE") {
            public double doCalc(double num1, double num2) {
                return num1 / num2;
            }
        });
    }

    /**
     * Adds a returnable object to the cache.
     *
     * @param returnable returnable to add
     */
    public static void add(Returnable returnable) {
        returnables.put(returnable.getName(), returnable);
    }

    /**
     * Returns whether the cache contains a method
     *
     * @param methodName name of the method
     * @return whether cache contains method
     */
    public static boolean contains(String methodName) {
        return returnables.containsKey(methodName);
    }

    /**
     * Returns the returnable method based on it's name.
     *
     * @param methodName name of the method
     * @return returnable
     */
    public static Returnable get(String methodName) {
        return (Returnable) returnables.get(methodName);
    }

    /**
     * Returns the value of the returnable based solely on it's method name. Is
     * not checked. (Use {@link Returnables#isValid(java.lang.String)} to check.
     *
     * @param method method name
     * @return value returned by the method
     */
    public static Object getFromMethod(String method) {
        String[] args = Method.getArguments(method);
        Value[] trueArgs = new Value[args.length];
        for (int x = 0; x < args.length; x++) {
            trueArgs[x] = new Value(args[x].trim());
        }
        return get(Method.getMethodName(method)).getValue(trueArgs);
    }

    /**
     * Returns whether the method is stored in the cache.
     *
     * @param value method name
     * @return whether the method stored
     */
    public static boolean isValid(String value) {
        return contains(Method.getMethodName(value));
    }

    /**
     * Returnable method. Returns a value based on its arguments.
     */
    public static interface Returnable {

        /**
         * Returns the name of the method. Used as Name(Args);
         *
         * @return name of the method
         */
        public abstract String getName();

        /**
         * Returns the value based on its arguments.
         *
         * @param args arguments given
         * @return value
         */
        public abstract Object getValue(Value[] args);
    }

    private static abstract class MathMethod implements Returnable {

        public final String name;

        public MathMethod(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Object getValue(Value[] args) {
            double value;
            int start = 0;
            try {
                value = Double.parseDouble(args[0].toString());
                start = 1;
            } catch (Exception ex) {
                value = 0;
            }
            for (int x = start; x < args.length; x++) {
                if (Value.isDouble(args[x].toString())) {
                    value = doCalc(value, Double.parseDouble(args[x].toString()));
                }
            }
            return new Double(value);
        }

        public abstract double doCalc(double num1, double num2);
    }
}
