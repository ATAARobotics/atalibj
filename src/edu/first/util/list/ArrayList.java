package edu.first.util.list;

import edu.first.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A non-type-safe non-thread-safe re-sizable array that automatically adjusts
 * to growth and decline of elements. Holds references to a list of objects and
 * gives outside access to them.
 *
 * <p> This implementation is built to give a more robust and consistent list
 * than {@link edu.wpi.first.wpilibj.networktables2.util.List}. It provides all
 * needed methods to add, remove and get elements from the array.
 *
 * <p> <i>*A small caveat - this implementation performs at 1-5% slower than the
 * official ArrayList implementation on JDK7. The performance differences are so
 * minimal that you should not worry about them.</i>
 *
 * @since May 15 13
 * @author Joel Gallant
 */
public final class ArrayList implements List {

    private transient Object[] elementData;
    private int size = 0;

    /**
     * Constructs an empty array list with no elements.
     *
     * @param capacity expected amount of elements to stage performance for
     */
    public ArrayList(int capacity) {
        this.elementData = new Object[capacity];
    }

    /**
     * Constructs an empty array list with no elements.
     */
    public ArrayList() {
        this(10);
    }

    /**
     * Constructs an array list with the elements of {@code iterable} inserted.
     *
     * @param collection object with a group of elements to add off the start
     */
    public ArrayList(Collection collection) {
        this.elementData = collection.toArray();
        if (elementData.getClass() != Object[].class) {
            elementData = Arrays.copyOf(elementData, size);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when {@code index < 0 && index > size}
     */
    public void addAll(int index, Collection c) {
        checkIndexAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacity(size + numNew);

        int numMoved = size - index;
        if (numMoved > 0) {
            System.arraycopy(elementData, index, elementData, index + numNew,
                    numMoved);
        }

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     */
    public Object get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     */
    public Object set(int index, Object element) {
        checkIndex(index);
        Object old = elementData[index];
        elementData[index] = element;
        return old;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when {@code index < 0 && index > size}
     */
    public void add(int index, Object element) {
        checkIndexAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     */
    public Object remove(int index) {
        checkIndex(index);

        Object oldValue = elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null;

        return oldValue;
    }

    /**
     * {@inheritDoc}
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when {@code index < 0 && index >= size}
     */
    public List subList(int fromIndex, int toIndex) {
        checkIndex(fromIndex);
        checkIndex(toIndex);

        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException(fromIndex + "  is higher than " + toIndex);
        }

        List l = new ArrayList((toIndex - fromIndex) * 2);
        for (int x = fromIndex; x <= toIndex; x++) {
            l.add(elementData[x]);
        }
        return l;
    }

    /**
     * {@inheritDoc}
     */
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns whether this collection contains {@code o}. Returns true if and
     * only if there is one element that {@code o.equals(element)}.
     *
     * @param o element whose presence in this collection is to be tested
     * @return if this collection contains the specified element
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * {@inheritDoc}
     *
     * <p> The returned array will be in the order of this list.
     */
    public Object[] toArray() {
        // array is guaranteed to be the size
        Object[] r = new Object[size];
        Iterator it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext()) // fewer elements than expected
            {
                return Arrays.copyOf(r, i);
            }
            r[i] = it.next();
        }
        return r;
    }

    /**
     * Inserts the element into the collection. This method will always put the
     * element at the end of the collection.
     *
     * @param e element to add to the collection
     */
    public void add(Object e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
    }

    /**
     * Removes the element from the collection. This method will always remove
     * the first instance of this element.
     *
     * @param o element to remove from the collection
     */
    public void remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (elementData[index] == null) {
                    fastRemove(index);
                    return;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return;
                }
            }
        }
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
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacity(size + numNew);
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
    }

    /**
     * {@inheritDoc}
     */
    public void removeAll(Collection c) {
        Iterator i = c.iterator();
        while (i.hasNext()) {
            Object o = i.next();
            if (contains(o)) {
                remove(o);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void retainAll(Collection c) {
        Iterator i = iterator();
        while (i.hasNext()) {
            Object o = i.next();
            if (!c.contains(o)) {
                remove(o);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        Iterator i = iterator();
        while (i.hasNext()) {
            remove(i.next());
        }
    }

    /**
     * Ensures that the array list is configured to hold the amount of objects
     * given. This method is provided to improve performance. If you plan to add
     * a lot of elements, this method should be used.
     *
     * @param min minimum capacity
     */
    public void ensureCapacity(int min) {
        if (min > elementData.length) {
            growTo(min);
        }
    }

    /**
     * {@inheritDoc}
     *
     * Elements are in the order that they are in this list.
     */
    public Iterator iterator() {
        return new Iterator() {
            final Object[] e = Arrays.copyOf(elementData, size);
            int index = 0;

            public boolean hasNext() {
                return index < e.length;
            }

            public Object next() throws NoSuchElementException {
                if (index >= e.length) {
                    throw new NoSuchElementException("Element " + index + " does not exist");
                }
                return e[index++];
            }
        };
    }

    /**
     * Compares the specified object with this list for equality. Returns
     * {@code true} if and only if the specified object is also a list, both
     * lists have the same size, and all corresponding pairs of elements in the
     * two lists are <i>equal</i>. (Two elements {@code e1} and {@code e2} are
     * <i>equal</i> if {@code (e1==null ? e2==null :
     * e1.equals(e2))}.) In other words, two lists are defined to be equal if
     * they contain the same elements in the same order.<p>
     *
     * This implementation first checks if the specified object is this list. If
     * so, it returns {@code true}; if not, it checks if the specified object is
     * a list. If not, it returns {@code false}; if so, it iterates over both
     * lists, comparing corresponding pairs of elements. If any comparison
     * returns {@code false}, this method returns {@code false}. If either
     * iterator runs out of elements before the other it returns {@code false}
     * (as the lists are of unequal length); otherwise it returns {@code true}
     * when the iterations complete.
     *
     * @param o the object to be compared for equality with this list
     * @return {@code true} if the specified object is equal to this list
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof List)) {
            return false;
        }

        Iterator e1 = iterator();
        Iterator e2 = ((List) o).iterator();
        while (e1.hasNext() && e2.hasNext()) {
            Object o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2))) {
                return false;
            }
        }
        return !(e1.hasNext() || e2.hasNext());
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

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds (0 - " + (size - 1) + ")");
        }
    }

    private void checkIndexAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds (0 - " + (size - 1) + ")");
        }
    }

    private void growTo(int min) {
        elementData = Arrays.copyOf(elementData, min);
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null; // Let gc do its work
    }
}