package ata2014.modules;

import edu.first.module.sensors.AnalogInput;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class InversedArmPot extends AnalogInput {

    public InversedArmPot(int channel) {
        super(channel);
    }

    public double get() {
        return 1 - super.get();
    }
}
