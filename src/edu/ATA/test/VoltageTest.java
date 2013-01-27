package edu.ATA.test;

import edu.ATA.main.Robot;
import edu.ATA.module.Modules;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class VoltageTest extends Robot implements Modules {

    public void robotInit() {
        test.enable();
    }

    public void teleopPeriodic() {
        test.teleop();
    }
}
