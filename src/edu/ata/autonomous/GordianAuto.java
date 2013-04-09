package edu.ata.autonomous;

import com.sun.squawk.microedition.io.FileConnection;
import edu.ata.commands.ArcadeDrive;
import edu.ata.commands.DriveDistance;
import edu.ata.commands.SetAlignment;
import edu.ata.commands.SetBitchBar;
import edu.ata.commands.SetCompressor;
import edu.ata.commands.SetGear;
import edu.ata.commands.SetLoader;
import edu.ata.commands.SetShooter;
import edu.ata.commands.SetSmartDashboard;
import edu.ata.commands.SetWinch;
import edu.ata.commands.SetWiper;
import edu.ata.commands.TankDrive;
import edu.ata.commands.TurnToAngle;
import edu.ata.commands.WipeWindshieldWiper;
import edu.ata.subsystems.AlignmentSystem;
import edu.ata.subsystems.BitchBar;
import edu.ata.subsystems.Compressor;
import edu.ata.subsystems.Drivetrain;
import edu.ata.subsystems.GearShifters;
import edu.ata.subsystems.Loader;
import edu.ata.subsystems.MovementSystem;
import edu.ata.subsystems.ShooterWheel;
import edu.ata.subsystems.SmartDashboardSender;
import edu.ata.subsystems.Winch;
import edu.ata.subsystems.WindshieldWiper;
import edu.first.utils.DriverstationInfo;
import edu.first.utils.Logger;
import edu.gordian.Gordian;
import edu.gordian.Variable;
import edu.gordian.method.BooleanReturningMethod;
import edu.gordian.method.NumberReturningMethod;
import edu.gordian.method.RunningMethod;
import edu.gordian.variable.BooleanInterface;
import edu.gordian.variable.NumberInterface;
import java.io.IOException;
import javax.microedition.io.Connector;

