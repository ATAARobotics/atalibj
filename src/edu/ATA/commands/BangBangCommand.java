package edu.ATA.commands;

import edu.ATA.bindings.CommandBind;
import edu.ATA.command.Command;
import edu.ATA.module.target.BangBangModule;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class BangBangCommand implements Command, CommandBind {

    private final BangBangModule bangBangModule;
    private final double setpoint;

    public BangBangCommand(BangBangModule bangBangModule, double setpoint) {
        this.bangBangModule = bangBangModule;
        this.setpoint = setpoint;
    }

    public void run() {
        bangBangModule.setSetpoint(setpoint);
    }
}
