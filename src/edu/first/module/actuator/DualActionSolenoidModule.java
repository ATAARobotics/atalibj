package edu.first.module.actuator;

import edu.first.identifiers.ReturnableBoolean;
import edu.first.module.Module;
import edu.wpi.first.wpilibj.Solenoid;


/**
 * Subsystem to switch between in and out positions.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class DualActionSolenoidModule extends ForwardingDualActionSolenoid implements Module.DisableableModule {

    private boolean enabled;

    /**
     * Constructs the system with the in and out solenoids to control the bar.
     *
     * @param in solenoid to bring bar in
     * @param out solenoid to bring bar out
     */
    public DualActionSolenoidModule(Solenoid in, Solenoid out) {
        super(in, out);
    }

    public final boolean disable() {
        setIn();
        return !(enabled = false);
    }

    public final boolean enable() {
        return (enabled = true);
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public final void set(boolean position) {
        super.set(isEnabled() ? position : IN);
    }
}
class ForwardingDualActionSolenoid implements edu.first.module.actuator.Solenoid, ReturnableBoolean {

    public static boolean IN = false, OUT = true;
    private final Solenoid in, out;
    private boolean position;

    /**
     * Constructs the system with the in and out solenoids to control the bar.
     *
     * @param in solenoid to bring bar in
     * @param out solenoid to bring bar out
     */
    public ForwardingDualActionSolenoid(Solenoid in, Solenoid out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Switches from in-to-out and vise verse.
     */
    public final void switchPosition() {
        set(!position);
    }

    public void set(boolean position) {
        this.position = position;
        this.in.set(!position);
        this.out.set(position);
    }

    public final boolean get() {
        return position;
    }
    
    public final boolean isOut() {
        return get() == OUT;
    }

    /**
     * Sets the solenoids to an in position.
     */
    public final void setIn() {
        set(IN);
    }

    /**
     * Sets the solenoids to an out position.
     */
    public final void setOut() {
        set(OUT);
    }
}