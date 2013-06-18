package edu.first.lang;

/**
 * This interface imposes a total ordering on the objects of each class that
 * implements it. This ordering is referred to as the class's <i>natural
 * ordering</i>, and the class's {@code compareTo} method is referred to as its
 * <i>natural comparison method</i>.<p>
 *
 * Lists (and arrays) of objects that implement this interface can be sorted
 * automatically by {@link Collections#sort(List) Collections.sort} (and
 * {@link Arrays#sort(Object[]) Arrays.sort}). Objects that implement this
 * interface can be used as keys in a {@linkplain SortedMap sorted map} or as
 * elements in a {@linkplain SortedSet sorted set}, without the need to specify
 * a {@linkplain Comparator comparator}.<p>
 *
 * The natural ordering for a class {@code C} is said to be <i>consistent with
 * equals</i> if and only if {@code e1.compareTo(e2) == 0} has the same boolean
 * value as {@code e1.equals(e2)} for every {@code e1} and {@code e2} of class
 * {@code C}. Note that {@code null} is not an instance of any class, and
 * {@code e.compareTo(null)} should throw a {@code NullPointerException} even
 * though {@code e.equals(null)} returns {@code false}.<p>
 *
 * It is strongly recommended (though not required) that natural orderings be
 * consistent with equals. This is so because sorted sets (and sorted maps)
 * without explicit comparators behave "strangely" when they are used with
 * elements (or keys) whose natural ordering is inconsistent with equals. In
 * particular, such a sorted set (or sorted map) violates the general contract
 * for set (or map), which is defined in terms of the {@code equals} method.<p>
 *
 * For example, if one adds two keys {@code a} and {@code b} such that
 * {@code (!a.equals(b) && a.compareTo(b) == 0)} to a sorted set that does not
 * use an explicit comparator, the second {@code add} operation returns false
 * (and the size of the sorted set does not increase) because {@code a} and
 * {@code b} are equivalent from the sorted set's perspective.<p>
 *
 * Virtually all Java core classes that implement {@code Comparable} have
 * natural orderings that are consistent with equals. One exception is
 * {@code java.math.BigDecimal}, whose natural ordering equates
 * {@code BigDecimal} objects with equal values and different precisions (such
 * as 4.0 and 4.00).<p>
 *
 * For the mathematically inclined, the <i>relation</i> that defines the natural
 * ordering on a given class C is:
 * <pre>
 *       {(x, y) such that x.compareTo(y) <= 0}.
 * </pre> The
 * <i>quotient</i> for this total order is:
 * <pre>
 *       {(x, y) such that x.compareTo(y) == 0}.
 * </pre>
 *
 * It follows immediately from the contract for {@code compareTo} that the
 * quotient is an <i>equivalence relation</i> on {@code C}, and that the natural
 * ordering is a <i>total order</i> on {@code C}. When we say that a class's
 * natural ordering is <i>consistent with equals</i>, we mean that the quotient
 * for the natural ordering is the equivalence relation defined by the class's
 * {@link Object#equals(Object) equals(Object)} method:
 * <pre>
 *     {(x, y) such that x.equals(y)}. </pre><p>
 *
 * This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @author Josh Bloch
 * @see Comparator
 * @since June 17 13
 */
public interface Comparable {

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@code sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))} for all {@code x} and {@code y}. (This implies that
     * {@code x.compareTo(y)} must throw an exception if {@code y.compareTo(x)}
     * throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) == y.compareTo(z))} implies
     * {@code x.compareTo(z) = 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for all
     * {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)) == (x.equals(y))}. Generally speaking, any
     * class that implements the {@code Comparable} interface and violates this
     * condition should clearly indicate this fact. The recommended language is
     * "Note: this class has a natural ordering that is inconsistent with
     * equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is
     * less than, equal to, or greater than the specified object.
     *
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException if the specified object's type prevents it
     * from being compared to this object.
     */
    public int compareTo(Object o);
}
