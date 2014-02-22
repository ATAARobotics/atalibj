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

    public boolean atLimit() {
        return !limit.getPosition();
    }

    public void set(double value) {
        if (atLimit()) {
            super.set(0);
        } else {
            super.set(oneWay(value));
        }
    }

    public void setRate(double rate) {
        if (atLimit()) {
            super.setRate(0);
        } else {
            super.setRate(oneWay(rate));
        }
    }

    public void setRawSpeed(int speed) {
        if (atLimit()) {
            super.setRawSpeed(127);
        } else {
            super.setRawSpeed((int) oneWay(speed));
        }
    }

    public void setSpeed(double speed) {
        if (atLimit()) {
            super.setSpeed(0);
        } else {
            super.setSpeed(oneWay(speed));
        }
    }

    private double oneWay(double speed) {
        return -MathUtils.abs(speed);
    }
}
