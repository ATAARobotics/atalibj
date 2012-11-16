package edu.ata.driving.modules;

import java.util.Hashtable;
import java.util.Random;

/**
 * Base class used to represent all elements of the robot. Whether it be an arm,
 * a shooter, a sensor or a motor, it is a functioning part of the robot.
 *
 * <p> Modules can be enabled, and optionally given the ability to be disabled.
 * This provides flexibility of implementation of modules. Control of a module
 * will, by convention, enable modules. There are many possibilities of how to
 * use modules, and how they should behave.
 *
 * <p> All modules use identifiers, and are stored within
 * {@link Module#MODULES}. This identifier is used to compare modules in
 * {@link Object#equals(java.lang.Object)}. It is a completely randomly
 * generated 16 character string that uses {@link RandomString} to generate
 * itself. It is guaranteed to never produce the same string twice.
 *
 * <p> The reason for this identifier to exist while hash codes exist already is
 * to do three things.
 *
 * <p>1. To provide a far for accurate way to guarantee things are not the same.
 *
 * <p> 2. To eliminate the link between an object and its identifier. The two
 * things are by definition completely separate (Unlike hash codes).
 *
 * <p> 3. To provide a way to access modules easily in {@link Module#MODULES}.
 *
 * <p> This makes it so that module identifiers have the same relation to one
 * another as any other. (In A, B, C, {@code A vs C} is the same as
 * {@code B vs A} and {@code A vs B}, etc.)
 *
 * <p> {@code (new Module("Hello")).equals(new Module("Hello")) != true}
 *
 * @author Joel Gallant
 */
public abstract class Module {

    private static final RandomString RANDOM_STRING = new RandomString(16);
    /**
     * {@link Hashtable} object that stores all instantiated
     * {@link Module Modules}. Uses their identifier as the key, and their name
     * as the value. This table is mostly for use for telling the user what
     * modules exist in the code. Is useful to tell whether multiples of one
     * module have accidentally been made (Seeing as they are different in their
     * identifier).
     *
     * <p> Take caution in using this field. Removing elements has been
     * disabled.
     */
    public static final Hashtable MODULES = new Hashtable() {
        /**
         * <b>READ ONLY. DOES NOT REMOVE ELEMENT.</b>
         */
        public synchronized Object remove(Object key) {
            return key;
        }
    };
    private final String name;
    private final String identifier;

    /**
     * Creates a new module, with it's name stated. A name is a unique
     * identifier to the user of what the module actually does. Sometimes, it is
     * perfectly acceptable to use the same name twice, but this has a
     * consequence when debugging. It will be hard to identify what the module
     * is, and what it does. It's identifier is only good for knowing that
     * modules are different.
     *
     * <p> The module's identifier is done in the constructor of {@link Module}.
     * It would be programmatically unsafe to allow construction of the
     * identifier anywhere else.
     *
     * <p> The identifier is unique, and will not match any other identifiers.
     *
     * @param name the user identifiable name of the module (eg. "left back
     * motor", "main robot drivetrain")
     */
    public Module(String name) {
        this.name = name;
        String id = RANDOM_STRING.nextString();
        while (MODULES.containsKey(id)) {
            id = RANDOM_STRING.nextString();
        }
        this.identifier = id;
        MODULES.put(identifier, name);
    }

    /**
     * Returns the name given to the module. A name cannot be changed.
     *
     * @return the user identifiable name of the module (eg. "left back motor",
     * "main robot drivetrain")
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the unique identifier of the module.
     *
     * <p> All modules use identifiers, and are stored within
     * {@link Module#MODULES}. This identifier is used to compare modules in
     * {@link Object#equals(java.lang.Object)}. It is a completely randomly
     * generated 16 character string that uses {@link RandomString} to generate
     * itself. It is guaranteed to never produce the same string twice.
     *
     * <p> The reason for this identifier to exist while hash codes exist
     * already is to do three things.
     *
     * <p>1. To provide a far for accurate way to guarantee things are not the
     * same.
     *
     * <p> 2. To eliminate the link between an object and its identifier. The
     * two things are by definition completely separate (Unlike hash codes).
     *
     * <p> 3. To provide a way to access modules easily in
     * {@link Module#MODULES}.
     *
     * <p> This makes it so that module identifiers have the same relation to
     * one another as any other. (In A, B, C, {@code A vs C} is the same as
     * {@code B vs A} and {@code A vs B}, etc.)
     *
     * <p> {@code (new Module("Hello")).equals(new Module("Hello")) != true}
     *
     * @return 16 character identifier of the module
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Enables the module. This could mean many things, depending on the
     * implementation. A good rule of thumb for implementation is 'turning on',
     * or starting its service.
     */
    public abstract void enable();

    /**
     * Checks if the module has been enabled. This has to do with the
     * {@link Module#enable()} method, although it is not explicitly so. This
     * method is implementation dependent.
     *
     * <p> Typically, {@code isEnabled()} should return whether or not the
     * module is 'useable' (Can be used).
     *
     * @return whether the module is enabled
     */
    public abstract boolean isEnabled();

    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.identifier != null ? this.identifier.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Module) && ((Module) obj).identifier.equals(this.identifier);
    }

    /**
     * Generator for a random string. Is <b>not</b> guaranteed to produce a
     * completely new sequence.
     *
     * <p> Is not secure, and should <i>only</i> be used to produce identifiers
     * for {@link Module Modules}.
     */
    private static final class RandomString {

        private static final char[] symbols = new char[36];

        static {
            for (int x = 0; x < 10; ++x) {
                symbols[x] = (char) ('0' + x);
            }
            for (int x = 10; x < 36; ++x) {
                symbols[x] = (char) ('a' + x - 10);
            }
        }
        private final Random random = new Random();
        private final char[] buf;

        /**
         * Creates a new {@link RandomString} object. Checks for the length to
         * be less than 1.
         *
         * @param length length of the string
         */
        public RandomString(int length) {
            if (length < 1) {
                throw new IllegalArgumentException("length < 1: " + length);
            }
            buf = new char[length];
        }

        /**
         * Produces a random string with the length given containing characters
         * and numbers. Is cheap and non-secure.
         *
         * @return new random string
         */
        public String nextString() {
            for (int x = 0; x < buf.length; ++x) {
                buf[x] = symbols[random.nextInt(symbols.length)];
            }
            return new String(buf);
        }
    }
}