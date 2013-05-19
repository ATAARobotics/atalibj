package edu.first.util.list;

import edu.first.util.Iterator;

/**
 * An {@link ArrayList} that guarantees elements will be of the type given in
 * the constructor. All elements added are checked to make sure they are of the
 * same type. If they are not, a {@link IllegalStateException} is thrown.
 *
 * @author Joel Gallant
 */
public class SafeArrayList implements List {

    private final ArrayList list;
    private final Class clazz;

    /**
     * Constructs the list with the class to ensure all elements are of the
     * type.
     *
     * @param clazz class all elements will be instances of
     */
    public SafeArrayList(Class clazz) {
        this.list = new ArrayList();
        this.clazz = clazz;
    }

    /**
     * Constructs the list with the class to ensure all elements are of the
     * type.
     *
     * @param c object with a group of elements to add off the start
     * @param clazz class all elements will be instances of
     */
    public SafeArrayList(Collection c, Class clazz) {
        this.list = new ArrayList(c);
        this.clazz = clazz;
    }

    /**
     * Throws an {@link IllegalStateException} because type cannot be verified.
     */
    public void addAll(int index, Collection c) {
        throw new IllegalStateException("Adding illegal collection");
    }

    /**
     * {@inheritDoc}
     *
     * This method is guaranteed to return an instance of the class given.
     *
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     * @throws IllegalStateException when element is not of the class type given
     */
    public Object get(int index) {
        Object o = list.get(index);
        if (clazz.isInstance(o)) {
            return o;
        } else {
            throw new IllegalStateException("Element at index " + index + " was not a " + clazz.getName());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException when {@code element} is {@code null}
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     * @throws IllegalStateException when element is not of the class type given
     */
    public Object set(int index, Object element) {
        if (clazz.isInstance(element)) {
            return list.set(index, element);
        } else {
            throw new IllegalStateException("Element " + element + " was not a " + clazz.getName());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException when {@code element} is {@code null}
     * @throws IllegalStateException when element is not of the class type given
     */
    public void add(int index, Object element) {
        if (clazz.isInstance(element)) {
            list.add(index, element);
        } else {
            throw new IllegalStateException("Element " + element + " was not a " + clazz.getName());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     */
    public Object remove(int index) {
        return list.remove(index);
    }

    /**
     * {@inheritDoc}
     *
     * If the object is not of the given type, this method will always return
     * {@code -1}.
     */
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    /**
     * {@inheritDoc}
     *
     * If the object is not of the given type, this method will always return
     * {@code -1}.
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
     * Returns a {@link SafeArrayList} that uses the same class type as this
     * list.
     *
     * @param fromIndex index of first element
     * @param toIndex index of the last element
     * @return a safe list
     */
    public SafeArrayList safeSubList(int fromIndex, int toIndex) {
        return new SafeArrayList(subList(fromIndex, toIndex), clazz);
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
     * If the object given is not an instance of the given class, this method
     * will always return {@code false}.
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
     * Every element will be an instance of the given class.
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
     * @throws IllegalStateException when element is not of the class type given
     */
    public void add(Object e) {
        if (clazz.isInstance(e)) {
            list.add(e);
        } else {
            throw new IllegalStateException("Element " + e + " was not a " + clazz.getName());
        }
    }

    /**
     * Removes the element from the collection. This method will always remove
     * the first instance of this element.
     *
     * @param o element to remove from the collection
     */
    public void remove(Object o) {
        list.remove(o);
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsAll(Collection c) {
        return list.containsAll(c);
    }

    /**
     * Throws an {@link IllegalStateException} because type cannot be verified.
     */
    public void addAll(Collection c) {
        throw new IllegalStateException("Adding illegal collection");
    }

    /**
     * {@inheritDoc}
     */
    public void removeAll(Collection c) {
        list.removeAll(c);
    }

    /**
     * {@inheritDoc}
     */
    public void retainAll(Collection c) {
        list.retainAll(c);
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
}
