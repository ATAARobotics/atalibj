package edu.ata.automation.driving.modules;

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
 * {@link Object#equals(java.lang.Object)}. It is a completely randomly created
 * 16 character string that uses {@link RandomString} to generate itself. It can
 * pretty much be guaranteed to never produce the same string twice, although it
 * is completely impossible to ensure that is the case.
 *
 * <p> The odds of two identifiers being the same are as follows :
 *
 * <pre> 1 / ( 36 ^ #length# ) </pre>
 *
 * <p> The {@link Module} class uses 16 characters, making the odds 1 / (7.96 x
 * 10^24). Otherwise known as completely insane.
 *
 * @author Joel Gallant
 */
public abstract class Module {

    /**
     * {@link Hashtable} object that stores all instantiated
     * {@link Module Modules}. Uses their identifier as the key, and their name
     * as the value. This table is mostly for use for telling the user what
     * modules exist in the code. Is useful to tell whether multiples of one
     * module have accidentally been made (Seeing as they are different in their
     * identifier).
     *
     * <p> Take caution in using this field. Removing elements from the table
     * could cause bugs elsewhere. If this could be <i>read-only</i>, it would
     * be. Treat it as such.
     */
    public final Hashtable MODULES = new Hashtable();
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
     * <p> The module's identifier is donew in the constructor of
     * {@link Module}. It would be programmatically unsafe to allow construction
     * of the identifier anywhere else.
     *
     * @param name the user identifiable name of the module (eg. "left back
     * motor")
     */
    public Module(String name) {
        this.name = name;
        this.identifier = new RandomString(16).nextString();
        MODULES.put(identifier, name);
    }

    /**
     * Returns the name given to the module. A name cannot be changed.
     *
     * @return the user identifiable name of the module (eg. "left back motor")
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the unique identifier of the module.
     *
     * <p> The odds of two identifiers being the same are as follows :
     *
     * <pre> 1 / ( 36 ^ #length# ) </pre>
     *
     * <p> The {@link Module} class uses 16 characters, making the odds 1 /
     * (7.96 x 10^24). Otherwise known as completely insane.
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

    /**
     * Returns a hash code value for the object. This method is supported for
     * the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}. <p> The general contract of {@code hashCode}
     * is: <ul> <li>Whenever it is invoked on the same object more than once
     * during an execution of a Java application, the {@code hashCode} method
     * must consistently return the same integer, provided no information used
     * in {@code equals} comparisons on the object is modified. This integer
     * need not remain consistent from one execution of an application to
     * another execution of the same application. <li>If two objects are equal
     * according to the {@code equals(Object)} method, then calling the
     * {@code hashCode} method on each of the two objects must produce the same
     * integer result. <li>It is <em>not</em> required that if two objects are
     * unequal according to the
     * {@link java.lang.Object#equals(java.lang.Object)} method, then calling
     * the {@code hashCode} method on each of the two objects must produce
     * distinct integer results. However, the programmer should be aware that
     * producing distinct integer results for unequal objects may improve the
     * performance of hash tables. </ul> <p> As much as is reasonably practical,
     * the hashCode method defined by class {@code Object} does return distinct
     * integers for distinct objects. (This is typically implemented by
     * converting the internal address of the object into an integer, but this
     * implementation technique is not required by the Java<font
     * size="-2"><sup>TM</sup></font> programming language.)
     *
     * @return a hash code value for this object.
     * @see java.lang.Object#equals(java.lang.Object)
     * @see java.lang.System#identityHashCode
     */
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.identifier != null ? this.identifier.hashCode() : 0);
        return hash;
    }

    /**
     * Indicates whether some other object is "equal to" this one. <p> The
     * {@code equals} method implements an equivalence relation on non-null
     * object references: <ul> <li>It is <i>reflexive</i>: for any non-null
     * reference value {@code x}, {@code x.equals(x)} should return
     * {@code true}. <li>It is <i>symmetric</i>: for any non-null reference
     * values {@code x} and {@code y}, {@code x.equals(y)} should return
     * {@code true} if and only if {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * {@code x}, {@code y}, and {@code z}, if {@code x.equals(y)} returns
     * {@code true} and {@code y.equals(z)} returns {@code true}, then
     * {@code x.equals(z)} should return {@code true}. <li>It is
     * <i>consistent</i>: for any non-null reference values {@code x} and
     * {@code y}, multiple invocations of {@code x.equals(y)} consistently
     * return {@code true} or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the objects is
     * modified. <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}. </ul> <p> The
     * {@code equals} method for class {@code Object} implements the most
     * discriminating possible equivalence relation on objects; that is, for any
     * non-null reference values {@code x} and {@code y}, this method returns
     * {@code true} if and only if {@code x} and {@code y} refer to the same
     * object ({@code x == y} has the value {@code true}). <p> Note that it is
     * generally necessary to override the {@code hashCode} method whenever this
     * method is overridden, so as to maintain the general contract for the
     * {@code hashCode} method, which states that equal objects must have equal
     * hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument;
     * {@code false} otherwise.
     * @see #hashCode()
     * @see java.util.HashMap
     */
    public boolean equals(Object obj) {
        return obj instanceof Module && ((Module) obj).identifier.equals(this.identifier);
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
         * and numbers. Is cheap and secure.
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