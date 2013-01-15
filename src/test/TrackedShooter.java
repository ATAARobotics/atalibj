package test;

import edu.ATA.module.sensor.EncoderModule;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Team 4334
 */
public class TrackedShooter extends BasicShooter {

    protected EncoderModule encoder = new EncoderModule(new Encoder(1, 2));

    public String name() {
        return "Tracked Shooter";
    }

    public void robotInit() throws Error {
        super.robotInit();
        encoder.enable();
    }

    public void teleopPeriodic() throws Error {
        super.teleopPeriodic();
        SmartDashboard.putNumber("Encoder", encoder.getRate());
    }
}
