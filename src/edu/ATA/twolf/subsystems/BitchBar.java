package edu.ATA.twolf.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.subsystem.Subsystem;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class BitchBar extends Subsystem {
    
    private final SolenoidModule in, out;
    private boolean position;
    
    public BitchBar(SolenoidModule in, SolenoidModule out) {
        super(new Module[]{in, out});
        this.in = in;
        this.out = out;
        switchPosition();
    }
    
    public void switchPosition() {
        in.set(position);
        out.set(!position);
        position = !position;
    }
}
