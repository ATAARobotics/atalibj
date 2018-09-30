package main.java.ca.fourthreethreefour.module.actuators;

import edu.first.module.Module;
import edu.first.module.actuators.SpeedController;

public class MotorModule extends Module.StandardModule implements SpeedController {
	
	private Module module;
	private SpeedController speedController;
	
	public static enum Type {
		TALON_SRX, VICTOR_SPX,
	}
	
	public MotorModule(Type type, int channel) {
		switch (type) {
			case TALON_SRX:
				TalonSRXModule talon = new TalonSRXModule(channel);
				this.module = talon;
				this.speedController = talon;
				break;
			case VICTOR_SPX:
				VictorSPXModule victor = new VictorSPXModule(channel);
				this.module = victor;
				this.speedController = victor;
				break;
		}
	}

	@Override
	public void init() {
		this.module.init();
	}

	@Override
	public void setSpeed(double speed) {
		this.speedController.setSpeed(speed);
	}

	@Override
	public void setRawSpeed(int speed) {
		this.speedController.setRawSpeed(speed);
	}

	@Override
	public double getSpeed() {
		return this.speedController.getSpeed();
	}

	@Override
	public int getRawSpeed() {
		return this.speedController.getRawSpeed();
	}

	@Override
	public void update() {
		this.speedController.update();
	}

	@Override
	public void setRate(double rate) {
		this.speedController.setRate(rate);
	}

	@Override
	public void set(double value) {
		this.speedController.set(value);
	}
	
	@Override
	public double getRate() {
		return this.speedController.getRate();
	}

	@Override
	public double get() {
		return this.speedController.get();
	}

	@Override
	protected void enableModule() {
		this.module.enable();
	}

	@Override
	protected void disableModule() {
		this.module.disable();
	}
	
}
