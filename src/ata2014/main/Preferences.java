package ata2014.main;

import api.gordian.storage.InternalNotFoundException;
import api.gordian.storage.Variables;
import edu.first.util.File;
import edu.first.util.TextFiles;
import edu.first.util.log.Logger;
import org.gordian.scope.GordianScope;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class Preferences {
    
    public static Preferences preferences = new Preferences(new File("Preferences.txt"));
    
    final Variables variables;
    
    public Preferences(File file) {
        GordianScope gordian = new GordianScope();
        gordian.run(TextFiles.getTextFromFile(file));
        variables = gordian.variables();
    }
    
    public api.gordian.Object get(String key) {
        try {
            return variables.get(key);
        } catch (InternalNotFoundException e) {
            Logger.getLogger(this).error("Could not find preference " + key, e);
            throw new NullPointerException();
        }
    }
}
