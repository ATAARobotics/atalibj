package ata2014.modules;

import edu.first.module.actuators.TalonModule;
import edu.first.module.sensors.DigitalInput;
import edu.first.util.MathUtils;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class WinchMotor extends TalonModule {

    private final DigitalInput limit;

    public WinchMotor(DigitalInput limit, int channel) {
        super(channel);
        this.limit = limit;
    }

    public void set(double value) {
        if (limit.getPosition()) {
            super.set(oneWay(value));
        } else {
            super.set(0);
        }
    }

    public void setRate(double rate) {
        if (limit.getPosition()) {
            super.setRate(oneWay(rate));
        } else {
            super.setRate(0);
        }
    }

    public void setRawSpeed(int speed) {
        if (limit.getPosition()) {
            super.setRawSpeed((int) oneWay(speed));
        } else {
            super.setRawSpeed(0);
        }
    }

    public void setSpeed(double speed) {
        if (limit.getPosition()) {
            super.setSpeed(oneWay(speed));
        } else {
            super.setSpeed(0);
        }
    }

    private double oneWay(double speed) {
        return -MathUtils.abs(speed);
    }
}
