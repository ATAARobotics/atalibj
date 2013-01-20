package test;

/**
 *
 * @author joel
 */
public class GearedLoadingShooter extends GearedDrive {

    private boolean shooting;

    public String name() {
        return "Geared Loading Shooter";
    }

    public void robotInit() throws Error {
        super.robotInit();
        loadingShooter.enable();
    }

    public void teleopPeriodic() throws Error {
        super.teleopPeriodic();
        if (controller.getAButton() && !shooting) {
            shooting = true;
            robotDrive.stopMotors();
            loadingShooter.loadAndShoot(1, 1, 1, 3);
        } else if (!controller.getAButton()) {
            shooting = false;
        }
    }
}
