/*
 * Copyright (c) 2003, 2010, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package edu.first.util.list;

/**
 * Implementing this interface allows an object to be the target of the
 * "foreach" statement.
 *
 * @since May 12 13
 * @author Joshua Bloch
 */
public interface Iterable {

    /**
     * Returns an iterator over a set of elements.
     *
     * @return an {@code Iterator}
     */
    public Iterator iterator();
}
