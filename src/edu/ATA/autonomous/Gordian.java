package edu.ATA.autonomous;

import ATA.gordian.Script;
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

    private static void init(ShiftingDrivetrain drivetrain, Shooter shooter, 
            BangBangModule bangBangModule, AlignmentSystem alignmentSystem) {
        // Insert all methods, variables, returning methods and initialization code here.
    }
}
