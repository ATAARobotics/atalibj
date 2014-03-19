package ata2014.settings;

import edu.first.util.File;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Files {

    File portFile = new File("Ports.txt");
    File settingsFile = new File("Settings.txt");
    File constantsFile = new File("Constants.txt");
    File logFile = new File("Log.txt");
}