/**
 * Static class meant to keep Gordian in a state where it can run the current
 * script. To make sure the script uses all of the methods given, the
 * {@link Gordian#ensureInit()} method makes sure that all storage of methods
 * and variables are stored.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class GordianAuto {

    private static boolean init = false;
    private static Gordian gordian;
    private static AlignmentSystem alignmentSystem;
    private static BitchBar bitchBar;
    private static Compressor compressor;
    private static Drivetrain drivetrain;
    private static GearShifters gearShifters;
    private static Loader loader;
    private static MovementSystem movementSystem;
    private static ShooterWheel shooterWheel;
    private static SmartDashboardSender smartDashboardSender;
    private static Winch winch;
    private static WindshieldWiper windshieldWiper;

    private GordianAuto() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Makes sure that everything is ready to be run in Gordian. This includes
     * methods, variables, etc. You can generally accept that everything is
     * ready to be run after running this method.
     *
     * @param gearShifters shifting mechanism
     * @param drivetrain the drivetrain you are using
     * @param shooter the shooter you are using
     * @param bangBangModule the bang-bang module that you are using
     * @param alignmentSystem that alignment system that your are using
     * @param drivetrainControl moving module to move drivetrain
     * @param encoder encoder to get distance of drivetrain
     * @param gyro gyroscope for angle of the drivetrain
     */
    public static void ensureInit(AlignmentSystem alignmentSystem, BitchBar bitchBar,
            Compressor compressor, Drivetrain drivetrain, GearShifters gearShifters,
            Loader loader, MovementSystem movementSystem, ShooterWheel shooterWheel,
            SmartDashboardSender smartDashboardSender, Winch winch,
            WindshieldWiper windshieldWiper) {
        if (!init) {
            init = true;
            GordianAuto.alignmentSystem = alignmentSystem;
            GordianAuto.bitchBar = bitchBar;
            GordianAuto.compressor = compressor;
            GordianAuto.drivetrain = drivetrain;
            GordianAuto.gearShifters = gearShifters;
            GordianAuto.loader = loader;
            GordianAuto.movementSystem = movementSystem;
            GordianAuto.shooterWheel = shooterWheel;
            GordianAuto.smartDashboardSender = smartDashboardSender;
            GordianAuto.winch = winch;
            GordianAuto.windshieldWiper = windshieldWiper;
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
        FileConnection connection = (FileConnection) Connector.open("file:///" + fileName, Connector.READ);
        String script = Logger.getTextFromFile(connection);
        connection.close();
        gordian = new Gordian(script);
        init();
        gordian.run();
    }

    private static void init() {
        // Insert all methods, variables, returning methods and initialization code here.
        gordian.addMethod(new RunningMethod("log") {
            public void run(Variable[] args) {
                Logger.log(Logger.Urgency.USERMESSAGE, args[0].getValue().toString());
            }
        });
        gordian.addMethod(new RunningMethod("print") {
            public void run(Variable[] args) {
                System.out.println(args[0].getValue());
            }
        });
        gordian.addMethod(new BooleanReturningMethod("isEnabled") {
            public boolean getBoolean() {
                return DriverstationInfo.isEnabled();
            }
        });
        gordian.addMethod(new BooleanReturningMethod("isAutonomous") {
            public boolean getBoolean() {
                return DriverstationInfo.getGamePeriod().equals(DriverstationInfo.AUTONOMOUS);
            }
        });

        gordian.addMethod(new BooleanReturningMethod("alignmentOut") {
            public boolean getBoolean() {
                return alignmentSystem.isOut();
            }
        });
        gordian.addMethod(new BooleanReturningMethod("alignmentRight") {
            public boolean getBoolean() {
                return alignmentSystem.isRight();
            }
        });
        gordian.addMethod(new BooleanReturningMethod("alignmentLeft") {
            public boolean getBoolean() {
                return alignmentSystem.isLeft();
            }
        });
        gordian.addMethod(new BooleanReturningMethod("bitchBarOut") {
            public boolean getBoolean() {
                return bitchBar.isOut();
            }
        });
        gordian.addMethod(new BooleanReturningMethod("atPressure") {
            public boolean getBoolean() {
                return compressor.isAtPressure();
            }
        });
        gordian.addMethod(new NumberReturningMethod("gear") {
            public double getDouble() {
                return gearShifters.gear();
            }
        });
        gordian.addMethod(new BooleanReturningMethod("isFirstGear") {
            public boolean getBoolean() {
                return gearShifters.isFirstGear();
            }
        });
        gordian.addMethod(new BooleanReturningMethod("isSecondGear") {
            public boolean getBoolean() {
                return gearShifters.isSecondGear();
            }
        });
        gordian.addMethod(new BooleanReturningMethod("isLoaderOut") {
            public boolean getBoolean() {
                return loader.isOut();
            }
        });
        gordian.addMethod(new NumberReturningMethod("getShooterRPM") {
            public double getDouble() {
                return shooterWheel.getRPM();
            }
        });
        gordian.addMethod(new NumberReturningMethod("getShooterSetpointRPM") {
            public double getDouble() {
                return shooterWheel.getSetpointRPM();
            }
        });
        gordian.addMethod(new BooleanReturningMethod("shooterPastSetpoint") {
            public boolean getBoolean() {
                return shooterWheel.isPastSetpoint();
            }
        });
        gordian.addMethod(new NumberReturningMethod("winchPosition") {
            public double getDouble() {
                return winch.getPosition();
            }
        });
        gordian.addMethod(new NumberReturningMethod("wiperPosition") {
            public double getDouble() {
                return windshieldWiper.getPosition();
            }
        });

        gordian.addMethod(new RunningMethod("arcade") {
            public void run(Variable[] args) {
                new ArcadeDrive(drivetrain, ((NumberInterface) args[0]).doubleValue(), ((NumberInterface) args[1]).doubleValue(), false).run();
            }
        });
        gordian.addMethod(new RunningMethod("tank") {
            public void run(Variable[] args) {
                new TankDrive(drivetrain, ((NumberInterface) args[0]).doubleValue(), ((NumberInterface) args[1]).doubleValue(), false).run();
            }
        });
        gordian.addMethod(new RunningMethod("alignIn") {
            public void run(Variable[] args) {
                new SetAlignment(alignmentSystem, SetAlignment.IN, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("alignOut") {
            public void run(Variable[] args) {
                new SetAlignment(alignmentSystem, SetAlignment.OUT, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("alignLeft") {
            public void run(Variable[] args) {
                new SetAlignment(alignmentSystem, SetAlignment.LEFT, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("alignRight") {
            public void run(Variable[] args) {
                new SetAlignment(alignmentSystem, SetAlignment.RIGHT, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("bitchBarIn") {
            public void run(Variable[] args) {
                new SetBitchBar(bitchBar, SetBitchBar.IN, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("bitchBarOut") {
            public void run(Variable[] args) {
                new SetBitchBar(bitchBar, SetBitchBar.OUT, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("switchBitchBar") {
            public void run(Variable[] args) {
                new SetBitchBar(bitchBar, SetBitchBar.SWITCH, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("compressorOn") {
            public void run(Variable[] args) {
                new SetCompressor(compressor, SetCompressor.ON, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("compressorOff") {
            public void run(Variable[] args) {
                new SetCompressor(compressor, SetCompressor.OFF, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("switchCompressor") {
            public void run(Variable[] args) {
                new SetCompressor(compressor, SetCompressor.SWITCH, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("setFirstGear") {
            public void run(Variable[] args) {
                new SetGear(gearShifters, SetGear.FIRST, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("setSecondGear") {
            public void run(Variable[] args) {
                new SetGear(gearShifters, SetGear.SECOND, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("switchGear") {
            public void run(Variable[] args) {
                new SetGear(gearShifters, SetGear.SWITCH, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("setLoaderIn") {
            public void run(Variable[] args) {
                new SetLoader(loader, SetLoader.IN, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("setLoaderOut") {
            public void run(Variable[] args) {
                new SetLoader(loader, SetLoader.OUT, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("fireLoader") {
            public void run(Variable[] args) {
                new SetLoader(loader, SetLoader.FIRE, false).run();
            }
        });
        gordian.addMethod(new RunningMethod("driveDistance") {
            public void run(Variable[] args) {
                new DriveDistance(movementSystem, ((NumberInterface) args[0]).doubleValue(), false).run();
            }
        });
        gordian.addMethod(new RunningMethod("turnAngle") {
            public void run(Variable[] args) {
                new TurnToAngle(movementSystem, ((NumberInterface) args[0]).doubleValue(), false).run();
            }
        });
        gordian.addMethod(new RunningMethod("setShooter") {
            public void run(Variable[] args) {
                new SetShooter(shooterWheel, ((NumberInterface) args[0]).doubleValue(), false).run();
            }
        });
        gordian.addMethod(new RunningMethod("setDashboard") {
            public void run(Variable[] args) {
                new SetSmartDashboard(smartDashboardSender, ((BooleanInterface) args[0]).booleanValue(), false).run();
            }
        });
        gordian.addMethod(new RunningMethod("setWinchSpeed") {
            public void run(Variable[] args) {
                new SetWinch(winch, SetWinch.SPEED, ((NumberInterface) args[0]).doubleValue(), false).run();
            }
        });
        gordian.addMethod(new RunningMethod("setWinchPosition") {
            public void run(Variable[] args) {
                new SetWinch(winch, SetWinch.POSITION, ((NumberInterface) args[0]).doubleValue(), false).run();
            }
        });
        gordian.addMethod(new RunningMethod("setWiperSpeed") {
            public void run(Variable[] args) {
                new SetWiper(windshieldWiper, SetWiper.SPEED, ((NumberInterface) args[0]).doubleValue(), false).run();
            }
        });
        gordian.addMethod(new RunningMethod("setWiperPosition") {
            public void run(Variable[] args) {
                new SetWiper(windshieldWiper, SetWiper.PLACE, ((NumberInterface) args[0]).doubleValue(), false).run();
            }
        });
        gordian.addMethod(new RunningMethod("wipeWiper") {
            public void run(Variable[] args) {
                new WipeWindshieldWiper(windshieldWiper, false).run();
            }
        });
    }
}
