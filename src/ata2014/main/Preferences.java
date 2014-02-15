package ata2014.main;

import api.gordian.storage.InternalNotFoundException;
import api.gordian.storage.Variables;
import edu.first.util.File;
import edu.first.util.TextFiles;
import edu.first.util.log.Logger;
import org.gordian.scope.GordianScope;
import org.gordian.storage.GordianVariables;
import org.gordian.value.GordianBoolean;
import org.gordian.value.GordianNumber;
import org.gordian.value.GordianString;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class Preferences {

    private static final Preferences instance = new Preferences(new File("Preferences.txt"));
    private final Variables variables;

    public static Preferences getInstance() {
        return instance;
    }

    private Preferences(File file) {
        GordianScope gordian = new GordianScope();
        String contents = TextFiles.getTextFromFile(file);
        if (contents != null) {
            gordian.run(contents);
            variables = gordian.variables();
        } else {
            variables = new GordianVariables(true);
            TextFiles.writeAsFile(file, "# 2014 Preferences File");
            Logger.getLogger(this).warn("Preferences file was not found / read from. Created one for you.");
        }
    }

    public int getInt(String key, int backup) {
        try {
            return ((GordianNumber) variables.get(key)).getInt();
        } catch (InternalNotFoundException ex) {
            System.out.println("Didn't find " + key);
            return backup;
        }
    }

    public double getDouble(String key, double backup) {
        try {
            return ((GordianNumber) variables.get(key)).getValue();
        } catch (InternalNotFoundException ex) {
            System.out.println("Didn't find " + key);
            return backup;
        }
    }

    public boolean getBoolean(String key, boolean backup) {
        try {
            return ((GordianBoolean) variables.get(key)).getValue();
        } catch (InternalNotFoundException ex) {
            System.out.println("Didn't find " + key);
            return backup;
        }
    }

    public String getString(String key, String backup) {
        try {
            return ((GordianString) variables.get(key)).getValue();
        } catch (InternalNotFoundException ex) {
            System.out.println("Didn't find " + key);
            return backup;
        }
    }
}
