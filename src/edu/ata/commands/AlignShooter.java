package edu.ata.commands;

import edu.ata.subsystems.Shooter;
import edu.first.utils.preferences.DoublePreference;

/**
 * Command to align the shooter.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class AlignShooter extends ThreadableCommand {

    private final Shooter shooter;
    private final double setpoint;
    private final DoublePreference setpointPreference;

    /**
     * Constructs the command with the shooter and the position.
     *
     * @param shooter shooter to align
     * @param setpoint value to align to
     * @param newThread if command should run in a new thread
     */
    public AlignShooter(Shooter shooter, double setpoint, boolean newThread) {
        super(newThread);
        this.shooter = shooter;
        this.setpoint = setpoint;
        this.setpointPreference = null;
    }

    /**
     *
     * @param shooter
     * @param setpointPreference
     * @param newThread
     */
    public AlignShooter(Shooter shooter, DoublePreference setpointPreference, boolean newThread) {
        super(newThread);
        this.shooter = shooter;
        this.setpoint = 0;
        this.setpointPreference = setpointPreference;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if (setpointPreference != null) {
                    shooter.alignTo(setpointPreference.get());
                } else {
                    shooter.alignTo(setpoint);
                }
            }
        };
    }
}
