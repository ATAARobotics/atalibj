package edu.first.util.list;

import edu.first.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A non-type-safe non-thread-safe re-sizable array that automatically adjusts
 * to growth and decline of elements. Holds references to a list of objects and
 * gives outside access to them.
 *
 * This implementation is built to give a more robust and consistent list than
 * {@link edu.wpi.first.wpilibj.networktables2.util.List}. It provides all
 * needed methods to add, remove and get elements from the array.
 *
 * @since May 15 13
 * @author Joel Gallant
 */
public final class ArrayList implements List {

    private final ResizingArray array = new ResizingArray();

    /**
     * Constructs an empty array list with no elements.
     */
    public ArrayList() {
    }

    /**
     * Constructs an array list with the elements of {@code iterable} inserted.
     *
     * @param collection object with a group of elements to add off the start
     */
    public ArrayList(Collection collection) {
        addAll(collection);
    }

    /**
     * {@inheritDoc}
     */
    public int size() {
        return array.size();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return array.size() == 0;
    }

    /**
     * Returns whether this collection contains {@code o}. Returns true if and
     * only if there is one element that {@code o.equals(element)}.
     *
     * @param o element whose presence in this collection is to be tested
     * @return if this collection contains the specified element
     */
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    /**
     * {@inheritDoc}
     *
     * <p> The returned array will be in the order of this list.
     */
    public Object[] toArray() {
        Object[] a = new Object[size()];
        for (int x = 0; x < a.length; x++) {
            a[x] = get(x);
        }
        return a;
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
    public void add(Object o) {
        array.add(o);
    }

    /**
     * Removes the element from the collection. This method will always remove
     * the first instance of this element.
     *
     * @param o element to remove from the collection
     */
    public void remove(Object object) {
        array.remove(object);
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsAll(Collection c) {
        Iterator i = c.iterator();
        while (i.hasNext()) {
            if (!contains(i.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void addAll(Collection c) {
        Iterator i = c.iterator();
        while (i.hasNext()) {
            add(i.next());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeAll(Collection c) {
        Iterator i = c.iterator();
        while (i.hasNext()) {
            remove(i.next());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void retainAll(Collection c) {
        Iterator i = iterator();
        while (i.hasNext()) {
            Object next = i.next();
            if (!c.contains(next)) {
                remove(next);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        array.clear();
    }

    /**
     * {@inheritDoc}
     */
    public void addAll(int index, Collection c) {
        Iterator i = c.iterator();
        for (; i.hasNext(); index++) {
            add(index, i.next());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     */
    public Object get(int index) {
        return array.get(index);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws NullPointerException when {@code element} is {@code null}
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     */
    public Object set(int index, Object element) {
        return array.set(index, element);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws NullPointerException when {@code element} is {@code null}
     */
    public void add(int index, Object element) {
        array.add(index, element);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     */
    public Object remove(int index) {
        Object o = get(index);
        remove(o);
        return o;
    }

    /**
     * {@inheritDoc}
     */
    public int indexOf(Object o) {
        for (int x = 0; x < size(); x++) {
            if (o.equals(get(x))) {
                return x;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    public int lastIndexOf(Object o) {
        for (int x = size() - 1; x >= 0; x--) {
            if (o.equals(get(x))) {
                return x;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    public List subList(int fromIndex, int toIndex) {
        ArrayList l = new ArrayList();
        for (int x = fromIndex; x <= toIndex; x++) {
            l.add(get(x));
        }
        return l;
    }

    /**
     * {@inheritDoc}
     * 
     * Elements are in the order that they are in this list.
     */
    public Iterator iterator() {
        return new Itr(toArray());
    }

    // Private iterator implementation
    private static class Itr implements Iterator {

        private final Object[] elements;
        private int p = 0;

        private Itr(Object[] elements) {
            this.elements = elements;
        }

        public boolean hasNext() {
            return p < elements.length;
        }

        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[p++];
        }
    }

    private static final class ResizingArray {

        private Object[] elements = new Object[0];

        private void add(Object o) {
            if (o == null) {
                throw new NullPointerException();
            }
            Object[] b = elements;
            elements = new Object[b.length + 1];
            System.arraycopy(b, 0, elements, 0, b.length);

            elements[elements.length - 1] = o;
        }

        private void add(int i, Object o) {
            if (i >= size()) {
                throw new IndexOutOfBoundsException("Index " + i + " is out of bounds.");
            }
            if (o == null) {
                throw new NullPointerException();
            }

            Object[] b = elements;
            elements = new Object[b.length + 1];
            System.arraycopy(b, 0, elements, 0, i);
            System.arraycopy(b, i, elements, i + 1, b.length - i);

            elements[i] = o;
        }

        private Object set(int x, Object o) {
            if (o == null) {
                throw new NullPointerException();
            }
            if (x >= size()) {
                throw new IndexOutOfBoundsException("Index " + x + " is out of bounds.");
            }
            Object p = elements[x];
            elements[x] = o;
            return p;
        }

        private void remove(Object o) {
            for (int x = 0; x < elements.length; x++) {
                if (o.equals(elements[x])) {
                    elements[x] = null;
                    shrink(x);
                    return;
                }
            }
        }

        private Object get(int i) {
            if (i >= size()) {
                throw new IndexOutOfBoundsException("Index " + i + " is out of bounds.");
            }
            return elements[i];
        }

        private void clear() {
            elements = new Object[0];
        }

        private int size() {
            return elements.length;
        }

        private void shrink(int x) {
            for (; x < elements.length; x++) {
                if (elements[x] == null) {

                    Object[] b = elements;
                    elements = new Object[b.length - 1];
                    System.arraycopy(b, 0, elements, 0, x);
                    System.arraycopy(b, x + 1, elements, x, b.length - x - 1);

                    shrink(x + 1);
                    return;
                }
            }
        }
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
        Iterator it = iterator();
        if (!it.hasNext()) {
            return "[]";
        }

        StringBuffer sb = new StringBuffer();
        sb.append('[');
        for (;;) {
            Object e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(',').append(' ');
        }
    }
}