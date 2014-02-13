package ata2014.modules;

import edu.first.module.actuators.VictorModule;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class InversedVictor extends VictorModule {

    public InversedVictor(int port) {
        super(port);
    }

    public void set(double value) {
        super.set(-value);
    }

    public void setRawSpeed(int speed) {
        super.setRawSpeed(-speed);
    }

    public void setSpeed(double speed) {
        super.setSpeed(-speed);
    }

}
