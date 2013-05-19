/*
 * Copyright (c) 1997, 2012, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package edu.first.util.list;

import com.sun.squawk.util.Arrays;
import com.sun.squawk.util.Comparer;
import edu.first.util.Iterator;

/**
 * Utilities for {@link Collection}. Manipulates the collections using various
 * methods to change the collections.
 *
 * @since May 18 13
 * @author Joel Gallant
 */
public class Collections {

    // cannot be subclassed or instantiated
    private Collections() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Sorts the specified list into ascending order, according to the
     * {@linkplain Comparable natural ordering} of its elements. All elements in
     * the list must implement the {@link Comparable} interface. Furthermore,
     * all elements in the list must be
     * <i>mutually comparable</i> (that is, {@code e1.compareTo(e2)} must not
     * throw a {@code ClassCastException} for any elements {@code e1} and
     * {@code e2} in the list).
     *
     * <p>This sort is guaranteed to be <i>stable</i>: equal elements will not
     * be reordered as a result of the sort.
     *
     * <p>The specified list must be modifiable, but need not be resizable.
     *
     * <p>Implementation note: This implementation is a stable, adaptive,
     * iterative mergesort that requires far fewer than n lg(n) comparisons when
     * the input array is partially sorted, while offering the performance of a
     * traditional mergesort when the input array is randomly ordered. If the
     * input array is nearly sorted, the implementation requires approximately n
     * comparisons. Temporary storage requirements vary from a small constant
     * for nearly sorted input arrays to n/2 object references for randomly
     * ordered input arrays.
     *
     * <p>The implementation takes equal advantage of ascending and descending
     * order in its input array, and can take advantage of ascending and
     * descending order in different parts of the same input array. It is
     * well-suited to merging two or more sorted arrays: simply concatenate the
     * arrays and sort the resulting array.
     *
     * <p>The implementation was adapted from Tim Peters's list sort for Python
     * (<a
     * href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>). It uses techiques from Peter McIlroy's "Optimistic Sorting
     * and Information Theoretic Complexity", in Proceedings of the Fourth
     * Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474, January
     * 1993.
     *
     * <p>This implementation dumps the specified list into an array, sorts the
     * array, and iterates over the list resetting each element from the
     * corresponding position in the array. This avoids the n<sup>2</sup> log(n)
     * performance that would result from attempting to sort a linked list in
     * place.
     *
     * @param list the list to be sorted.
     * @throws ClassCastException if the list contains elements that are not
     * <i>mutually comparable</i> (for example, strings and integers).
     * @throws IllegalStateException if the specified list's list-iterator does
     * not support the {@code set} operation.
     * @throws IllegalArgumentException (optional) if the implementation detects
     * that the natural ordering of the list elements is found to violate the
     * {@link Comparable} contract
     */
    public static void sort(List list, Comparer c) {
        Object[] a = list.toArray();
        Arrays.sort(a, c);
        for (int j = 0; j < a.length; j++) {
            list.set(j, a[j]);
        }
    }

    /**
     * Adds all of the specified elements to the specified collection. Elements
     * to be added may be specified individually or as an array. The behavior of
     * this convenience method is identical to that of
     * {@code c.addAll(Arrays.asList(elements))}, but this method is likely to
     * run significantly faster under most implementations.
     *
     * <p> When elements are specified individually, this method provides a
     * convenient way to add a few elements to an existing collection:
     * <pre>
     *     Collections.addAll(flavors, new String[] {"Peaches 'n Plutonium", "Rocky Racoon"});
     * </pre>
     *
     * @param c the collection into which {@code elements} are to be inserted
     * @param elements the elements to insert into {@code c}
     * @return if the collection changed as a result of the call
     * @see Collection#addAll(Collection)
     */
    public static void addAll(Collection c, Object[] elements) {
        for (int x = 0; x < elements.length; x++) {
            c.add(elements[x]);
        }
    }

