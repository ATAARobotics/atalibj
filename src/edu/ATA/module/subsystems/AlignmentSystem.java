package edu.ATA.module.subsystems;

import edu.ATA.module.Module;
import edu.ATA.module.actuator.SolenoidModule;
import edu.ATA.module.subsystem.Subsystem;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class AlignmentSystem extends Subsystem {

    private final static boolean LONG_ON_START = true;
    private final SolenoidModule shortAlign, longAlign, staticAlign;

    public AlignmentSystem(SolenoidModule shortAlign, SolenoidModule longAlign, SolenoidModule staticAlign) {
        super(new Module[]{shortAlign, longAlign, staticAlign});
        this.shortAlign = shortAlign;
        this.longAlign = longAlign;
        this.staticAlign = staticAlign;
        // Long is on by default
        longAlign.set(LONG_ON_START);
    }

    public void collapse() {
        shortAlign.set(false);
        longAlign.set(false);
        staticAlign.set(false);
    }

    public void setShort() {
        shortAlign.set(true);
        longAlign.set(false);
        staticAlign.set(true);
    }

    public void setLong() {
        shortAlign.set(false);
        longAlign.set(true);
        staticAlign.set(true);
    }
}
