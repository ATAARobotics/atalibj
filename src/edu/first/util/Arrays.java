package edu.first.util;

import edu.first.lang.Comparator;
import edu.first.util.list.Collections;
import edu.first.util.list.List;

/**
 * This class contains various methods for manipulating arrays (such as sorting
 * and searching).
 *
 * The methods in this class all throw a NullPointerException if the specified
 * array reference is null.
 *
 * @since June 21 13
 * @author Joel Gallant
 */
public final class Arrays {

    // cannot be subclassed or instantiated
    private Arrays() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Returns an array that contains elements from the {@code o} array. Its
     * size will either be {@code size} or the size of the given array,
     * depending on which is smaller.
     *
     * @param o array with elements to copy
     * @param size size to use (provided it is equal to or smaller than the
     * length of {@code o})
     * @return a copy of the array with a minimum size (size will never be
     * larger than original array)
     */
    public static Object[] copyOf(Object[] o, int size) {
        return copy(o, new Object[MathUtils.min(o.length, size)]);
    }

    /**
     * Returns the array as a list with every element in the array in the list.
     *
     * @param a array with elements to put in the list
     * @return list with the elements of the array
     */
    public static List asList(Object[] a) {
        return Collections.asList(a);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     */
    public static void sort(long[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted the
     * index of the last element, exclusive, to be sorted
     */
    public static void sort(long[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     */
    public static void sort(int[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     */
    public static void sort(int[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     */
    public static void sort(short[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     */
    public static void sort(short[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     */
    public static void sort(char[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     */
    public static void sort(char[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     */
    public static void sort(byte[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     */
    public static void sort(byte[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     */
    public static void sort(double[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     */
    public static void sort(double[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     */
    public static void sort(float[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     */
    public static void sort(float[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @param c the comparator to determine the order of the array
     */
    public static void sort(Object[] a, Comparator c) {
        com.sun.squawk.util.Arrays.sort(a, c);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     * @param c the comparator to determine the order of the array
     */
    public static void sort(Object[] a, int fromIndex, int toIndex, Comparator c) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex, c);
    }

    /**
     * Searches the specified array for the specified value using the binary
     * search algorithm. The array must be sorted (as by the {@code  sort()}
     * method) prior to making this call. If it is not sorted, the results are
     * undefined. If the array contains multiple elements with the specified
     * value, there is no guarantee which one will be found.
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     * otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>. The
     * <i>insertion point</i> is defined as the point at which the key would be
     * inserted into the array: the index of the first element greater than the
     * key, or <tt>a.length</tt> if all elements in the array are less than the
     * specified key. Note that this guarantees that the return value will be 0
     * if and only if the key is found
     */
    public static int binarySearch(long[] a, long key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    /**
     * Searches the specified array for the specified value using the binary
     * search algorithm. The array must be sorted (as by the {@code  sort()}
     * method) prior to making this call. If it is not sorted, the results are
     * undefined. If the array contains multiple elements with the specified
     * value, there is no guarantee which one will be found.
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     * otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>. The
     * <i>insertion point</i> is defined as the point at which the key would be
     * inserted into the array: the index of the first element greater than the
     * key, or <tt>a.length</tt> if all elements in the array are less than the
     * specified key. Note that this guarantees that the return value will be 0
     * if and only if the key is found
     */
    public static int binarySearch(int[] a, int key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    /**
     * Searches the specified array for the specified value using the binary
     * search algorithm. The array must be sorted (as by the {@code  sort()}
     * method) prior to making this call. If it is not sorted, the results are
     * undefined. If the array contains multiple elements with the specified
     * value, there is no guarantee which one will be found.
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     * otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>. The
     * <i>insertion point</i> is defined as the point at which the key would be
     * inserted into the array: the index of the first element greater than the
     * key, or <tt>a.length</tt> if all elements in the array are less than the
     * specified key. Note that this guarantees that the return value will be 0
     * if and only if the key is found
     */
    public static int binarySearch(short[] a, short key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    /**
     * Searches the specified array for the specified value using the binary
     * search algorithm. The array must be sorted (as by the {@code  sort()}
     * method) prior to making this call. If it is not sorted, the results are
     * undefined. If the array contains multiple elements with the specified
     * value, there is no guarantee which one will be found.
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     * otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>. The
     * <i>insertion point</i> is defined as the point at which the key would be
     * inserted into the array: the index of the first element greater than the
     * key, or <tt>a.length</tt> if all elements in the array are less than the
     * specified key. Note that this guarantees that the return value will be 0
     * if and only if the key is found
     */
    public static int binarySearch(char[] a, char key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    /**
     * Searches the specified array for the specified value using the binary
     * search algorithm. The array must be sorted (as by the {@code  sort()}
     * method) prior to making this call. If it is not sorted, the results are
     * undefined. If the array contains multiple elements with the specified
     * value, there is no guarantee which one will be found.
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     * otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>. The
     * <i>insertion point</i> is defined as the point at which the key would be
     * inserted into the array: the index of the first element greater than the
     * key, or <tt>a.length</tt> if all elements in the array are less than the
     * specified key. Note that this guarantees that the return value will be 0
     * if and only if the key is found
     */
    public static int binarySearch(byte[] a, byte key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    /**
     * Searches the specified array for the specified value using the binary
     * search algorithm. The array must be sorted (as by the {@code  sort()}
     * method) prior to making this call. If it is not sorted, the results are
     * undefined. If the array contains multiple elements with the specified
     * value, there is no guarantee which one will be found.
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     * otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>. The
     * <i>insertion point</i> is defined as the point at which the key would be
     * inserted into the array: the index of the first element greater than the
     * key, or <tt>a.length</tt> if all elements in the array are less than the
     * specified key. Note that this guarantees that the return value will be 0
     * if and only if the key is found
     */
    public static int binarySearch(double[] a, double key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    /**
     * Searches the specified array for the specified value using the binary
     * search algorithm. The array must be sorted (as by the {@code  sort()}
     * method) prior to making this call. If it is not sorted, the results are
     * undefined. If the array contains multiple elements with the specified
     * value, there is no guarantee which one will be found.
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     * otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>. The
     * <i>insertion point</i> is defined as the point at which the key would be
     * inserted into the array: the index of the first element greater than the
     * key, or <tt>a.length</tt> if all elements in the array are less than the
     * specified key. Note that this guarantees that the return value will be 0
     * if and only if the key is found
     */
    public static int binarySearch(float[] a, float key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    /**
     * Searches the specified array for the specified value using the binary
     * search algorithm. The array must be sorted (as by the {@code  sort()}
     * method) prior to making this call. If it is not sorted, the results are
     * undefined. If the array contains multiple elements with the specified
     * value, there is no guarantee which one will be found.
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @param c the comparator to determine the order of the array
     * @return index of the search key, if it is contained in the array;
     * otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>. The
     * <i>insertion point</i> is defined as the point at which the key would be
     * inserted into the array: the index of the first element greater than the
     * key, or <tt>a.length</tt> if all elements in the array are less than the
     * specified key. Note that this guarantees that the return value will be 0
     * if and only if the key is found
     */
    public static int binarySearch(Object[] a, Object key, Comparator c) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key, c);
    }

    /**
     * Make a new array and initializes its contents from the contents of a
     * specified array.
     *
     * @param src the array to be copied
     * @param dest the array to copy to
     * @return the array with everything copied
     */
    public static Object[] copy(Object[] src, Object[] dest) {
        return com.sun.squawk.util.Arrays.copy(src, 0, dest, 0, src.length);
    }

    /**
     * Make a new array and initializes its contents from the contents of a
     * specified array.
     *
     * @param src the source array
     * @param srcPos starting position in the source array
     * @param destLength the length of the destination array
     * @param destPos starting position in the destination data
     * @param length the number of array elements to be copied
     * @return the array with everything copied
     */
    public static boolean[] copy(boolean[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    /**
     * Make a new array and initializes its contents from the contents of a
     * specified array.
     *
     * @param src the source array
     * @param srcPos starting position in the source array
     * @param destLength the length of the destination array
     * @param destPos starting position in the destination data
     * @param length the number of array elements to be copied
     * @return the array with everything copied
     */
    public static byte[] copy(byte[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    /**
     * Make a new array and initializes its contents from the contents of a
     * specified array.
     *
     * @param src the source array
     * @param srcPos starting position in the source array
     * @param destLength the length of the destination array
     * @param destPos starting position in the destination data
     * @param length the number of array elements to be copied
     * @return the array with everything copied
     */
    public static char[] copy(char[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    /**
     * Make a new array and initializes its contents from the contents of a
     * specified array.
     *
     * @param src the source array
     * @param srcPos starting position in the source array
     * @param destLength the length of the destination array
     * @param destPos starting position in the destination data
     * @param length the number of array elements to be copied
     * @return the array with everything copied
     */
    public static double[] copy(double[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    /**
     * Make a new array and initializes its contents from the contents of a
     * specified array.
     *
     * @param src the source array
     * @param srcPos starting position in the source array
     * @param destLength the length of the destination array
     * @param destPos starting position in the destination data
     * @param length the number of array elements to be copied
     * @return the array with everything copied
     */
    public static float[] copy(float[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    /**
     * Make a new array and initializes its contents from the contents of a
     * specified array.
     *
     * @param src the source array
     * @param srcPos starting position in the source array
     * @param destLength the length of the destination array
     * @param destPos starting position in the destination data
     * @param length the number of array elements to be copied
     * @return the array with everything copied
     */
    public static int[] copy(int[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    /**
     * Make a new array and initializes its contents from the contents of a
     * specified array.
     *
     * @param src the source array
     * @param srcPos starting position in the source array
     * @param destLength the length of the destination array
     * @param destPos starting position in the destination data
     * @param length the number of array elements to be copied
     * @return the array with everything copied
     */
    public static long[] copy(long[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    /**
     * Make a new array and initializes its contents from the contents of a
     * specified array.
     *
     * @param src the source array
     * @param srcPos starting position in the source array
     * @param dest the destination array
     * @param destPos starting position in the destination data
     * @param length the number of array elements to be copied
     * @return the array with everything copied
     */
    public static Object[] copy(Object[] src, int srcPos, Object[] dest, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, dest, destPos, length);
    }

    /**
     * Returns a new array with every element of the source array.
     *
     * @param array source array
     * @return array with elements from source array
     */
    public static Object[] copy(Object[] array) {
        return com.sun.squawk.util.Arrays.copy(array);
    }

    /**
     * Make a new array and initializes its contents from the contents of a
     * specified array.
     *
     * @param src the source array
     * @param srcPos starting position in the source array
     * @param destLength the length of the destination array
     * @param destPos starting position in the destination data
     * @param length the number of array elements to be copied
     * @return the array with everything copied
     */
    public static short[] copy(short[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of Objects are
     * <i>equal</i> to one another. The two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal. Two objects <tt>e1</tt>
     * and <tt>e2</tt> are considered <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>. In other words, the two arrays are equal if they
     * contain the same elements in the same order. Also, two array references
     * are considered equal if both are <tt>null</tt>.
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return <tt>true</tt> if the two arrays are equal
     */
    public static boolean equals(long[] a, long[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of Objects are
     * <i>equal</i> to one another. The two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal. Two objects <tt>e1</tt>
     * and <tt>e2</tt> are considered <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>. In other words, the two arrays are equal if they
     * contain the same elements in the same order. Also, two array references
     * are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return <tt>true</tt> if the two arrays are equal
     */
    public static boolean equals(int[] a, int[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of Objects are
     * <i>equal</i> to one another. The two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal. Two objects <tt>e1</tt>
     * and <tt>e2</tt> are considered <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>. In other words, the two arrays are equal if they
     * contain the same elements in the same order. Also, two array references
     * are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return <tt>true</tt> if the two arrays are equal
     */
    public static boolean equals(short[] a, short[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of Objects are
     * <i>equal</i> to one another. The two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal. Two objects <tt>e1</tt>
     * and <tt>e2</tt> are considered <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>. In other words, the two arrays are equal if they
     * contain the same elements in the same order. Also, two array references
     * are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return <tt>true</tt> if the two arrays are equal
     */
    public static boolean equals(char[] a, char[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of Objects are
     * <i>equal</i> to one another. The two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal. Two objects <tt>e1</tt>
     * and <tt>e2</tt> are considered <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>. In other words, the two arrays are equal if they
     * contain the same elements in the same order. Also, two array references
     * are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return <tt>true</tt> if the two arrays are equal
     */
    public static boolean equals(byte[] a, byte[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of Objects are
     * <i>equal</i> to one another. The two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal. Two objects <tt>e1</tt>
     * and <tt>e2</tt> are considered <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>. In other words, the two arrays are equal if they
     * contain the same elements in the same order. Also, two array references
     * are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return <tt>true</tt> if the two arrays are equal
     */
    public static boolean equals(boolean[] a, boolean[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of Objects are
     * <i>equal</i> to one another. The two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal. Two objects <tt>e1</tt>
     * and <tt>e2</tt> are considered <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>. In other words, the two arrays are equal if they
     * contain the same elements in the same order. Also, two array references
     * are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return <tt>true</tt> if the two arrays are equal
     */
    public static boolean equals(double[] a, double[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of Objects are
     * <i>equal</i> to one another. The two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal. Two objects <tt>e1</tt>
     * and <tt>e2</tt> are considered <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>. In other words, the two arrays are equal if they
     * contain the same elements in the same order. Also, two array references
     * are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return <tt>true</tt> if the two arrays are equal
     */
    public static boolean equals(float[] a, float[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of Objects are
     * <i>equal</i> to one another. The two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal. Two objects <tt>e1</tt>
     * and <tt>e2</tt> are considered <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>. In other words, the two arrays are equal if they
     * contain the same elements in the same order. Also, two array references
     * are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return <tt>true</tt> if the two arrays are equal
     */
    public static boolean equals(Object[] a, Object[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    /**
     * Returns a hashcode for a given object. If object is not an array, the
     * returned hashcode will be the same as if object.hashCode() was called. If
     * object is an array, then computed hashcode follows the general contract
     * of {@link Object.hashCode()} with respect to the equals methods in this
     * class. That is, any two array objects that are equal according to the
     * appropriate equals method will have the same hashcode.
     *
     * @param object the object for which a hash code is required
     * @return the hash code of the object
     */
    public static int hashCode(Object object) {
        return com.sun.squawk.util.Arrays.hashCode(object);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(long[] a, long val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(long[] a, int fromIndex, int toIndex, long val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(int[] a, int val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(int[] a, int fromIndex, int toIndex, int val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(short[] a, short val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(short[] a, int fromIndex, int toIndex, short val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(char[] a, char val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(char[] a, int fromIndex, int toIndex, char val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(byte[] a, byte val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(byte[] a, int fromIndex, int toIndex, byte val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(boolean[] a, boolean val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(boolean[] a, int fromIndex, int toIndex, boolean val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(double[] a, double val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(double[] a, int fromIndex, int toIndex, double val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(float[] a, float val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(float[] a, int fromIndex, int toIndex, float val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(Object[] a, Object val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    /**
     * Assigns the specified value to each element of the specified array.
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     * @param val the value to be stored in all elements of the array
     */
    public static void fill(Object[] a, int fromIndex, int toIndex, Object val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }
}
