package edu.first.module.sensors;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * Module implementation of the KoP gyroscope. Composes and wraps {@link Gyro},
 * giving it module properties to fit with the standard in atalibj.
 *
 * @since May 22 13
 * @author Joel Gallant
 */
public class GyroscopeModule extends Module.StandardModule implements Gyroscope, Gyro {

    private final Gyro gyro;

    /**
     * Constructs the module with the gyro object underneath this class to call
     * methods from.
     *
     * @throws NullPointerException when gyro is null
     * @param gyro the composing instance which will return values
     */
    protected GyroscopeModule(Gyro gyro) {
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
        this(new GyroscopeModule(channel));
    }

    /**
     * Constructs the module with the channel of the gyroscope.
     *
     * @param channel analog channel to find gyro on
     */
    public GyroscopeModule(AnalogInput channel) {
        this(new GyroscopeModule(channel));
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
        //gyro.setSensitivity(voltsPerDegreePerSecond);
    	//TODO setSensitivity doesn't exist anymore, someone should actually bother to fix this
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

	@Override
	public void calibrate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void free() {
		// TODO Auto-generated method stub
		
	}
}
