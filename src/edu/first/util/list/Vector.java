package edu.first.util.list;

import edu.first.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A concurrent list data structure that is thread safe. It should *only* be
 * used when threads are necessary. Performance is very poor.
 *
 * @author Joel Gallant
 */
public final class Vector implements List {

    private final java.util.Vector vector;

    /**
     * Constructs the vector list.
     */
    public Vector() {
        this.vector = new java.util.Vector();
    }

    /**
     * Constructs the vector list.
     *
     * @param capacity initial capacity to use
     */
    public Vector(int capacity) {
        this.vector = new java.util.Vector(capacity);
    }

    /**
     * {@inheritDoc}
     */
    public void addAll(int index, Collection c) {
        Iterator i = c.iterator();
        while (i.hasNext()) {
            add(index++, i.next());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object get(int index) {
        return vector.elementAt(index);
    }

    /**
     * {@inheritDoc}
     */
    public Object set(int index, Object element) {
        Object old = get(index);
        vector.setElementAt(element, index);
        return old;
    }

    /**
     * {@inheritDoc}
     */
    public void add(int index, Object element) {
        vector.insertElementAt(element, index);
    }

    /**
     * {@inheritDoc}
     */
    public Object remove(int index) {
        Object o = get(index);
        vector.removeElementAt(index);
        return o;
    }

    /**
     * {@inheritDoc}
     */
    public int indexOf(Object o) {
        return vector.indexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    public int lastIndexOf(Object o) {
        return vector.lastIndexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    public List subList(int fromIndex, int toIndex) {
        List l = new ArrayList(toIndex - fromIndex);
        for (int x = fromIndex; x <= toIndex; x++) {
            l.add(get(x));
        }
        return l;
    }

    /**
     * {@inheritDoc}
     */
    public int size() {
        return vector.size();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(Object o) {
        return vector.contains(o);
    }

    /**
     * {@inheritDoc}
     */
    public Object[] toArray() {
        Object[] o = new Object[size()];
        for (int x = 0; x < o.length; x++) {
            o[x] = get(x);
        }
        return o;
    }

    /**
     * {@inheritDoc}
     */
    public void add(Object e) {
        vector.addElement(e);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(Object o) {
        vector.removeElement(o);
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsAll(Collection c) {
        boolean b = true;
        Iterator i = c.iterator();
        while (i.hasNext()) {
            if (!vector.contains(i.next())) {
                b = false;
            }
        }
        return b;
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
        vector.removeAllElements();
    }

    /**
     * {@inheritDoc}
     */
    public Iterator iterator() {
        return new Iterator() {
            final Object[] e = Arrays.copyOf(toArray(), size());
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
}