    /**
     * Returns the number of elements in the specified collection equal to the
     * specified object. More formally, returns the number of elements {@code e}
     * in the collection such that
     * {@code (o == null ? e == null : o.equals(e))}.
     *
     * @param c the collection in which to determine the frequency of {@code o}
     * @param o the object whose frequency is to be determined
     * @throws NullPointerException if {@code c} is null
     */
    public static int frequency(Collection c, Object o) {
        int result = 0;
        Iterator i = c.iterator();
        if (o == null) {
            while (i.hasNext()) {
                if (i.next() == null) {
                    result++;
                }
            }
        } else {
            while (i.hasNext()) {
                if (o.equals(i.next())) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * Returns an unmodifiable view of the specified list. This method allows
     * modules to provide users with "read-only" access to internal lists. Query
     * operations on the returned list "read through" to the specified list, and
     * attempts to modify the returned list, whether direct or via its iterator,
     * result in an {@code IllegalStateException}.
     *
     * @param list the list for which an unmodifiable view is to be returned
     * @return an unmodifiable view of the specified list
     */
    public static List unmodifiableList(List list) {
        return new UnmodifiableList(list);
    }

    /**
     * Returns an unmodifiable view of the specified collection. This method
     * allows modules to provide users with "read-only" access to internal
     * collections. Query operations on the returned collection "read through"
     * to the specified collection, and attempts to modify the returned
     * collection, whether direct or via its iterator, result in an
     * {@code IllegalStateException}
     *
     * The returned collection does <i>not</i> pass the hashCode and equals
     * operations through to the backing collection, but relies on
     * {@code Object}'s {@code equals} and {@code hashCode} methods. This is
     * necessary to preserve the contracts of these operations in the case that
     * the backing collection is a list.
     *
     * @param c the collection for which an unmodifiable view is to be returned
     * @return an unmodifiable view of the specified collection
     */
    public static Collection unmodifiableCollection(Collection c) {
        return new UnmodifiableCollection(c);
    }

    private static class UnmodifiableList extends UnmodifiableCollection implements List {

        final List list;

        private UnmodifiableList(List list) {
            super(list);
            this.list = list;
        }

        public boolean equals(Object o) {
            return o == this || (o instanceof List && list.equals(o));
        }

        public int hashCode() {
            return list.hashCode();
        }

        public Object get(int index) {
            return list.get(index);
        }

        public Object set(int index, Object element) {
            throw new IllegalStateException();
        }

        public void add(int index, Object element) {
            throw new IllegalStateException();
        }

        public Object remove(int index) {
            throw new IllegalStateException();
        }

        public int indexOf(Object o) {
            return list.indexOf(o);
        }

        public int lastIndexOf(Object o) {
            return list.lastIndexOf(o);
        }

        public void addAll(int index, Collection c) {
            throw new IllegalStateException();
        }

        public List subList(int fromIndex, int toIndex) {
            return new UnmodifiableList(list.subList(fromIndex, toIndex));
        }
    }

    private static class UnmodifiableCollection implements Collection {

        final Collection c;

        private UnmodifiableCollection(Collection c) {
            if (c == null) {
                throw new NullPointerException();
            }
            this.c = c;
        }

        public int size() {
            return c.size();
        }

        public boolean isEmpty() {
            return c.isEmpty();
        }

        public boolean contains(Object o) {
            return c.contains(o);
        }

        public Object[] toArray() {
            return c.toArray();
        }

        public String toString() {
            return c.toString();
        }

        public Iterator iterator() {
            return new Iterator() {
                private final Iterator i = c.iterator();

                public boolean hasNext() {
                    return i.hasNext();
                }

                public Object next() {
                    return i.next();
                }

                public void remove() {
                    throw new IllegalStateException();
                }
            };
        }

        public void add(Object e) {
            throw new IllegalStateException();
        }

        public void remove(Object o) {
            throw new IllegalStateException();
        }

        public boolean containsAll(Collection coll) {
            return c.containsAll(coll);
        }

        public void addAll(Collection coll) {
            throw new IllegalStateException();
        }

        public void removeAll(Collection coll) {
            throw new IllegalStateException();
        }

        public void retainAll(Collection coll) {
            throw new IllegalStateException();
        }

        public void clear() {
            throw new IllegalStateException();
        }
    }
}
