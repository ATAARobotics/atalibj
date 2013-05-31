package edu.first.util;

/**
 * The base class for enums. This class is not meant to be instantiated.
 *
 * The correct way to implement this class goes as follows:
 * <pre>
 * public final class Colours extends Enum {
 *     public static final Colours INSTANCE = new Colours();
 *     public static final Value YELLOW = INSTANCE.generate("YELLOW");
 *     public static final Value ORANGE = INSTANCE.generate("ORANGE");
 *     public static final Value BLUE = INSTANCE.generate("BLUE");
 *     public static final Value RED = INSTANCE.generate("RED");
 * }
 * </pre> 
 * 
 * In every official enum, use this pattern:
 * <ul>
 * <li> Extend {@link Enum}
 * <li> Make class final
 * <li> Create <b>one</b> static final instance of the class, allow public
 * access to it
 * <li> For every value in the enum, declare it as
 * <p> {@code public static final Value NAME = INSTANCE_FIELD.generate("NAME");}
 * </ul>
 *
 * All enum values are bound to their "creator" {@code Enum}. The only way to
 * create a value is {@link Enum#generate(java.lang.String)}.
 *
 * Enum-type methods are available through the {@link Enum} instance.
 *
 * @since May 12 13
 * @author Joel Gallant
 */
public class Enum {

    // Official array of enum values - is not thread safe, generating while accessing is bad
    private Value[] values = new Value[0];
    private int size = 0;

    /**
     * Constructs the enum instance.
     */
    protected Enum() {
    }

    /**
     * Generates an enum value that is bound to this class.
     *
     * @param name the identifier of the value - usually the same as declared
     * field
     * @return unique value bound to this class
     */
    protected final Value generate(String name) {
        Value v = new Value(name);

        Value[] buf = values;
        values = new Value[size];
        System.arraycopy(buf, 0, values, 0, size - 1);
        values[size - 1] = v;

        return v;
    }

    /**
     * Returns the amount of values inside of the enum.
     *
     * @return amount of values in enum
     */
    public final int size() {
        return size;
    }

    /**
     * Returns the {@link Value} inside of the enum with the same name as given.
     * If no values match the name, this returns null.
     *
     * @param name the name of the value in the enum
     * @return value representation of name
     */
    public final Value valueOf(String name) {
        for (int x = 0; x < size; x++) {
            if (values[x].name.equals(name)) {
                return values[x];
            }
        }
        return null;
    }

    /**
     * Returns the values that are part of the enum. This array can be broken if
     * this method is called at the same time as
     * {@link Enum#generate(java.lang.String)}.
     *
     * @return array of the values in the enum
     */
    public final Value[] getValues() {
        return values;
    }

    /**
     * The representation of values inside of an enum. All instances of
     * {@code Value} are created in {@link Enum#generate(java.lang.String)}.
     * Every instance of {@code Value} will not be equal to any other instance.
     *
     * @since May 12 13
     * @author Joel Gallant
     */
    public final class Value {

        private final String name;
        private final Class parent;
        private final int v;

        private Value(String name) {
            this.name = name;
            // Uses enum instance for parent
            parent = Enum.this.getClass();
            v = ++size;
        }
        
        /**
         * Returns the {@link Enum} parent that this value is bounded to.
         * 
         * @return the enum this value is in
         */
        public Enum getParent() {
            return Enum.this;
        }

        /**
         * {@inheritDoc}
         */
        public int hashCode() {
            int hash = 5;
            hash = 47 * hash + (this.parent != null ? this.parent.hashCode() : 0);
            hash = 47 * hash + this.v;
            return hash;
        }

        /**
         * Returns whether or not the value is the same instance.
         *
         * @param obj the reference object with which to compare
         * @return true if this object is the same as the obj argument; false
         * otherwise.
         */
        public boolean equals(Object obj) {
            if (obj instanceof Value) {
                return parent.equals(((Value) obj).parent) && v == ((Value) obj).v;
            } else {
                return false;
            }
        }

        /**
         * Returns the name of the value.
         *
         * @return identifier given in {@code generate(name)}
         */
        public String getName() {
            return name;
        }

        /**
         * Returns the name of the value.
         *
         * @return identifier given in {@code generate(name)}
         */
        public String toString() {
            return name;
        }
    }
}
