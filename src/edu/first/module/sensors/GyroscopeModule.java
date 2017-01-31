package edu.first.module.sensors;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * Module implementation of the KoP gyroscope. Composes and wraps {@link Gyro},
 * giving it module properties to fit with the standard in atalibj.
 *
 * @since May 22 13
 * @author Joel Gallant
 */
public class GyroscopeModule extends Module.StandardModule implements Gyroscope {

    private final AnalogGyro gyro;

    /**
     * Constructs the module with the gyro object underneath this class to call
     * methods from.
     *
     * @throws NullPointerException when gyro is null
     * @param gyro the composing instance which will return values
     */
    protected GyroscopeModule(AnalogGyro gyro) {
        if (gyro == null) {
            throw new NullPointerException("Null gyro given");
        }
        this.gyro = gyro;
    }

    /**
     * Constructs the module with the port on the analog sidecar.
     *
     * @param channel port on sidecar
     */
    public GyroscopeModule(int channel) {
        this(new AnalogGyro(channel));
    }

    /**
     * Constructs the module with the channel of the gyroscope.
     *
     * @param channel analog channel to find gyro on
     */
    public GyroscopeModule(AnalogInput channel) {
        this(new AnalogGyro(channel));
    }

    /**
     * Resets the gyro to zero.
     */
    @Override
    public void init() {
        gyro.reset();
    }

    /**
     * Resets the gyro to zero.
     */
    @Override
    protected void enableModule() {
        gyro.reset();
    }

    /*
     * Does not do anything.
     */
    @Override
    protected void disableModule() {
    }

    /**
     * Resets the cumulative angle to zero.
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public void reset() {
        ensureEnabled();
        gyro.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSensitivity(double voltsPerDegreePerSecond) {
        gyro.setSensitivity(voltsPerDegreePerSecond);
  }

    /**
     * Returns the angle of the gyroscope, provided this method is enabled.
     *
     * @return cumulative angle given by gyroscope
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public double getAngle() {
        ensureEnabled();
        return gyro.getAngle();
    }

    /**
     * Returns the angle of the gyroscope, provided this method is enabled.
     *
     * @return cumulative angle given by gyroscope
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public double getPosition() {
        return getAngle();
    }

    /**
     * Returns the angle of the gyroscope, provided this method is enabled.
     *
     * @return cumulative angle given by gyroscope
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public double get() {
        return getAngle();
    }

	public void calibrate() {
		gyro.calibrate(); //TODO add custom functionality
		
	}

	public double getRate() {
		return gyro.getRate(); //TODO add custom functionality
	}

	public void free() {
		gyro.free(); //TODO add custom functionality
		
	}
}
