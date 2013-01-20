package edu.ATA.module.subsystem;

import edu.ATA.module.driving.RobotDriveModule;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.sensor.SolenoidModule;

/**
 * Drivetrain that has 2 gears. (super shifter) Uses 4 solenoids.
 *
 * @author joel
 */
public final class GearedDrivetrain extends Drivetrain {

    private final SolenoidModule left1, left2, right1, right2;
    private boolean geared = false;
    private boolean pressed = false;

    /**
     * Constructs the drivetrain using all of its modules. If you are not using
     * arcade drive, this defaults to tank drive. It's generally advisable to
     * only use the modules here, and not use them elsewhere, as references to
     * the same objects can cause conflicts.
     *
     * @param left1 left solenoid to set gear 1
     * @param left2 left solenoid to set gear 2
     * @param right1 right solenoid to set gear 1
     * @param right2 right solenoid to set gear 2
     * @param driveModule driving module
     * @param controller control to move robot with
     * @param arcadeDrive whether to use arcade or tank
     */
    public GearedDrivetrain(SolenoidModule left1, SolenoidModule left2,
            SolenoidModule right1, SolenoidModule right2, RobotDriveModule driveModule,
            XboxController controller, boolean arcadeDrive) {
        super(driveModule, controller, arcadeDrive);
        this.left1 = left1;
        this.left2 = left2;
        this.right1 = right1;
        this.right2 = right2;
    }

    /**
     * Enables all modules that are part of this class.
     *
     * @return whether all modules are enabled successfully
     */
    public boolean enable() {
        left1.enable();
        left2.enable();
        right1.enable();
        right2.enable();
        return super.enable() && isEnabled();
    }

    /**
     * Disables all modules that are a part of this class.
     *
     * @return whether all modules are disabled successfully
     */
    public boolean disable() {
        left1.disable();
        left2.disable();
        right1.disable();
        right2.disable();
        return super.disable() && !isEnabled();
    }

    /**
     * Returns whether all of the modules are enabled.
     *
     * @return if all modules are enabled
     */
    public boolean isEnabled() {
        return super.isEnabled() && left1.isEnabled() && left2.isEnabled()
                && right1.isEnabled() && right2.isEnabled();
    }

    /**
     * Drives the drivetrain. Uses {@link Drivetrain#drive()} as well as a
     * toggle on the right bumper to switch between gear 1 and 2.
     */
    public void drive() {
        super.drive();
        if (controller.getRightBumper() && !pressed) {
            pressed = true;
            geared = !geared;
        } else if (!controller.getRightBumper()) {
            pressed = false;
        }

        left1.set(geared);
        left2.set(!geared);
        right1.set(geared);
        right2.set(!geared);
    }
}
