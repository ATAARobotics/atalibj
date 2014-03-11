package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.module.joysticks.BindingJoystick;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class RemoveAxisBind implements Command {

    private final BindingJoystick joystick;
    private final BindingJoystick.AxisBind bind;

    public RemoveAxisBind(BindingJoystick joystick, BindingJoystick.AxisBind bind) {
        this.joystick = joystick;
        this.bind = bind;
    }

    public void run() {
        joystick.removeBind(bind);
    }
}
