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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

/**
 * Controls an on-board pneumatic compressor normally with a safety switch as a
 * Digital Input device.  Has two states, on and off, to control whether or not
 * air is being compressed. Compression is not ensured when state is on.
 *
 * @since June 06 13
 * @author Aaron Weiss
 */
public class SafeCompressorModule extends CompressorModule {
	private final DigitalInput safety;

	/**
	 * Constructs the compressor with the {@link Relay} object that the
	 * compressor interfaces with and the {@link DigitalInput} object
	 * corresponding to the safety switch.
	 *
	 * @param relay the composing instance which perform the functions
	 * @param safety the safety switch for the compressor
	 */
	public SafeCompressorModule(Relay relay, DigitalInput safety) {
		super(relay);
		this.safety = safety;
	}

	/**
	 * Constructs the respective compressor with the channel on the digital
	 * module.
	 *
	 * @param compressorChannel the compressor's channel on the digital sidecar
	 * @param safetyChannel the safety switch's channel on the digital sidecar
	 */
	public SafeCompressorModule(int compressorChannel, int safetyChannel) {
		this(new Relay(compressorChannel), new DigitalInput(safetyChannel));
	}

	/**
	 * {@inheritDoc}
	 * Compression is not guaranteed when state is on as the safety may prevent it.
	 */
	public void start() {
		set(State.ON);
	}

	/**
	 * {@inheritDoc}
	 * Compression is not guaranteed when state is on as the safety may prevent it.
	 *
	 * @throws IllegalStateException when module is not enabled
	 */
	public void set(State s) {
		ensureEnabled();
		if (!safety.get()) {
			super.set(s);
		} else {
			super.set(State.OFF);
		}
	}
}
