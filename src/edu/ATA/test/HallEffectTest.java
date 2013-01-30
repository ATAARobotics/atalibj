package edu.ATA.test;

import edu.ATA.main.Robot;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class HallEffectTest extends Robot {

    private final DigitalInput hallEffect = new DigitalInput(1);
    private final Counter counter = new Counter(hallEffect);

    public void robotInit() {
        counter.start();
    }

    public void teleopPeriodic() {
        SmartDashboard.putBoolean("HallEffect", hallEffect.get());
        SmartDashboard.putNumber("HallEffectCount", counter.get());
    }
}
