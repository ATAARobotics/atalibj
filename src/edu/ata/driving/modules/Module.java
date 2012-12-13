package edu.ata.driving.modules;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Base class used to represent all elements of the robot. Whether it be an arm,
 * a shooter, a sensor or a motor, it is a functioning part of the robot.
 *
 * <p> Modules can be enabled, and optionally given the ability to be disabled.
 * This provides flexibility of implementation of modules. Control of a module
 * will, by convention, enable modules. There are many possibilities of how to
 * use modules, and how they should behave.
 *
 * <p> Modules have identifiers that are all unique. They can be used to
 * identify which module object you are using because even two objects of the
 * same module have different identifiers. The identifier is just an
 * incrementing value, so each module you create has an identifier of how many
 * modules were created beforehand. (first modules created is 1, second is 2,
 * etc.)
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
     * <p> <b>READ ONLY. DOES NOT REMOVE ELEMENTS.</b>
     */
    public static final Hashtable MODULES = new Hashtable() {
        /**
         * <b>READ ONLY. DOES NOT REMOVE ELEMENT.</b>
         */
        public synchronized Object remove(Object key) {
            return key;
        }

        public synchronized String toString() {
            String s = "";
            if(isEmpty()) {
                return s;
            }
            Enumeration e = elements();
            while (e.hasMoreElements()) {
                s += e.nextElement().toString() + " | ";
            }
            return s.substring(0, s.length() - 3);
        }
    };
    private final String name;
    private final String identifier;

    /**
     * Creates a new module, with it's name stated. A name is a unique
     * identifier to the user of what the module actually does. Sometimes, it is
     * perfectly acceptable to use the same name twice, but this has a
     * consequences when debugging. It will be hard to identify what the module
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
        synchronized (MODULES) {
            // Set the identifier to the size of modules (1 higher than others)
            this.identifier = String.valueOf(MODULES.size());
            MODULES.put(identifier, toString());
        }
    }

    /**
     * Returns the name given to the module. A name cannot be changed. Do not
     * rely on names however, since two separate but similar modules can have
     * the same name. Instead, use the {@code getIdentifier()} method to
     * distinguish between modules.
     *
     * @return the user identifiable name of the module (eg. "left back motor",
     * "main robot drivetrain")
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the unique identifier of the module. Is an integer set when
     * constructed in the order of modules created. (first modules created is 1,
     * second is 2, etc.)
     *
     * @return unique identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Enables the module. This could mean many things, depending on the
     * implementation. A good rule of thumb for implementation is "turning on",
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
     * {@link java.util.HashMap}.
     *
     * <p> The general contract of {@code hashCode} is:
     *
     * <ul> <li>Whenever it is invoked on the same object more than once during
     * an execution of a Java application, the {@code hashCode} method must
     * consistently return the same integer, provided no information used in
     * {@code equals} comparisons on the object is modified. This integer need
     * not remain consistent from one execution of an application to another
     * execution of the same application. <li>If two objects are equal according
     * to the {@code equals(Object)} method, then calling the {@code hashCode}
     * method on each of the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal according
     * to the {@link java.lang.Object#equals(java.lang.Object)} method, then
     * calling the {@code hashCode} method on each of the two objects must
     * produce distinct integer results. However, the programmer should be aware
     * that producing distinct integer results for unequal objects may improve
     * the performance of hash tables. </ul>
     *
     * <p> As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct objects.
     * (This is typically implemented by converting the internal address of the
     * object into an integer, but this implementation technique is not required
     * by the Java<font size="-2"><sup>TM</sup></font> programming language.)
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
     * Indicates whether some other object is "equal to" this one.
     *
     * <p> The {@code equals} method implements an equivalence relation on
     * non-null object references: <ul> <li>It is <i>reflexive</i>: for any
     * non-null reference value {@code x}, {@code x.equals(x)} should return
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
     *     {@code x.equals(null)} should return {@code false}. </ul>
     *
     * <p> The {@code equals} method for class {@code Object} implements the
     * most discriminating possible equivalence relation on objects; that is,
     * for any non-null reference values {@code x} and {@code y}, this method
     * returns {@code true} if and only if {@code x} and {@code y} refer to the
     * same object ({@code x == y} has the value {@code true}).
     *
     * <p> Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the general
     * contract for the {@code hashCode} method, which states that equal objects
     * must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument;
     * {@code false} otherwise.
     * @see #hashCode()
     * @see java.util.HashMap
     */
    public boolean equals(Object obj) {
        return (obj instanceof Module) && ((Module) obj).identifier.equals(this.identifier);
    }

    public final String toString() {
        return getName() + "@" + getIdentifier();
    }
}