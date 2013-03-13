package edu.ata.commands;

import edu.ata.subsystems.Shooter;
import edu.first.module.joystick.BindableJoystick;
import edu.first.module.sensor.Potentiometer;
import edu.first.utils.Logger;
import edu.first.utils.preferences.DoublePreference;

/**
 * Command to rebind a button that aligns the shooter to a position. Removes all
 * binds on the button every time it is rebinded.
 *
 * @author Joel Gallant
 */
public final class RebindShooterPosition extends ThreadableCommand {

    private final String buttonName;
    private final Shooter shooter;
    private final DoublePreference preference;
    private final BindableJoystick controller;
    private final Potentiometer shooterPosition;
    private final int port;

    /**
     * Constructs the command using the necessary modules.
     *
     * @param buttonName name to give the user
     * @param shooter shooter to move
     * @param preference preference that holds the position value
     * @param controller joystick being binded
     * @param shooterPosition sensor to determine the position
     * @param port button port on the joystick
     * @param newThread if command should run in a new thread
     */
    public RebindShooterPosition(String buttonName, Shooter shooter,
            DoublePreference preference, BindableJoystick controller,
            Potentiometer shooterPosition, int port, boolean newThread) {
        super(newThread);
        this.buttonName = buttonName;
        this.shooter = shooter;
        this.preference = preference;
        this.controller = controller;
        this.shooterPosition = shooterPosition;
        this.port = port;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                double position = shooterPosition.getPosition();
                preference.set(position);
                controller.removeButtonBinds(port);
                controller.bindWhenPressed(port,
                        new ShooterAlignCommand(shooter, position, true));
                Logger.log(Logger.Urgency.USERMESSAGE, buttonName
                        + " set to " + (Math.floor(position * 100 + .5) / 100));
            }
        };
    }
}
