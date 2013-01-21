package edu.ATA.module.subsystem;

import edu.ATA.module.Module;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.sensor.SolenoidModule;

/**
 * Drivetrain that has 2 gears. (super shifter) Uses 4 solenoids.
 *
 * @author joel
 */
public final class GearedDrivetrain extends Subsystem {

    private final Drivetrain drivetrain;
    private final XboxController controller;
    private final SolenoidModule left1, left2, right1, right2;
    private boolean geared = false;
    private boolean pressed = false;

    /**
     * Constructs the drivetrain using all of its modules. If you are not using
     * arcade drive, this defaults to tank drive. Requires them to be modules so
     * that enabling and disabling can be guaranteed. The module aspect of the
     * given objects is handled by the inner methods of this class. (enabling,
     * disabling) It's generally advisable to only use the modules here, and not
     * use them elsewhere, as references to the same objects can cause
     * conflicts.
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
            SolenoidModule right1, SolenoidModule right2, Drivetrain drivetrain,
            XboxController controller) {
        super(new Module[]{left1, left2, right1, right2, drivetrain, controller});
        this.left1 = left1;
        this.left2 = left2;
        this.right1 = right1;
        this.right2 = right2;
        this.drivetrain = drivetrain;
        this.controller = controller;
    }

    /**
     * Drives the drivetrain. Uses {@link Drivetrain#drive()} as well as a
     * toggle on the right bumper to switch between gear 1 and 2.
     */
    public void drive() {
        drivetrain.drive();
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
