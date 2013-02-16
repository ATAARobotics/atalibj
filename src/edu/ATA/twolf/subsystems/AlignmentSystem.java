package edu.ATA.twolf.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.subsystem.Subsystem;

/**
 * The subsystem class for the alignment system.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class AlignmentSystem extends Subsystem {

    private final SolenoidModule shortAlignOut, shortAlignIn, longAlignOut, longAlignIn, staticAlignOut, staticAlignIn;

    public AlignmentSystem(SolenoidModule shortAlignOut, SolenoidModule shortAlignIn,
            SolenoidModule longAlignOut, SolenoidModule longAlignIn, SolenoidModule staticAlignOut, 
            SolenoidModule staticAlignIn) {
        super(new Module[]{shortAlignOut, shortAlignIn, longAlignOut, longAlignIn, staticAlignOut, staticAlignIn});
        this.shortAlignOut = shortAlignOut;
        this.shortAlignIn = shortAlignIn;
        this.longAlignOut = longAlignOut;
        this.longAlignIn = longAlignIn;
        this.staticAlignOut = staticAlignOut;
        this.staticAlignIn = staticAlignIn;
        collapse();
    }

    /**
     * Collapses all pistons.
     */
    public void collapse() {
        shortAlignIn.set(true);
        shortAlignOut.set(false);
        longAlignIn.set(true);
        longAlignOut.set(false);
        staticAlignIn.set(true);
        staticAlignOut.set(false);
    }

    /**
     * Hides long piston and extends short and static piston.
     */
    public void setShort() {
        shortAlignIn.set(false);
        shortAlignOut.set(true);
        longAlignIn.set(true);
        longAlignOut.set(false);
        staticAlignIn.set(false);
        staticAlignOut.set(true);
    }

    /**
     * Hides short piston and extends long and static piston.
     */
    public void setLong() {
        shortAlignIn.set(true);
        shortAlignOut.set(false);
        longAlignIn.set(false);
        longAlignOut.set(true);
        staticAlignIn.set(false);
        staticAlignOut.set(true);
    }
}
