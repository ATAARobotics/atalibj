package edu.ATA.autonomous;

import edu.ATA.commands.MoveToSetpoint;
import edu.ATA.twolf.subsystems.AlignmentSystem;
import edu.ATA.twolf.subsystems.ShiftingDrivetrain;
import edu.ATA.twolf.subsystems.Shooter;
import edu.first.module.sensor.EncoderModule;
import edu.first.module.sensor.GyroModule;
import edu.first.module.target.BangBangModule;
import edu.first.module.target.PIDModule;
import edu.first.utils.DriverstationInfo;
import edu.first.utils.Logger;
import edu.gordian.Gordian;
import edu.gordian.Variable;
import edu.gordian.method.BooleanReturningMethod;
import edu.gordian.method.NumberReturningMethod;
import edu.gordian.method.RunningMethod;
import edu.gordian.variable.NumberInterface;
import edu.wpi.first.wpilibj.Timer;
import java.io.IOException;
import javax.microedition.io.Connector;

/**
 * Static class meant to keep Gordian in a state where it can run the current
 * script. To make sure the script uses all of the methods given, the
 * {@link Gordian#ensureInit()} method makes sure that all storage of methods
 * and variables are stored. {@code ensureInit()} is called every time
 * {@link Gordian#run(java.lang.String)} is called, so you usually don't have to
 * worry about it.
 *
 * <p> In almost every case, you should run gordian script using
 * {@link Gordian#run(java.lang.String)}. Running
 * {@link Script#run(java.lang.String)} will not automatically include all
 * methods that you have made, and will most likely not be useful for any
 * practical applications other than basic logic and delays.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class GordianAuto {

    private static boolean init = false;
    private static Gordian gordian;
    private static ShiftingDrivetrain drivetrain;
    private static Shooter shooter;
    private static BangBangModule bangBangModule;
    private static AlignmentSystem alignmentSystem;
    private static PIDModule drivetrainPID;
    private static EncoderModule encoder;
    private static GyroModule gyro;

    private GordianAuto() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Makes sure that everything is ready to be run in Gordian. This includes
     * methods, variables, etc. You can generally accept that everything is
     * ready to be run after running this method.
     *
     * @param drivetrain the drivetrain you are using
     * @param shooter the shooter you are using
     * @param bangBangModule the bang-bang module that you are using
     * @param alignmentSystem that alignment system that your are using
     */
    public static void ensureInit(ShiftingDrivetrain drivetrain, Shooter shooter,
            BangBangModule bangBangModule, AlignmentSystem alignmentSystem, PIDModule drivetrainPID,
            EncoderModule encoder, GyroModule gyro) {
        if (!init) {
            GordianAuto.drivetrain = drivetrain;
            GordianAuto.shooter = shooter;
            GordianAuto.bangBangModule = bangBangModule;
            GordianAuto.alignmentSystem = alignmentSystem;
            GordianAuto.drivetrainPID = drivetrainPID;
            GordianAuto.encoder = encoder;
            GordianAuto.gyro = gyro;
            init = true;
        }
    }

    /**
     * Accesses the file at {@code "file:///"+fileName}, and uses the text found
     * there as the script.
     *
     * @param fileName name of the file to retrieve text from
     * @throws IOException thrown when accessing file fails
     */
    public static void run(String fileName) throws IOException {
        String script = Logger.getTextFromFile(Connector.openDataInputStream("file:///" + fileName));
        gordian = new Gordian(script);
        init();
        gordian.run();
    }

    private static void init() {
        // Insert all methods, variables, returning methods and initialization code here.
        gordian.addMethod(new RunningMethod("print") {
            public void run(Variable[] args) {
                System.out.println(args[0].getValue());
            }
        });
        gordian.addMethod(new RunningMethod("wait") {
            public void run(Variable[] args) {
                Timer.delay(((NumberInterface)args[0]).doubleValue());
            }
        });
        gordian.addMethod(new RunningMethod("log") {
            public void run(Variable[] args) {
                Logger.log(Logger.Urgency.USERMESSAGE, args[0].getValue().toString());
            }
        });
        gordian.addMethod(new BooleanReturningMethod("isEnabled") {
            public boolean getBoolean() {
                return DriverstationInfo.isEnabled();
            }
        });
        gordian.addMethod(new RunningMethod("arcade") {
            public void run(Variable[] args) {
                drivetrain.arcadeDrive(((NumberInterface) args[0]).doubleValue(), ((NumberInterface) args[1]).doubleValue());
            }
        });
        gordian.addMethod(new RunningMethod("tank") {
            public void run(Variable[] args) {
                drivetrain.tankDrive(((NumberInterface) args[0]).doubleValue(), ((NumberInterface) args[1]).doubleValue());
            }
        });
        gordian.addMethod(new RunningMethod("stop") {
            public void run(Variable[] args) {
                drivetrain.stop();
            }
        });
        gordian.addMethod(new RunningMethod("shiftGear") {
            public void run(Variable[] args) {
                drivetrain.shiftGears();
            }
        });
        gordian.addMethod(new RunningMethod("driveToSetpoint") {
            public void run(Variable[] args) {
                encoder.reset();
                double setpoint = ((NumberInterface) args[0]).doubleValue();
                Logger.log(Logger.Urgency.USERMESSAGE, "Driving to " + setpoint);
                new MoveToSetpoint(drivetrainPID, setpoint).run();
            }
        });
        gordian.addMethod(new RunningMethod("gyroTurn") {
            public void run(Variable[] args) {
                gyro.reset();
                double setpoint = ((NumberInterface) args[0]).doubleValue();
                double lspeed = ((NumberInterface) args[1]).doubleValue();
                double rspeed = ((NumberInterface) args[2]).doubleValue();
                Logger.log(Logger.Urgency.USERMESSAGE, "Turning to " + setpoint);
                String mode = DriverstationInfo.getGamePeriod();
                while (Math.abs(gyro.getAngle()) < Math.abs(setpoint) && mode.equals(DriverstationInfo.getGamePeriod())) {
                    drivetrain.tankDrive(lspeed, rspeed);
                    Timer.delay(0.02);
                }
                drivetrain.stop();
            }
        });
        gordian.addMethod(new RunningMethod("shoot") {
            public void run(Variable[] args) {
                shooter.shoot();
            }
        });
        gordian.addMethod(new RunningMethod("alignShooter") {
            public void run(Variable[] args) {
                shooter.alignTo(((NumberInterface) args[0]).doubleValue());
            }
        });
        gordian.addMethod(new RunningMethod("setShooterSpeed") {
            public void run(Variable[] args) {
                double speed = ((NumberInterface) args[0]).doubleValue();
                Logger.log(Logger.Urgency.USERMESSAGE, "Setting shooter to " + speed);
                bangBangModule.setSetpoint(speed);
            }
        });
        gordian.addMethod(new RunningMethod("stopShooter") {
            public void run(Variable[] args) {
                bangBangModule.setSetpoint(0);
            }
        });
        gordian.addMethod(new BooleanReturningMethod("isPastSetpoint") {
            public boolean getBoolean() {
                return bangBangModule.pastSetpoint();
            }
        });
        gordian.addMethod(new NumberReturningMethod("gyro") {
            public double getDouble() {
                return gyro.getAngle();
            }
        });
        gordian.addMethod(new RunningMethod("resetAngle") {
            public void run(Variable[] args) {
                gyro.reset();
            }
        });
        gordian.addMethod(new NumberReturningMethod("encoderDistance") {
            public double getDouble() {
                return encoder.getDistance();
            }
        });
        gordian.addMethod(new RunningMethod("resetDistance") {
            public void run(Variable[] args) {
                encoder.reset();
            }
        });
        gordian.addMethod(new RunningMethod("collapseAlignment") {
            public void run(Variable[] args) {
                alignmentSystem.collapse();
            }
        });
        gordian.addMethod(new RunningMethod("setLongAlignment") {
            public void run(Variable[] args) {
                alignmentSystem.setLong();
            }
        });
        gordian.addMethod(new RunningMethod("setShortAlignment") {
            public void run(Variable[] args) {
                alignmentSystem.setShort();
            }
        });
    }
}
