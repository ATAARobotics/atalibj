package edu.first.util.list;

/**
 * A collection represents a group of objects, known as its <i>elements</i>.
 * Some collections allow duplicate elements and others do not. Some are ordered
 * and others unordered. This interface is typically used to pass collections
 * around and manipulate them where maximum generality is desired.
 *
 * @since May 17 13
 * @author Joel Gallant
 */
public interface Collection extends Iterable {

    /**
     * Returns the number of elements in this collection. If this collection
     * contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this collection
     */
    int size();

    /**
     * Returns whether this collection contains no elements.
     *
     * @return if this collection contains no elements
     */
    boolean isEmpty();

    /**
     * Returns whether this collection contains {@code o}. Returns true if and
     * only if there is one element that
     * {@code o.equals(element) || (o == null && element == null)}.
     *
     * @param o element whose presence in this collection is to be tested
     * @return if this collection contains the specified element
     */
    boolean contains(Object o);

    /**
     * Returns an array containing all of the elements in this collection. The
     * returned array will be "safe" in that no references to it are maintained
     * by this collection. (In other words, this method must allocate a new
     * array even if this collection is backed by an array). The caller is thus
     * free to modify the returned array.
     *
     * @return an array containing all of the elements in this collection
     */
    Object[] toArray();

    /**
     * Inserts the element into the collection. Unless otherwise specified, this
     * method will always put the element at the "end" of the collection, if
     * that is possible.
     *
     * Some collections do not allow things like duplicates or null elements.
     * They should document such cases and what exceptions will be thrown.
     *
     * @param e element to add to the collection
     */
    void add(Object e);

    /**
     * Removes the element from the collection. Unless otherwise specified, this
     * method will always remove the "first" instance of this element, if that
     * is possible.
     *
     * @param o element to remove from the collection
     */
    void remove(Object o);

    /**
     * Returns whether this collection contains every element in {@code c}. Goes
     * over every element and checks if this collection
     * {@link #contains(Object)} it.
     *
     * @param c collection to check over
     * @return if every element is contained
     */
    boolean containsAll(Collection c);

    /**
     * Inserts every element in the collection {@code c} to this collection.
     * Goes over every element and adds them to this collection.
     * ({@link #add(Object)})
     *
     * @param c collection to add every element from
     */
    void addAll(Collection c);

    /**
     * Removes every element in the collection {@code c} from this collection.
     * Goes over every element and removes them from this collection.
     * ({@link #remove(Object)})
     *
     * @param c collection of elements to remove
     */
    void removeAll(Collection c);

    /**
     * Removes from this collection all of its elements that are not contained
     * in {@code c}.
     *
     * @param c collection of elements to ensure are contained
     */
    void retainAll(Collection c);

    /**
     * Removes every element in this collection. After this method is called,
     * provided nothing else is called, by convention {@link #isEmpty()} will
     * return {@code true}.
     */
    void clear();
}
