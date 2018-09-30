package main.java.ca.fourthreethreefour.module.actuators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.first.module.Module;
import edu.first.module.actuators.SpeedController;

public class VictorSPXModule extends Module.StandardModule implements SpeedController {

	private final VictorSPX victor;
	
	protected VictorSPXModule(VictorSPX victor) {
		if (victor == null) {
            throw new NullPointerException("Null victor given");
		}
		this.victor = victor;
	}
	
	public VictorSPXModule(int channel) {
		this(new VictorSPX(channel));
	}
	
	@Override
	public void init() {
	}

	@Override
	protected void enableModule() {
	}
	
	@Override
	protected void disableModule() {
		victor.set(ControlMode.Disabled, 0);
	}

	@Override
	public double get() {
		return victor.getMotorOutputPercent();
	}

	@Override
	public double getRate() {
		return victor.getMotorOutputPercent();
	}

	@Override
	public int getRawSpeed() {
		return (int) victor.getMotorOutputVoltage();
	}

	@Override
	public double getSpeed() {
		return victor.getMotorOutputPercent();
	}

	@Override
	public void set(double value) {
		victor.set(ControlMode.PercentOutput, value);
	}

	@Override
	public void setRate(double value) {
		victor.set(ControlMode.Velocity, value);
	}

	@Override
	public void setRawSpeed(int value) {
		victor.set(ControlMode.PercentOutput, value);
	}

	@Override
	public void setSpeed(double value) {
		victor.set(ControlMode.PercentOutput, value);
	}

	@Override
	public void update() {
		victor.valueUpdated();
	}
	
	public double getAnalogIn() {
		return victor.getSensorCollection().getAnalogIn();
	}

}
