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
	 * Determines whether or not the compressor is currently at its limit.
	 *
	 * @return if the compressor is at its limit or not
	 */
	public boolean isAtLimit() {
		return safety.get();
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
		if (!isAtLimit()) {
			super.set(s);
		} else {
			super.set(State.OFF);
		}
	}
}
