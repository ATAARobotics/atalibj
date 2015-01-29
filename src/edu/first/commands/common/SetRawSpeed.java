package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.identifiers.Input;
import edu.first.identifiers.StaticInput;
import edu.first.module.actuators.SpeedController;

/**
 * Sets the raw speed of a speed controller.
 *
 * @see SpeedController#setRawSpeed(int)
 * @since June 13 13
 * @author Joel Gallant
 */
public final class SetRawSpeed implements Command {

    private final SpeedController speedController;
    private final Input input;

    /**
     * Constructs the command using the speed controller and input.
     *
     * @param speedController speed controller that will be set
     * @param input speed to set to
     */
    public SetRawSpeed(SpeedController speedController, Input input) {
        this.speedController = speedController;
        this.input = input;
    }

    /**
     * Constructs the command using the speed controller and input.
     *
     * @param speedController speed controller that will be set
     * @param input speed to set to
     */
    public SetRawSpeed(SpeedController speedController, double input) {
        this(speedController, new StaticInput(input));
    }

    /**
     * Sets the raw speed of the speed controller.
     *
     * @see SpeedController#setRawSpeed(int)
     */
    @Override
    public void run() {
        speedController.set(input.get());
    }
}
