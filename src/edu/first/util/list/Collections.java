/*
 * Copyright (c) 1997, 2012, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIEObjectARY/CONFIDENObjectIAL. Use is subject to license terms.
 */
package edu.first.util.list;

import com.sun.squawk.util.Arrays;
import edu.first.lang.Comparator;
import java.util.NoSuchElementException;

/**
 * Utilities for {@link Collection}. Manipulates the collections using various
 * methods to change the collections.
 *
 * @since May 18 13
 * @author Joel Gallant
 */
public class Collections {

    // cannot be subclassed or instantiated
    private Collections() throws IllegalStateException {
        throw new IllegalStateException();
    }

    /**
     * Sorts the specified list into ascending order, according to the
     * {@linkplain Comparator natural ordering} of its elements. All elements in
     * the list must implement the {@link Comparator} interface. Furthermore,
     * all elements in the list must be
     * <i>mutually comparable</i> (that is, {@code e1.compareTo(e2)} must not
     * throw a {@code ClassCastException} for any elements {@code e1} and
     * {@code e2} in the list).
     *
     * <p> This sort is guaranteed to be <i>stable</i>: equal elements will not
     * be reordered as a result of the sort.
     *
     * <p> The specified list must be modifiable, but need not be resizable.
     *
     * <p> Implementation note: This implementation is a stable, adaptive,
     * iterative mergesort that requires far fewer than n lg(n) comparisons when
     * the input array is partially sorted, while offering the performance of a
     * traditional mergesort when the input array is randomly ordered. If the
     * input array is nearly sorted, the implementation requires approximately n
     * comparisons. Temporary storage requirements vary from a small constant
     * for nearly sorted input arrays to n/2 object references for randomly
     * ordered input arrays.
     *
     * <p> The implementation takes equal advantage of ascending and descending
     * order in its input array, and can take advantage of ascending and
     * descending order in different parts of the same input array. It is
     * well-suited to merging two or more sorted arrays: simply concatenate the
     * arrays and sort the resulting array.
     *
     * <p> The implementation was adapted from Tim Peters's list sort for Python
     * (<a
     * href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>). It uses techiques from Peter McIlroy's "Optimistic Sorting
     * and Information Theoretic Complexity", in Proceedings of the Fourth
     * Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474, January
     * 1993.
     *
     * <p> This implementation dumps the specified list into an array, sorts the
     * array, and iterates over the list resetting each element from the
     * corresponding position in the array. This avoids the n<sup>2</sup> log(n)
     * performance that would result from attempting to sort a linked list in
     * place.
     *
     * @param list the list to be sorted.
     * @param c way to sort the list
     * @throws ClassCastException if the list contains elements that are not
     * <i>mutually comparable</i> (for example, strings and integers).
     * @throws IllegalStateException if the specified list's list-iterator does
     * not support the {@code set} operation.
     * @throws IllegalArgumentException (optional) if the implementation detects
     * that the natural ordering of the list elements is found to violate the
     * {@link Comparator} contract
     */
    public static void sort(List list, Comparator c) {
        Object[] a = list.toArray();
        Arrays.sort(a, c);
        list.clear();
        addAll(list, a);
    }

    /**
     * Adds all of the specified elements to the specified collection. Elements
     * to be added may be specified individually or as an array. Objecthe
     * behavior of this convenience method is identical to that of
     * {@code c.addAll(Collections.asList(elements))}, but this method is likely to
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
     * @return how often object appears in the collection
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
     * Returns an unmodifiable view of the specified list. Objecthis method
     * allows modules to provide users with "read-only" access to internal
     * lists. Query operations on the returned list "read through" to the
     * specified list, and attempts to modify the returned list, whether direct
     * or via its iterator, result in an {@code IllegalStateException}.
     *
     * @param list the list for which an unmodifiable view is to be returned
     * @return an unmodifiable view of the specified list
     */
    public static List unmodifiableList(List list) {
        return new UnmodifiableList(list);
    }

    /**
     * Returns an unmodifiable view of the specified collection. Objecthis
     * method allows modules to provide users with "read-only" access to
     * internal collections. Query operations on the returned collection "read
     * through" to the specified collection, and attempts to modify the returned
     * collection, whether direct or via its iterator, result in an
     * {@code IllegalStateException}
     *
     * Objecthe returned collection does <i>not</i> pass the hashCode and equals
     * operations through to the backing collection, but relies on
     * {@code Object}'s {@code equals} and {@code hashCode} methods. Objecthis
     * is necessary to preserve the contracts of these operations in the case
     * that the backing collection is a list.
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

                public Object next() throws NoSuchElementException {
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

    /**
     * Returns the array as a list with every element in the array in the list.
     *
     * @param a array with elements to put in the list
     * @return list with the elements of the array
     */
    public static List asList(Object[] a) {
        ArrayList list = new ArrayList(a.length * 2);
        addAll(list, a);
        return list;
    }

    /**
     * Returns an optimized empty list that is built to be empty.
     *
     * @return list taking minimum space
     */
    public static List emptyList() {
        return new ArrayList(0);
    }

    /**
     * Returns a synchronized (thread-safe) list backed by the specified list.
     *
     * <p> It is imperative that the user manually synchronize on the returned
     * list when iterating over it:
     * <pre>
     *  List list = Collections.synchronizedList(new ArrayList());
     *      ...
     *  synchronized (list) {
     *      Iterator i = list.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     *
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * @param list the list to be "wrapped" in a synchronized list
     * @return a synchronized view of the specified list
     */
    public static List synchronizedList(List list) {
        return new SynchronizedList(list);
    }

