package test;

import edu.ATA.module.speedcontroller.SpikeRelay;

public class GearShiftingDrive extends BasicDrive {

    public void robotInit() throws Error {
        super.robotInit();
        spikeRelay.enable();
        spikeRelay.setDirection(SpikeRelay.FORWARDS_ONLY);
        gearShifter.enable();
    }

    public void teleopInit() {
        super.teleopInit();
        spikeRelay.set(true);
    }

    public void teleopPeriodic() throws Error {
        super.teleopPeriodic();
        gearShifter.set(controller.getRightBumper());
    }

    public void disabled() throws Error {
        super.disabled();
        spikeRelay.set(false);
    }
}
