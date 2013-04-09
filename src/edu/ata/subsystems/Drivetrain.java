package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.driving.RobotDriveModule;
import edu.first.module.subsystem.Subsystem;

public final class Drivetrain extends Subsystem {

    private static final long delay = 15L;
    private final RobotDriveModule drive;
    private boolean arcade;
    private double left, right;
    private double forwards, turn;

    public Drivetrain(RobotDriveModule drive) {
        super(new Module[]{drive});
        this.drive = drive;
    }

    public void start() {
        startAtFixedRate(delay);
    }

    public void run() {
        synchronized (this) {
            if (arcade) {
                drive.arcadeDrive(forwards, turn);
            } else {
                drive.tankDrive(left, right);
            }
        }
    }

    public void arcadeDrive(double forwards, double turn) {
        synchronized (this) {
            this.arcade = true;
            this.forwards = forwards;
            this.turn = turn;
        }
    }

    public void tankDrive(double left, double right) {
        synchronized (this) {
            this.arcade = false;
            this.left = left;
            this.right = right;
        }
    }
}