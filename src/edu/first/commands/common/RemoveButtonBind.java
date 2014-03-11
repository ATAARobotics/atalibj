package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.module.joysticks.BindingJoystick;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class RemoveButtonBind implements Command {

    private final BindingJoystick joystick;
    private final BindingJoystick.ButtonBind bind;

    public RemoveButtonBind(BindingJoystick joystick, BindingJoystick.ButtonBind bind) {
        this.joystick = joystick;
        this.bind = bind;
    }

    public void run() {
        joystick.addButtonBind(bind);
    }
}
