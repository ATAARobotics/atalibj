package edu.first.util.list;

import com.sun.squawk.util.Comparer;
import edu.first.util.Iterator;

/**
 * A {@link List} that is automatically sorted. It will always sort based on
 * {@link Comparer#compare(java.lang.Object, java.lang.Object)}.
 *
 * @since May 18 13
 * @author Joel Gallant
 */
public final class SortedArrayList implements List {

    private final List list;
    private final Comparer comparer;

    /**
     * Constructs the list using a {@link Comparer} that will be used to sort
     * this list. There is no type-safety to guarantee that this list will only
     * contain one type of object, so {@code c} should account for all types
     * that are intended to be used by this list, but also account for others.
     * If you will guarantee type safety, throw an error in
     * {@link Comparer#compare(java.lang.Object, java.lang.Object)} to notify
     * when type safety has been broken.
     *
     * @param c comparer to use in sorting
     */
    public SortedArrayList(Comparer c) {
        this.list = new ArrayList();
        this.comparer = c;
    }

    /**
     * Constructs the list using a {@link Comparer} that will be used to sort
     * this list. There is no type-safety to guarantee that this list will only
     * contain one type of object, so {@code c} should account for all types
     * that are intended to be used by this list, but also account for others.
     * If you will guarantee type safety, throw an error in
     * {@link Comparer#compare(java.lang.Object, java.lang.Object)} to notify
     * when type safety has been broken.
     *
     * @param c object with a group of elements to add off the start
     * @param comparer comparer to use in sorting
     */
    public SortedArrayList(Collection c, Comparer comparer) {
        this.list = new ArrayList(c);
        this.comparer = comparer;
    }

    /**
     * {@inheritDoc}
     */
    public void addAll(int index, Collection c) {
        list.addAll(index, c);
        sort();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     */
    public Object get(int index) {
        return list.get(index);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException when {@code element} is {@code null}
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     */
    public Object set(int index, Object element) {
        Object o = list.set(index, element);
        sort();
        return o;
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException when {@code element} is {@code null}
     */
    public void add(int index, Object element) {
        list.add(index, element);
        sort();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     */
    public Object remove(int index) {
        Object o = list.remove(index);
        sort();
        return o;
    }

    /**
     * {@inheritDoc}
     */
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    public List subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    /**
     * {@inheritDoc}
     */
    public int size() {
        return list.size();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns whether this collection contains {@code o}. Returns true if and
     * only if there is one element that {@code o.equals(element)}.
     *
     * @param o element whose presence in this collection is to be tested
     * @return if this collection contains the specified element
     */
    public boolean contains(Object o) {
        return list.contains(o);
    }

    /**
     * {@inheritDoc}
     *
     * <p> The returned array will be in the order of this list.
     */
    public Object[] toArray() {
        return list.toArray();
    }

    /**
     * Inserts the element into the collection. This method will always put the
     * element at the end of the collection.
     *
     * This list does not allow {@code null} elements. A
     * {@link NullPointerException} will be thrown if a null value is added.
     *
     * @param e element to add to the collection
     * @throws NullPointerException when {@code o} is added
     */
    public void add(Object e) {
        list.add(e);
        sort();
    }

    /**
     * Removes the element from the collection. This method will always remove
     * the first instance of this element.
     *
     * @param o element to remove from the collection
     */
    public void remove(Object o) {
        list.remove(o);
        sort();
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsAll(Collection c) {
        return list.containsAll(c);
    }

    /**
     * {@inheritDoc}
     */
    public void addAll(Collection c) {
        list.addAll(c);
        sort();
    }

    /**
     * {@inheritDoc}
     */
    public void removeAll(Collection c) {
        list.removeAll(c);
        sort();
    }

    /**
     * {@inheritDoc}
     */
    public void retainAll(Collection c) {
        list.retainAll(c);
        sort();
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        list.clear();
    }

    /**
     * {@inheritDoc}
     *
     * Elements are in the order that they are in this list.
     */
    public Iterator iterator() {
        return list.iterator();
    }

    /**
     * Returns a string representation of this collection. The string
     * representation consists of a list of the collection's elements in the
     * order they are returned by its iterator, enclosed in square brackets
     * (<tt>"[]"</tt>). Adjacent elements are separated by the characters
     * <tt>", "</tt> (comma and space). Elements are converted to strings as by
     * {@link String#valueOf(Object)}.
     *
     * @return a string representation of this collection
     */
    public String toString() {
        return list.toString();
    }

    private void sort() {
        Collections.sort(list, comparer);
    }
}
