/*
 * The MIT License (MIT)
 *
 * Copyright (C) 2013 Aaron Weiss
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package edu.first.module.actuators;

import edu.first.util.Enum;

/**
 * General interface defining an on-board pneumatic compressor.
 * Has two distinct states, on and off that correspond to whether or not the
 * compressor is currently trying to run. Implementations may define safety
 * measures that may stop the compressor from running despite being on.
 *
 * @since June 06 13
 * @author Aaron Weiss
 */
public interface Compressor {
	/**
	 * Starts the compressor regardless of current state.
	 */
	public void start();

	/**
	 * Stops the compressor regardless of current state.
	 */
	public void stop();

	/**
	 * Sets the compressor to the specified state.
	 *
	 * @param s the new compressor state
	 */
	public void set(State s);

	/**
	 * Gets the compressor's current state.
	 *
	 * @return the current state of the compressor
	 */
	public State getState();

	/**
	 * Enum with the two different compressor states.
	 */
	public static final class State extends Enum {

		/**
		 * Turns on the compressor.
		 */
		public static final State ON = new State("ON");

		/**
		 * Turns off the compressor.
		 */
		public static final State OFF = new State("OFF");

		private State(String name) {
			super(name);
		}
	}
}
