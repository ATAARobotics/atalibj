package edu.ata.commands;

import edu.ata.preferences.RPMPreference;
import edu.first.module.target.BangBangModule;

/**
 *
 * @author Joel Gallant
 */
public class ChangeRPMCommand extends ThreadableCommand {

    private final RPMPreference current;
    private final double change;
    private final BangBangModule controller;

    /**
     *
     * @param current
     * @param change
     * @param controller
     * @param newThread
     */
    public ChangeRPMCommand(RPMPreference current, double change, BangBangModule controller, boolean newThread) {
        super(newThread);
        this.current = current;
        this.change = change;
        this.controller = controller;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                double newSetpoint = current.getRPM() + change;
                current.set(newSetpoint);
                controller.setSetpoint(current.getRPM());
                controller.setDefaultSpeed(current.getDefaultSpeed());
            }
        };
    }
}
