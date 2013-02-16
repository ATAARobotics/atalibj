package edu.first.commands;

import edu.first.command.Command;
import edu.first.module.joystick.BindableJoystick;
import edu.first.module.speedcontroller.SpeedControllerModule;

/**
 * Command to set the speed of a speed controller. Should be run in loops (ex.
 * {@link BindableJoystick#bindWhilePressed(int, edu.ATA.command.Command)}).
 *
 * @author Joel Gallant
 */
public final class SpeedControllerCommand implements Command {

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

    /**
     * Sets the speed of the speed controller to the speed given in the
     * constructor.
     */
    public void run() {
        speedControllerModule.set(speed);
    }
}
