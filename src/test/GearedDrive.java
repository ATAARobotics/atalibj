package test;

import edu.ATA.main.DefaultRobot;
import edu.ATA.module.Modules;
import edu.ATA.module.speedcontroller.SpikeRelay;

/**
 *
 * @author joel
 */
public class GearedDrive extends DefaultRobot implements Modules {

    public String name() {
        return "Geared Drive";
    }

    public void robotInit() throws Error {
        gearedDrivetrain.enable();
        compressor.enable();
        compressor.setDirection(SpikeRelay.FORWARDS_ONLY);
    }

    public void teleopInit() {
        compressor.set(true);
    }

    public void teleopPeriodic() throws Error {
        gearedDrivetrain.drive();
    }

    public void disabled() throws Error {
        compressor.set(false);
    }
}