    /**
     * Returns a synchronized (thread-safe) list backed by the specified list.
     *
     * <p> It is imperative that the user manually synchronize on the returned
     * list when iterating over it:
     * <pre>
     *  List list = Collections.synchronizedList(new ArrayList(), lock);
     *      ...
     *  synchronized (lock) {
     *      Iterator i = list.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     *
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * @param list the list to be "wrapped" in a synchronized list
     * @param lock the object to synchronize all operations on
     * @return a synchronized view of the specified list
     */
    public static List synchronizedList(List list, Object lock) {
        return new SynchronizedList(list, lock);
    }

    /**
     * Returns a synchronized (thread-safe) collection backed by the specified
     * collection.
     *
     * <p> It is imperative that the user manually synchronize on the returned
     * collection when iterating over it:
     * <pre>
     *  Collection c = Collections.synchronizedCollection(myCollection);
     *     ...
     *  synchronized (c) {
     *      Iterator i = c.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *         foo(i.next());
     *  }
     * </pre> Failure to follow this advice may result in non-deterministic
     * behavior.
     *
     * <p>The returned collection does <i>not</i> pass the {@code hashCode} and
     * {@code equals} operations through to the backing collection, but relies
     * on {@code Object}'s equals and hashCode methods. This is necessary to
     * preserve the contracts of these operations in the case that the backing
     * collection is a set or a list.
     *
     * @param collection the collection to be "wrapped" in a synchronized
     * collection
     * @return a synchronized view of the specified collection
     */
    public static Collection synchronizedCollection(Collection collection) {
        return new SynchronizedCollection(collection);
    }

    /**
     * Returns a synchronized (thread-safe) collection backed by the specified
     * collection.
     *
     * <p> It is imperative that the user manually synchronize on the returned
     * collection when iterating over it:
     * <pre>
     *  Collection c = Collections.synchronizedCollection(myCollection, lock);
     *     ...
     *  synchronized (lock) {
     *      Iterator i = c.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *         foo(i.next());
     *  }
     * </pre> Failure to follow this advice may result in non-deterministic
     * behavior.
     *
     * <p> The returned collection does <i>not</i> pass the {@code hashCode} and
     * {@code equals} operations through to the backing collection, but relies
     * on {@code Object}'s equals and hashCode methods. This is necessary to
     * preserve the contracts of these operations in the case that the backing
     * collection is a set or a list.
     *
     * @param collection the collection to be "wrapped" in a synchronized
     * collection
     * @param lock the object to synchronize all operations on
     * @return a synchronized view of the specified collection
     */
    public static Collection synchronizedCollection(Collection collection, Object lock) {
        return new SynchronizedCollection(collection, lock);
    }

    private static class SynchronizedCollection implements Collection {

        final Collection c;  // Backing Collection
        final Object lock;     // Object on which to synchronize

        SynchronizedCollection(Collection c) {
            if (c == null) {
                throw new NullPointerException();
            }
            this.c = c;
            lock = this;
        }

        SynchronizedCollection(Collection c, Object lock) {
            this.c = c;
            this.lock = lock;
        }

        public int size() {
            synchronized (lock) {
                return c.size();
            }
        }

        public boolean isEmpty() {
            synchronized (lock) {
                return c.isEmpty();
            }
        }

        public boolean contains(Object o) {
            synchronized (lock) {
                return c.contains(o);
            }
        }

        public Object[] toArray() {
            synchronized (lock) {
                return c.toArray();
            }
        }

        public Iterator iterator() {
            return c.iterator(); // Must be manually synched by user!
        }

        public void add(Object e) {
            synchronized (lock) {
                c.add(e);
            }
        }

        public void remove(Object o) {
            synchronized (lock) {
                c.remove(o);
            }
        }

        public boolean containsAll(Collection coll) {
            synchronized (lock) {
                return c.containsAll(coll);
            }
        }

        public void addAll(Collection coll) {
            synchronized (lock) {
                c.addAll(coll);
            }
        }

        public void removeAll(Collection coll) {
            synchronized (lock) {
                c.removeAll(coll);
            }
        }

        public void retainAll(Collection coll) {
            synchronized (lock) {
                c.retainAll(coll);
            }
        }

        public void clear() {
            synchronized (lock) {
                c.clear();
            }
        }

        public String toString() {
            synchronized (lock) {
                return c.toString();
            }
        }
    }

    private static class SynchronizedList extends SynchronizedCollection implements List {

        final List list;

        private SynchronizedList(List list) {
            super(list);
            this.list = list;
        }

        private SynchronizedList(List list, Object lock) {
            super(list, lock);
            this.list = list;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            synchronized (lock) {
                return list.equals(o);
            }
        }

        public int hashCode() {
            synchronized (lock) {
                return list.hashCode();
            }
        }

        public Object get(int index) {
            synchronized (lock) {
                return list.get(index);
            }
        }

        public Object set(int index, Object element) {
            synchronized (lock) {
                return list.set(index, element);
            }
        }

        public void add(int index, Object element) {
            synchronized (lock) {
                list.add(index, element);
            }
        }

        public Object remove(int index) {
            synchronized (lock) {
                return list.remove(index);
            }
        }

        public int indexOf(Object o) {
            synchronized (lock) {
                return list.indexOf(o);
            }
        }

        public int lastIndexOf(Object o) {
            synchronized (lock) {
                return list.lastIndexOf(o);
            }
        }

        public void addAll(int index, Collection c) {
            synchronized (lock) {
                list.addAll(index, c);
            }
        }

        public List subList(int fromIndex, int toIndex) {
            synchronized (lock) {
                return new SynchronizedList(list.subList(fromIndex, toIndex), lock);
            }
        }
    }

    /**
     * Returns true if the specified arguments are equal, or both null.
     */
    private static boolean eq(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }
}
