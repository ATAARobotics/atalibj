package edu.ATA.module.subsystem;

import edu.ATA.command.Command;
import edu.ATA.command.Commands;
import edu.ATA.module.Module;
import edu.ATA.module.sensor.EncoderModule;
import edu.ATA.module.sensor.SolenoidModule;
import edu.ATA.module.speedcontroller.SpeedControllerModule;
import edu.wpi.first.wpilibj.Timer;

/**
 * Shooter subsystem that is able to be loaded using pneumatics. Uses a two
 * sided solenoid to "load" and "reload" the shooter. Loading is when the
 * pneumatic pushes the disc in the shooter, and reloading is when it retracts
 * and lets another disc in.
 *
 * @author joel
 */
public class LoadingShooter extends Subsystem {

    private final Shooter shooter;
    private final SolenoidModule load, reload;

    /**
     * Constructs the subsystem using its two solenoids, an encoder for speed
     * and a motor to shoot. Requires them to be modules so that enabling and
     * disabling can be guaranteed. The module aspect of the given objects is
     * handled by the inner methods of this class. (enabling, disabling) It's
     * generally advisable to only use the modules here, and not use them
     * elsewhere, as references to the same objects can cause conflicts.
     *
     * <p> <b> Please make sure the encoder given uses
     * {@link EncoderModule#RATE} in its constructor.</b>
     *
     * @param load the solenoid that loads the disc into the shooter
     * @param reload the solenoid that retracts the pneumatic to let a new disc
     * in
     * @param encoder encoder to measure speed
     * @param motor motor to control
     */
    public LoadingShooter(SolenoidModule load, SolenoidModule reload, Shooter shooter) {
        super(new Module[]{load, reload, shooter});
        this.shooter = shooter;
        this.load = load;
        this.reload = reload;
    }

    /**
     * Reloads the shooter, pushes the disc out and reloads again.
     *
     * @param loadTime time to wait for disc to load
     * @param waitTime time to wait for disc to be pushed
     */
    public void load(double loadTime, double waitTime) {
        synchronized (this) {
            Commands.runInNewThread(new Load(loadTime, waitTime));
        }
    }

    /**
     * Reloads the shooter, pushes the disc out, reloads, turns on the motor to
     * the setpoint using bang-bang, shooters, stops the motor.
     *
     * @param loadTime time to wait for disc to load
     * @param waitTime time to wait for disc to be pushed
     * @param speed speed to shoot at
     * @param shootTime time to shoot for
     */
    public void loadAndShoot(double loadTime, double waitTime, double speed, double shootTime) {
        synchronized (this) {
            Commands.runInNewThread(new LoadAndShoot(new Load(loadTime, waitTime), speed, shootTime));
        }
    }

    private class Load implements Command {

        private final double loadTime, waitTime;

        public Load(double loadTime, double waitTime) {
            this.loadTime = loadTime;
            this.waitTime = waitTime;
        }

        public void runCommand() {
            // Let disc in
            reload.set(true);
            load.set(false);
            Timer.delay(loadTime);
            // Push disc out
            reload.set(false);
            load.set(true);
            Timer.delay(waitTime);
            // Let new disc in
            load.set(false);
            reload.set(true);
        }
    }

    private class LoadAndShoot implements Command {

        private final Load load;
        private final double speed, shootTime;

        public LoadAndShoot(Load load, double speed, double shootTime) {
            this.load = load;
            this.speed = speed;
            this.shootTime = shootTime;
        }

        public void runCommand() {
            load.runCommand();
            shooter.setSetpoint(speed);
            Timer.delay(shootTime);
            shooter.setSetpoint(0);
        }
    }
}
