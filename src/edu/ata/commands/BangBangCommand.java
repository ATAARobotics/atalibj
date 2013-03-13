package edu.ata.commands;

import edu.first.module.target.BangBangModule;
import edu.first.utils.preferences.DoublePreference;

/**
 * Command to set the setpoint of a bang bang module.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class BangBangCommand extends ThreadableCommand {

    private final BangBangModule bangBangModule;
    private DoublePreference setpointPreference;
    private final double setpoint;

    /**
     * Constructs the command with the band bang module and the desired
     * setpoint.
     *
     * @param bangBangModule bang bang to control
     * @param setpoint setpoint of bang bang
     * @param newThread if command should run in a new thread
     */
    public BangBangCommand(BangBangModule bangBangModule, double setpoint, boolean newThread) {
        super(newThread);
        this.bangBangModule = bangBangModule;
        this.setpoint = setpoint;
    }

    /**
     * Constructs the command with the band bang module and the desired
     * setpoint.
     *
     * @param bangBangModule bang bang to control
     * @param setpoint setpoint preference of bang bang
     * @param newThread if command should run in a new thread
     */
    public BangBangCommand(BangBangModule bangBangModule, DoublePreference setpoint, boolean newThread) {
        super(newThread);
        this.bangBangModule = bangBangModule;
        this.setpointPreference = setpoint;
        this.setpoint = 0;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if (setpointPreference != null) {
                    bangBangModule.setSetpoint(setpointPreference.get());
                } else {
                    bangBangModule.setSetpoint(setpoint);
                }
            }
        };
    }
}
