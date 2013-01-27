package edu.ATA.commands;

import edu.ATA.bindings.CommandBind;
import edu.ATA.command.Command;
import edu.ATA.module.joystick.BindableJoystick;
import edu.ATA.module.speedcontroller.SpeedControllerModule;

/**
 * Command to set the speed of a speed controller. Should be run in loops (ex.
 * {@link BindableJoystick#bindWhilePressed(int, edu.ATA.command.Command)}).
 *
 * @author Joel Gallant <joel.gallant236@gmail.com>
 */
public class SpeedControllerCommand implements CommandBind {

    private final SpeedControllerModule speedControllerModule;
    private final double speed;

    /**
     * Constructs the command using the speed controller and its desired speed.
     * The speed controller module need to be enabled for the command to work.
     *
     * @param speedControllerModule speed controller to change speed of
     * @param speed speed to set module to
     */
    public SpeedControllerCommand(SpeedControllerModule speedControllerModule, double speed) {
        this.speedControllerModule = speedControllerModule;
        this.speed = speed;
    }

    public void run() {
        speedControllerModule.set(speed);
    }
}
