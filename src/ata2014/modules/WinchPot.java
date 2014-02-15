package ata2014.modules;

import edu.first.module.sensors.AnalogInput;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class WinchPot extends AnalogInput {

    private final double min, max;

    public WinchPot(double min, double max, int channel) {
        super(channel);
        this.min = min;
        this.max = max;
    }

    public double get() {
        return (super.get() - min) / max;
    }

    public double getAverage() {
        return (super.getAverage() - min) / max;
    }

    public double getVoltage() {
        return super.getAverage();
    }
}
