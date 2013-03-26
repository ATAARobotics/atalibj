package edu.ata.commands;

import edu.ata.preferences.RPMPreference;
import edu.first.module.target.BangBangModule;
import edu.first.utils.preferences.DoublePreference;

public class SetRPMCommand extends ThreadableCommand {

    private final RPMPreference current;
    private final DoublePreference rpm;
    private final BangBangModule controller;

    public SetRPMCommand(RPMPreference current, DoublePreference rpm, BangBangModule controller, boolean newThread) {
        super(newThread);
        this.current = current;
        this.rpm = rpm;
        this.controller = controller;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                current.set(rpm.get());
                controller.setSetpoint(current.getRPM());
                controller.setDefaultSpeed(current.getDefaultSpeed());
            }
        };
    }
}