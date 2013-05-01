package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.DualActionSolenoidModule;
import edu.first.module.sensor.PotentiometerModule;
import edu.first.module.subsystem.Subsystem;
import edu.first.module.target.BangBangModule;
import edu.first.utils.Logger;
import edu.wpi.first.wpilibj.Timer;

public final class Loader extends Subsystem {

    private static final boolean coastShots = true;
    private final DualActionSolenoidModule solenoid;
    private final BangBangModule shooterWheel;
    private final PotentiometerModule potentiometer;

    public Loader(DualActionSolenoidModule solenoid, BangBangModule shooterWheel,
            PotentiometerModule potentiometer) {
        super(new Module[]{solenoid, shooterWheel, potentiometer});
        this.solenoid = solenoid;
        this.shooterWheel = shooterWheel;
        this.potentiometer = potentiometer;
    }

    public void start() {
    }

    public void run() {
    }

    public void fire() {
        if (!solenoid.get()) {
            solenoid.setOut();
            Timer.delay(0.5);
        }
        if (coastShots) {
            shooterWheel.setCoast(true);
        }

        Logger.log(Logger.Urgency.USERMESSAGE, "Shooting");
        Logger.log(Logger.Urgency.LOG, "Pot Before Shot - " + potentiometer.getPosition());
        Logger.log(Logger.Urgency.LOG, "Speed Before Shot - " + shooterWheel.getInput());

        solenoid.setIn();
        Timer.delay(0.5);
        if (coastShots) {
            shooterWheel.setCoast(false);
        }
        solenoid.setOut();

        Logger.log(Logger.Urgency.LOG, "Pot After Shot - " + potentiometer.getPosition());
    }

    public void setIn() {
        solenoid.setIn();
    }

    public void setOut() {
        solenoid.setOut();
    }

    public boolean isOut() {
        return solenoid.isOut();
    }
}