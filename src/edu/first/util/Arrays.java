package edu.first.util;

import edu.first.lang.Comparator;

public final class Arrays {

    // cannot be subclassed or instantiated
    private Arrays() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static Object[] copyOf(Object[] o, int size) {
        Object[] c = new Object[size];
        System.arraycopy(o, 0, c, 0, Math.min(o.length, size));
        return c;
    }

    public static void sort(long[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    public static void sort(long[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    public static void sort(int[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    public static void sort(int[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    public static void sort(short[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    public static void sort(short[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    public static void sort(char[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    public static void sort(char[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    public static void sort(byte[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    public static void sort(byte[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    public static void sort(double[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    public static void sort(double[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    public static void sort(float[] a) {
        com.sun.squawk.util.Arrays.sort(a);
    }

    public static void sort(float[] a, int fromIndex, int toIndex) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex);
    }

    public static void sort(Object[] a, Comparator c) {
        com.sun.squawk.util.Arrays.sort(a, c);
    }

    public static void sort(Object[] a, int fromIndex, int toIndex, Comparator c) {
        com.sun.squawk.util.Arrays.sort(a, fromIndex, toIndex, c);
    }

    public static int binarySearch(long[] a, long key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    public static int binarySearch(int[] a, int key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    public static int binarySearch(short[] a, short key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    public static int binarySearch(char[] a, char key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    public static int binarySearch(byte[] a, byte key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    public static int binarySearch(double[] a, double key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    public static int binarySearch(float[] a, float key) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key);
    }

    public static int binarySearch(Object[] a, Object key, Comparator c) {
        return com.sun.squawk.util.Arrays.binarySearch(a, key, c);
    }

    public static Object[] copy(Object[] src, Object[] dest) {
        return com.sun.squawk.util.Arrays.copy(src, 0, dest, 0, src.length);
    }

    public static boolean[] copy(boolean[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    public static byte[] copy(byte[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    public static char[] copy(char[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    public static double[] copy(double[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    public static float[] copy(float[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    public static int[] copy(int[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    public static long[] copy(long[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    public static Object[] copy(Object[] src, int srcPos, Object[] dest, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, dest, destPos, length);
    }

    public static Object[] copy(Object[] array) {
        return com.sun.squawk.util.Arrays.copy(array);
    }

    public static short[] copy(short[] src, int srcPos, int destLength, int destPos, int length) {
        return com.sun.squawk.util.Arrays.copy(src, srcPos, destLength, destPos, length);
    }

    public static boolean equals(long[] a, long[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    public static boolean equals(int[] a, int[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    public static boolean equals(short[] a, short[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    public static boolean equals(char[] a, char[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    public static boolean equals(byte[] a, byte[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    public static boolean equals(boolean[] a, boolean[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    public static boolean equals(double[] a, double[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    public static boolean equals(float[] a, float[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    public static boolean equals(Object[] a, Object[] a2) {
        return com.sun.squawk.util.Arrays.equals(a, a2);
    }

    public static boolean equals(Object a, Object b) {
        return com.sun.squawk.util.Arrays.equals(a, b);
    }

    public static int hashCode(Object object) {
        return com.sun.squawk.util.Arrays.hashCode(object);
    }

    public static int length(Object array) {
        return com.sun.squawk.util.Arrays.length(array);
    }

    public static void fill(long[] a, long val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    public static void fill(long[] a, int fromIndex, int toIndex, long val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    public static void fill(int[] a, int val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    public static void fill(int[] a, int fromIndex, int toIndex, int val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    public static void fill(short[] a, short val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    public static void fill(short[] a, int fromIndex, int toIndex, short val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    public static void fill(char[] a, char val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    public static void fill(char[] a, int fromIndex, int toIndex, char val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    public static void fill(byte[] a, byte val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    public static void fill(byte[] a, int fromIndex, int toIndex, byte val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    public static void fill(boolean[] a, boolean val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    public static void fill(boolean[] a, int fromIndex, int toIndex, boolean val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    public static void fill(double[] a, double val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    public static void fill(double[] a, int fromIndex, int toIndex, double val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    public static void fill(float[] a, float val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    public static void fill(float[] a, int fromIndex, int toIndex, float val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }

    public static void fill(Object[] a, Object val) {
        com.sun.squawk.util.Arrays.fill(a, val);
    }

    public static void fill(Object[] a, int fromIndex, int toIndex, Object val) {
        com.sun.squawk.util.Arrays.fill(a, fromIndex, toIndex, val);
    }
}
