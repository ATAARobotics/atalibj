package edu.ATA.module.joystick;

import edu.ATA.bindings.Bindable;
import edu.ATA.module.Module;

/**
 *
 * @author Joel Gallant
 */
public class BindableJoystick extends Bindable implements Joystick, Module.DisableableModule {

    private final JoystickModule joystick;

    public BindableJoystick(edu.wpi.first.wpilibj.Joystick joystick) {
        this.joystick = new JoystickModule(joystick);
    }

    public final boolean disable() {
        return joystick.disable();
    }

    public final boolean enable() {
        return joystick.enable();
    }

    public final boolean isEnabled() {
        return joystick.isEnabled();
    }

    public double getAxis(int port) {
        return joystick.getAxis(port);
    }

    public boolean getButton(int button) {
        return joystick.getButton(button);
    }

    public final boolean getPressed(int port) {
        return getButton(port);
    }

    public final double getAxisValue(int port) {
        return getAxis(port);
    }
}
