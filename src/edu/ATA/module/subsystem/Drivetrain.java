package edu.ATA.module.subsystem;

import edu.ATA.module.Module;
import edu.ATA.module.driving.RobotDriveModule;
import edu.ATA.module.joystick.XboxController;

/**
 * Module to control a basic drivetrain. Uses a {@link RobotDriveModule} and
 * {@link XboxController} to move the robot.
 *
 * @author joel
 */
public class Drivetrain extends Subsystem implements Module.DisableableModule {

    protected final RobotDriveModule driveModule;
    protected final XboxController controller;
    private final boolean arcadeDrive;

    /**
     * Constructs the drivetrain using a {@link RobotDriveModule} and
     * {@link XboxController}. If you are not using arcade drive, this defaults
     * to tank drive. Requires them to be modules so that enabling and disabling
     * can be guaranteed. The module aspect of the given objects is handled by
     * the inner methods of this class. (enabling, disabling) It's generally
     * advisable to only use the modules here, and not use them elsewhere, as
     * references to the same objects can cause conflicts.
     *
     * @param driveModule driving module
     * @param controller control to move robot with
     * @param arcadeDrive whether to use arcade or tank
     */
    public Drivetrain(RobotDriveModule driveModule, XboxController controller, boolean arcadeDrive) {
        super(new Module[]{driveModule, controller});
        this.driveModule = driveModule;
        this.controller = controller;
        this.arcadeDrive = arcadeDrive;
    }

    /**
     * Drives the drivetrain using the settings.
     */
    public void drive() {
        if (arcadeDrive) {
            driveModule.arcadeDrive(convertSpeed(controller.getRightDistanceFromMiddle()), controller.getRightX(), false);
        } else {
            driveModule.tankDrive(convertSpeed(controller.getLeftDistanceFromMiddle()), convertSpeed(controller.getRightDistanceFromMiddle()), false);
        }
    }

    private double convertSpeed(double x) {
        return -4.43 * (((x * x * x * x) * (x - 1.3) * (x - 1.3)) / (x - 1.4));
    }
}
