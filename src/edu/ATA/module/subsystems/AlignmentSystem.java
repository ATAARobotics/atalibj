package edu.ATA.module.subsystems;

import edu.ATA.module.Module;
import edu.ATA.module.actuator.SolenoidModule;
import edu.ATA.module.subsystem.Subsystem;

/**
 * The subsystem class for the alignment system.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class AlignmentSystem extends Subsystem {

    private final static boolean LONG_ON_START = true;
    private final SolenoidModule shortAlign, longAlign, staticAlign;

    /**
     * The object to set the alignment system.
     *
     * @param shortAlign the the solenoid controlling the short piston
     * @param longAlign the the solenoid controlling the long piston
     * @param staticAlign the the solenoid controlling the static piston
     */
    public AlignmentSystem(SolenoidModule shortAlign, SolenoidModule longAlign, SolenoidModule staticAlign) {
        super(new Module[]{shortAlign, longAlign, staticAlign});
        this.shortAlign = shortAlign;
        this.longAlign = longAlign;
        this.staticAlign = staticAlign;
        // Long is on by default
        longAlign.set(LONG_ON_START);
    }

    /**
     * Collapses all pistons.
     */
    public void collapse() {
        shortAlign.set(false);
        longAlign.set(false);
        staticAlign.set(false);
    }

    /**
     * Hides long piston and extends short and static piston.
     */
    public void setShort() {
        shortAlign.set(true);
        longAlign.set(false);
        staticAlign.set(true);
    }

    /**
     * Hides short piston and extends long and static piston.
     */
    public void setLong() {
        shortAlign.set(false);
        longAlign.set(true);
        staticAlign.set(true);
    }
}
