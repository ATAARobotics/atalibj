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

    private final SolenoidModule in, out;

    public AlignmentSystem(SolenoidModule in, SolenoidModule out) {
        super(new Module[]{in, out});
        this.in = in;
        this.out = out;
        collapse();
    }

    /**
     * Collapses all pistons.
     */
    public void collapse() {
        in.set(true);
        out.set(false);
    }
    
    public void extend() {
        in.set(false);
        out.set(true);
    }
}
