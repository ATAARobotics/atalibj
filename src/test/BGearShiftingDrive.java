package test;

import edu.ATA.module.speedcontroller.SpikeRelay;

/**
 *
 * @author Team 4334
 */
public class BGearShiftingDrive extends ABasicDrive {
    
    private boolean first = true;

    public void robotInit() throws Error {
        super.robotInit();
        spikeRelay.enable();
        spikeRelay.setDirection(SpikeRelay.FORWARDS_ONLY);
        gearShifter1.enable();
        gearShifter2.enable();
        gearShifter3.enable();
        gearShifter4.enable();
    }

    public void teleopInit() {
        super.teleopInit();
        spikeRelay.set(SpikeRelay.ON);
    }

    public void teleopPeriodic() throws Error {
        super.teleopPeriodic();
        if(controller.getRightBumper()) {
            first = !first;
        }
        
        gearShifter1.set(first);
        gearShifter2.set(!first);
        gearShifter3.set(first);
        gearShifter4.set(!first);
    }

    public void disabled() throws Error {
        super.disabled();
        spikeRelay.set(false);
    }
}
