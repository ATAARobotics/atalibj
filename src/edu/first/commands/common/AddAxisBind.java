package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.module.joysticks.BindingJoystick;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class AddAxisBind implements Command {

    private final BindingJoystick joystick;
    private final BindingJoystick.AxisBind bind;

    public AddAxisBind(BindingJoystick joystick, BindingJoystick.AxisBind bind) {
        this.joystick = joystick;
        this.bind = bind;
    }

    public void run() {
        joystick.addAxisBind(bind);
    }
}
