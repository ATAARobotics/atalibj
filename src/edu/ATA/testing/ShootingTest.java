package edu.ATA.testing;

import edu.ATA.main.Robot;
import edu.ATA.module.Modules;

/**
 *
 * @author Joel Gallant <joel.gallant236@gmail.com>
 */
public class ShootingTest extends Robot implements Modules {

    public void robotInit() {
        shooterSubsystem.enable();
    }

    public void teleopInit() {
        shooter.set(0);
    }

    public void teleopPeriodic() {
        shooterSubsystem.teleop();
    }
}
