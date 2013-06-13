/*
 * Copyright (c) 1997, 2010, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package edu.first.util.list;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * An object that implements the Iterator interface generates a series of
 * elements, one at a time. Successive calls to the {@code next()} method
 * returns successive elements of the series.
 * <p>
 * For example, to print all elements of an {@link ArrayList}:
 * <pre>
 *     ArrayList a = new ArrayList();
 *     ...
 *     for (e = a.iterator(); e.hasNext();) {
 *         System.out.println(e.next());
 *     }
 * </pre>
 *
 * @see Iterable
 * @see Enumeration
 * @since May 12 13
 * @author Joshua Bloch
 */
public interface Iterator {

    /**
     * Returns {@code true} if the iteration has more elements. (In other words,
     * returns {@code true} if {@link #next} would return an element rather than
     * throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    Object next() throws NoSuchElementException;
}
