package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.driving.RobotDriveModule;
import edu.first.module.sensor.EncoderModule;
import edu.first.module.subsystem.Subsystem;

public final class MovementSystem extends Subsystem {

    private final RobotDriveModule drive;
    private final EncoderModule encoder;
    
    public MovementSystem(RobotDriveModule driveModule, EncoderModule encoder) {
        super(new Module[] {driveModule, encoder});
        this.drive = driveModule;
        this.encoder = encoder;
    }

    public void start() {
    }

    public void run() {
    }

    public void driveToDistance(double distance) {
        while(Math.abs(encoder.getDistance() - distance) > 10) {
            double p = 0.001 * (distance - encoder.getDistance());
            
            double speed = p;
            if(Math.abs(speed) > 0.4) {
                speed = speed > 0 ? 0.4 : -0.4;
            } else if(Math.abs(speed) < 0.2) {
                speed = speed > 0 ? 0.2 : -0.2;
            }
            drive.arcadeDrive(speed, 0);
        }
        drive.stopMotors();
    }
}