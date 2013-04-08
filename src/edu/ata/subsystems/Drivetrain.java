package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.driving.RobotDriveModule;
import edu.first.module.subsystem.Subsystem;

public final class Drivetrain extends Subsystem {

    private static final long delay = 15L;
    private final RobotDriveModule drive;
    private double forwards, turn;

    public Drivetrain(RobotDriveModule drive) {
        super(new Module[]{drive});
        this.drive = drive;
    }

    public void start() {
        startAtFixedRate(delay);
    }

    public void run() {
        drive.arcadeDrive(forwards, turn);
    }

    public void arcadeDrive(double forwards, double turn) {
        this.forwards = forwards;
        this.turn = turn;
    }
}