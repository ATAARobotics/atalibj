package main.java.ca.fourthreethreefour.module.actuators;

import edu.first.module.actuators.SpeedController;
import edu.first.module.actuators.SpeedControllerGroup;
import edu.first.module.subsystems.Subsystem;

public class TalonSRXModuleGroup extends Subsystem implements SpeedController {

	private final SpeedControllerGroup group;
	
	public TalonSRXModuleGroup(TalonSRXModule[] group) {
		super(group);
		this.group = new SpeedControllerGroup(group);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSpeed(double speed) {
		group.setSpeed(speed);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRawSpeed(int speed) {
		group.setRawSpeed(speed);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getSpeed() {
		return group.getSpeed();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRawSpeed() {
		return group.getRawSpeed();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRate(double rate) {
		group.set(rate);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void set(double value) {
		group.set(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getRate() {
		return group.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double get() {
		return group.get();
	}
	
}
