package edu.ATA.autonomous;

import ATA.gordian.Data;
import ATA.gordian.Script;
import ATA.gordian.data.BooleanData;
import ATA.gordian.data.DoubleData;
import ATA.gordian.data.NumberData;
import ATA.gordian.data.ReturningMethod;
import ATA.gordian.instructions.MethodBody;
import ATA.gordian.storage.Methods;
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
public final class Gordian {

    private static boolean init = false;

    private Gordian() throws IllegalAccessException {
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
            init(drivetrain, shooter, bangBangModule, alignmentSystem, drivetrainPID, encoder, gyro);
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
        Script.run(script);
    }

    private static void init(final ShiftingDrivetrain drivetrain, final Shooter shooter,
            final BangBangModule bangBangModule, final AlignmentSystem alignmentSystem,
            final PIDModule drivetrainPID, final EncoderModule encoder, final GyroModule gyro) {
        // Insert all methods, variables, returning methods and initialization code here.
        Methods.METHODS_STORAGE.addMethod("log", new MethodBody() {
            public void run(Data[] args) {
                Logger.log(Logger.Urgency.USERMESSAGE, args[0].getValue().toString());
            }
        });
        Data.RETURNING_METHODS.addMethod("isEnabled", new ReturningMethod() {
            public Data getValue(Data[] args) {
                return new BooleanData(String.valueOf(DriverstationInfo.isEnabled()));
            }
        });
        Methods.METHODS_STORAGE.addMethod("arcade", new MethodBody() {
            public void run(Data[] args) {
                drivetrain.arcadeDrive(((NumberData) args[0]).doubleValue(), ((NumberData) args[1]).doubleValue());
            }
        });
        Methods.METHODS_STORAGE.addMethod("tank", new MethodBody() {
            public void run(Data[] args) {
                drivetrain.tankDrive(((NumberData) args[0]).doubleValue(), ((NumberData) args[1]).doubleValue());
            }
        });
        Methods.METHODS_STORAGE.addMethod("stop", new MethodBody() {
            public void run(Data[] args) {
                drivetrain.stop();
            }
        });
        Methods.METHODS_STORAGE.addMethod("shiftGear", new MethodBody() {
            public void run(Data[] args) {
                drivetrain.shiftGears();
            }
        });
        Methods.METHODS_STORAGE.addMethod("driveToSetpoint", new MethodBody() {
            public void run(Data[] args) {
                encoder.reset();
                double setpoint = ((NumberData) args[0]).doubleValue();
                Logger.log(Logger.Urgency.USERMESSAGE, "Driving to " + setpoint);
                new MoveToSetpoint(drivetrainPID, setpoint).run();
            }
        });
        Methods.METHODS_STORAGE.addMethod("gyroTurn", new MethodBody() {
            public void run(Data[] args) {
                gyro.reset();
                double setpoint = ((NumberData) args[0]).doubleValue();
                double lspeed = ((NumberData) args[1]).doubleValue();
                double rspeed = ((NumberData) args[2]).doubleValue();
                Logger.log(Logger.Urgency.USERMESSAGE, "Turning to " + setpoint);
                String mode = DriverstationInfo.getGamePeriod();
                while (Math.abs(gyro.getAngle()) < Math.abs(setpoint) && mode.equals(DriverstationInfo.getGamePeriod())) {
                    drivetrain.tankDrive(lspeed, rspeed);
                    Timer.delay(0.02);
                }
                drivetrain.stop();
                
            }
        });
        Methods.METHODS_STORAGE.addMethod("shoot", new MethodBody() {
            public void run(Data[] args) {
                shooter.shoot();
            }
        });
        Methods.METHODS_STORAGE.addMethod("alignShooter", new MethodBody() {
            public void run(Data[] args) {
                shooter.alignTo(((NumberData) args[0]).doubleValue());
            }
        });
        Methods.METHODS_STORAGE.addMethod("setShooterSpeed", new MethodBody() {
            public void run(Data[] args) {
                double speed = ((NumberData) args[0]).doubleValue();
                Logger.log(Logger.Urgency.USERMESSAGE, "Setting shooter to " + speed);
                bangBangModule.setSetpoint(speed);
            }
        });
        Methods.METHODS_STORAGE.addMethod("stopShooter", new MethodBody() {
            public void run(Data[] args) {
                bangBangModule.setSetpoint(0);
            }
        });
        Data.RETURNING_METHODS.addMethod("isPastSetpoint", new ReturningMethod() {
            public Data getValue(Data[] args) {
                return new BooleanData(String.valueOf(bangBangModule.pastSetpoint()));
            }
        });
        Data.RETURNING_METHODS.addMethod("gyro", new ReturningMethod() {
            public Data getValue(Data[] args) {
                return Data.get(String.valueOf(gyro.getAngle()));
            }
        });
        Methods.METHODS_STORAGE.addMethod("resetAngle", new MethodBody() {
            public void run(Data[] args) {
                gyro.reset();
            }
        });
        Data.RETURNING_METHODS.addMethod("encoderDistance", new ReturningMethod() {
            public Data getValue(Data[] args) {
                return new DoubleData(String.valueOf(encoder.getDistance()));
            }
        });
        Methods.METHODS_STORAGE.addMethod("resetDistance", new MethodBody() {
            public void run(Data[] args) {
                encoder.reset();
            }
        });
        Methods.METHODS_STORAGE.addMethod("collapseAlignment", new MethodBody() {
            public void run(Data[] args) {
                alignmentSystem.collapse();
            }
        });
        Methods.METHODS_STORAGE.addMethod("setLongAlignment", new MethodBody() {
            public void run(Data[] args) {
                alignmentSystem.setLong();
            }
        });
        Methods.METHODS_STORAGE.addMethod("setShortAlignment", new MethodBody() {
            public void run(Data[] args) {
                alignmentSystem.setShort();
            }
        });
    }
}
