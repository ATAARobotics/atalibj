package edu.ata.commands;

import edu.first.module.target.BangBangModule;
import edu.first.utils.preferences.DoublePreference;

/**
 *
 * @author Joel Gallant
 */
public class ChangeSetpointCommand extends ThreadableCommand {

    private final DoublePreference current;
    private final double change;
    private final BangBangModule controller;

    public ChangeSetpointCommand(DoublePreference current, double change, BangBangModule controller, boolean newThread) {
        super(newThread);
        this.current = current;
        this.change = change;
        this.controller = controller;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                double newSetpoint = current.get() + change;
                current.set(newSetpoint);
                controller.setSetpoint(newSetpoint);
            }
        };
    }
}
