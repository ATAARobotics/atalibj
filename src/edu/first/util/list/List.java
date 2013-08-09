package edu.first.util.list;

/**
 * An ordered collection (also known as a <i>sequence</i>). The user of this
 * interface has precise control over where in the list each element is
 * inserted. The user can access elements by their integer index (position in
 * the list), and search for elements in the list.
 *
 * @since May 17 13
 * @author Joel Gallant
 */
public interface List extends Collection {

    /**
     * Adds every element in the order of their {@link Collection#iterator()} at
     * the specified {@code index} of this list. The list will expand and shift
     * other elements to the right.
     *
     * @param index placement in the list
     * @param c collection with elements to add
     */
    void addAll(int index, Collection c);

    /**
     * Returns the element currently at {@code index}.
     *
     * @param index placement in the list
     * @return element at the index
     */
    Object get(int index);

    /**
     * Replaces the element at the index to the new element.
     *
     * @param index placement in the list
     * @param element new object to place in the list
     * @return old element that was in the list
     */
    Object set(int index, Object element);

    /**
     * Adds the element to the list at the index, and shifts the elements to the
     * right so that this element is then at the {@code index}.
     *
     * @param index placement in the list
     * @param element new object to place at the index
     */
    void add(int index, Object element);

    /**
     * Removes the object at the index. Returns the object that was removed.
     *
     * @param index placement in the list
     * @return object that was removed
     */
    Object remove(int index);

    /**
     * Returns the first index of the specified element. If it is not contained,
     * returns {@code -1}.
     *
     * Uses {@link Object#equals(java.lang.Object)} to test whether the object
     * is there.
     *
     * @param o element that is contained
     * @return index of the element in the list
     */
    int indexOf(Object o);

    /**
     * Returns the index of the very last instance of this element in the list.
     * If it is not contained, returns {@code -1}.
     *
     * Uses {@link Object#equals(java.lang.Object)} to test whether the object
     * is there.
     *
     * @param o element that is contained
     * @return index of the last instance of the element in the list
     */
    int lastIndexOf(Object o);

    /**
     * Returns a list that contains the elements from the {@code fromIndex} to
     * the {@code toIndex} in the order they are in this list.
     *
     * @param fromIndex index of first element
     * @param toIndex index of the last element
     * @return list with every element from {@code fromIndex} to {@code toIndex}
     */
    List subList(int fromIndex, int toIndex);
}
