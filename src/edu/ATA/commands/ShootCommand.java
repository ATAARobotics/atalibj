package edu.ATA.commands;

import edu.ATA.bindings.CommandBind;
import edu.ATA.command.Command;
import edu.ATA.module.subsystems.Shooter;

/**
 * The command class for the shooter shooting.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class ShootCommand implements Command, CommandBind {

    private final Shooter shooter;

    /**
     * Shoots the shooter.
     *
     * @param shooter the shooter object used
     */
    public ShootCommand(Shooter shooter) {
        this.shooter = shooter;
    }

    public void run() {
        shooter.shoot();
    }
}
