package edu.ata.commands;

import edu.first.module.target.BangBangModule;

/**
 * Command to set the setpoint of a bang bang module.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class BangBangCommand extends ThreadableCommand {

    private final BangBangModule bangBangModule;
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

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                bangBangModule.setSetpoint(setpoint);
            }
        };
    }
}
