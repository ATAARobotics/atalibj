package edu.ATA.commands;

import edu.ATA.bindings.CommandBind;
import edu.ATA.command.Command;
import edu.ATA.module.subsystems.Shooter;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class ShootCommand implements Command, CommandBind {

    private final Shooter shooter;

    public ShootCommand(Shooter shooter) {
        this.shooter = shooter;
    }

    public void run() {
        shooter.shoot();
    }
}
