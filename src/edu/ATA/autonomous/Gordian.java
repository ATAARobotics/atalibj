package edu.ATA.autonomous;

import ATA.gordian.Data;
import ATA.gordian.Script;
import ATA.gordian.data.BooleanData;
import ATA.gordian.data.NumberData;
import ATA.gordian.data.ReturningMethod;
import ATA.gordian.instructions.MethodBody;
import ATA.gordian.storage.Methods;
import edu.ATA.main.Logger;
import edu.ATA.module.subsystems.AlignmentSystem;
import edu.ATA.module.subsystems.ShiftingDrivetrain;
import edu.ATA.module.subsystems.Shooter;
import edu.ATA.module.target.BangBangModule;
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
     */
    public static void ensureInit(ShiftingDrivetrain drivetrain, Shooter shooter,
            BangBangModule bangBangModule, AlignmentSystem alignmentSystem) {
        if (!init) {
            init(drivetrain, shooter, bangBangModule, alignmentSystem);
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
            final BangBangModule bangBangModule, final AlignmentSystem alignmentSystem) {
        // Insert all methods, variables, returning methods and initialization code here.
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
                drivetrain.driveTo(((NumberData)args[0]).doubleValue(), 2);
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
                bangBangModule.setSetpoint(((NumberData) args[0]).doubleValue());
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
