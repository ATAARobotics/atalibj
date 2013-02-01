package edu.ATA.test;

import edu.ATA.main.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class SpeedControllersTest extends Robot {

    Joystick joystick = new Joystick(1);
    SpeedController s1 = new Talon(4, 1),
            s2 = new Talon(4, 2),
            s3 = new Victor(4, 3),
            s4 = new Victor(4, 4),
            s5 = new Victor(4, 5),
            s6 = new Victor(4, 6);

    public void teleopPeriodic() {
        double speed = joystick.getRawAxis(3);
        s1.set(speed);
        s2.set(speed);
        s3.set(speed);
        s4.set(speed);
        s5.set(speed);
        s6.set(speed);
    }
}
