package edu.ATA.commands;

import edu.first.module.target.BangBangModule;

/**
 * This is the command class for the {@link BangBangModule} to set the setpoint
 *
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class BangBangCommand extends ThreadableCommand {

    private final BangBangModule bangBangModule;
    private final double setpoint;

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
