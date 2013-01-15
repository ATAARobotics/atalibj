package test;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 *
 * @author Team 4334
 */
public class RobotChooser extends SendableChooser {

    public RobotChooser() {
        addObject("BasicShooter", "BasicShooter");
        addObject("TrackedShooter", "TrackedShooter");
        addObject("MovingShooter", "MovingShooter");
        addObject("PneumaticShooter", "PneumaticShooter");
    }
}
