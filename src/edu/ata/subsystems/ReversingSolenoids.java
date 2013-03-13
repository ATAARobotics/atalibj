package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.subsystem.Subsystem;

/**
 * Subsystem to switch between in and out positions.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class ReversingSolenoids extends Subsystem {

    private final SolenoidModule in, out;
    private boolean position;

    /**
     * Constructs the system with the in and out solenoids to control the bar.
     *
     * @param in solenoid to bring bar in
     * @param out solenoid to bring bar out
     */
    public ReversingSolenoids(SolenoidModule in, SolenoidModule out) {
        super(new Module[]{in, out});
        this.in = in;
        this.out = out;
        switchPosition();
    }

    /**
     * Switches from in-to-out and vise verse.
     */
    public void switchPosition() {
        position = !position;
        in.set(position);
        out.set(!position);
    }
    
    /**
     * Sets the solenoids to an in position.
     */
    public void setIn() {
        position = true;
        in.set(true);
        out.set(false);
    }
    
    /**
     * Sets the solenoids to an out position.
     */
    public void setOut() {
        position = false;
        in.set(false);
        out.set(true);
    }

    /**
     * Returns whether the system is in the "in" position.
     *
     * @return if system is in
     */
    public boolean isIn() {
        return position;
    }
}