package edu.first.util;

import edu.first.util.list.Iterator;
import edu.first.util.list.ArrayList;
import edu.first.util.list.List;
import java.util.Hashtable;

/**
 * The base class for enums. This class is not meant to be instantiated.
 *
 * The correct way to implement this class goes as follows:
 * <pre>
 * public final class Colour extends Enum {
 *
 *     public static final Colour RED = new Colour("RED");
 *     public static final Colour BLUE = new Colour("BLUE");
 *     public static final Colour YELLOW = new Colour("YELLOW");
 *     public static final Colour ORANGE = new Colour("ORANGE");
 *
 *     private Colour(String name) {
 *         super(name);
 *     }
 * }
 * </pre>
 *
 * or, if you do not need names for {@link #toString()}:
 * <pre>
 * public final class Colour extends Enum {
 *
 *     public static final Colour RED = new Colour();
 *     public static final Colour BLUE = new Colour();
 *     public static final Colour YELLOW = new Colour();
 *     public static final Colour ORANGE = new Colour();
 *
 *     private Colour() {
 *         super();
 *     }
 * }
 * </pre>
 *
 * In every official enum, use this pattern:
 * <ul>
 * <li> Extend {@link Enum}
 * <li> Make class final
 * <li> Create a private constructor using {@link #Enum(java.lang.String)} or
 * {@link #Enum()} as a super call.
 * <li> For every value in the enum, declare it as
 * <p> {@code public static final EnumName NAME = new EnumName("NAME");}
 * <p> or
 * <p> {@code public static final EnumName NAME = new EnumName();}
 * <p> if you do not need named fields.
 * </ul>
 *
 * Enums are type-safe (will only match other enum values of the same type),
 * instance-safe (cannot be instantiated anywhere but in the enum) and
 * compare-safe ({@code ==} and {@link #equals(Object o)} should both only
 * return true if they are the same).
 *
 * @since May 12 13
 * @author Joel Gallant
 */
public class Enum {

    // holds all instances - this had better be safe!
    private static final Hashtable instances = new Hashtable();
    // counts elements and prevents them from having the same "equals"
    private static int preventEquals;
    // makes sure enum is never equal to anything except itself
    private final int equals = ++preventEquals;
    // optional name
    private final String name;

    /**
     * Creates the element without a name. {@link #toString()} will return the
     * standard string for objects.
     */
    protected Enum() {
        this(null);
    }

    /**
     * Creates the element with a name. {@link #toString()} will return the
     * name. Names should be the exact same as the fields they are declared as.
     *
     * @param name identifier of the element
     */
    protected Enum(String name) {
        this.name = name;
    }

    {
        if (!instances.containsKey(getClass())) {
            instances.put(getClass(), new ArrayList());
        }
        // inserts the instance into elements
        ((List) instances.get(getClass())).add(this);
    }

    /**
     * Returns every element inside of the enum that this element is in.
     *
     * @return all values in the same enum
     */
    public final List getValues() {
        return (List) instances.get(getClass());
    }

    /**
     * Returns the element with the specified name inside of the enum that this
     * element is in. Returns null if no such element exists.
     *
     * @param name identifier for element
     * @return value with specified name
     */
    public final Enum valueOf(String name) {
        Iterator i = getValues().iterator();
        while (i.hasNext()) {
            Enum val = (Enum) i.next();
            if (val.name.equals(name)) {
                return val;
            }
        }
        return null;
    }

    /**
     * Returns whether the object is the same element as this one. This is
     * slightly safer than performing {@code o1 == o2}, but less efficient.
     *
     * @param obj object to compare
     * @return if they are the same element in the same enum
     */
    public final boolean equals(Object obj) {
        if (getClass().isInstance(obj)) {
            return equals == ((Enum) obj).equals;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public final int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.equals;
        return hash;
    }

    /**
     * If this element has a name, returns the name. If not, returns
     * {@link Object#toString()}.
     *
     * @return string representation of element
     * @see #Enum(java.lang.String)
     * @see Object#toString()
     */
    public final String toString() {
        return name == null ? super.toString() : name;
    }
}
