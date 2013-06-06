package edu.first.module.actuators;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.Relay;

/**
 * Controls an on-board pneumatic compressor normally without any added safety
 * measures. Has two states, on and off, to control whether or not air is being
 * compressed. Compression is ensured when state is on.
 *
 * @since June 06 13
 * @author Aaron Weiss
 */
public class CompressorModule extends Module.StartardModule implements Compressor {
	private final Relay relay;

	/**
	 * Constructs the compressor with the {@link Relay} object that the
	 * compressor interfaces with.
	 *
	 * @param relay the composing instance which perform the functions
	 */
	public CompressorModule(Relay relay) {
		if(relay == null) {
			throw new NullPointerException("Null relay given");
		}
		this.relay = relay;
	}

	/**
	 * Constructs the compressor with the channel on the digital module.
	 *
	 * @param channel the channel on the digital sidecar
	 */
	public CompressorModule(int channel) {
		this(new Relay(channel));
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected void enableModule() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected void disableModule() {
		set(State.ON);
	}

	/**
	 * {@inheritDoc}
	 */
	public void start() {
		set(State.ON);
	}

	/**
	 * {@inheritDoc}
	 */
	public void stop() {
		set(State.OFF);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalStateException when module is not enabled
	 */
	public void set(State s) {
		ensureEnabled();
		relay.set(convertValue(s));
	}

	/**
	 * {@inheritDoc}
	 */
	public State getState() {
		return convertDirection(relay.get());
	}

	private State convertDirection(Relay.Value v) {
		if (v == Relay.Value.kOn) {
			return State.ON;
		} else {
			return State.OFF;
		}
	}

	private Relay.Value convertValue(State s) {
		if (s == State.ON) {
			return Relay.Value.kOn;
		} else {
			return Relay.Value.kOff;
		}
	}
}
