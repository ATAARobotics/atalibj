package edu.ata.subsystems;

import edu.ata.modules.XboxController;
import edu.first.identifiers.Function;
import edu.first.module.Module;
import edu.first.module.subsystem.Subsystem;

public final class Driving extends Subsystem {

    private static final Function DRIVE_FUNCTION = new Function() {
        public double apply(double start) {
            return start != 0 ? (start * start * start + 0.12) : 0;
        }
    };
    private static final long delay = 10L;
    private static final double secondControllerTurnSpeed = 0.5;
    private final Drivetrain drivetrain;
    private final XboxController controller;
    private final XboxController secondController;

    public Driving(Drivetrain drivetrain, XboxController controller, XboxController secondController) {
        super(new Module[]{drivetrain, controller, secondController});
        this.drivetrain = drivetrain;
        this.controller = controller;
        this.secondController = secondController;
    }

    public void start() {
        startAtFixedRate(delay);
    }

    public void run() {
        if (!secondController.LeftJoystickButton()) {
            drivetrain.arcadeDrive(DRIVE_FUNCTION.apply(controller.LeftDistanceFromMiddle()),
                    controller.RightX());
            // Switch out for tank drive
            // drivetrain.tankDrive(DRIVE_FUNCTION.apply(controller.LeftDistanceFromMiddle()),
            //      DRIVE_FUNCTION.apply(controller.RightDistanceFromMiddle()));
        } else {
            drivetrain.arcadeDrive(0,
                    secondController.RightY() * secondControllerTurnSpeed);
        }
    }
}
