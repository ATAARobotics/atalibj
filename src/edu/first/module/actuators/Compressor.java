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
