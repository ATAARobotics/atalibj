package ata2014.main;

import api.gordian.storage.InternalNotFoundException;
import api.gordian.storage.Variables;
import edu.first.util.File;
import edu.first.util.TextFiles;
import org.gordian.scope.GordianScope;
import org.gordian.value.GordianBoolean;
import org.gordian.value.GordianNull;
import org.gordian.value.GordianNumber;
import org.gordian.value.GordianString;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class SettingsFile {

    private final File file;
    private Variables settings;

    public SettingsFile(File file) {
        this.file = file;
        reload();
    }

    public void reload() {
        String fileContents = TextFiles.getTextFromFile(file);
        GordianScope scope = new GordianScope();
        scope.run(fileContents);
        this.settings = scope.variables();
    }

    public double getDouble(String key, double backup) {
        try {
            return ((GordianNumber) settings.get(key)).getValue();
        } catch (InternalNotFoundException e) {
            return backup;
        } catch (ClassCastException e) {
            return backup;
        }
    }

    public int getInt(String key, int backup) {
        try {
            return ((GordianNumber) settings.get(key)).getInt();
        } catch (InternalNotFoundException e) {
            return backup;
        } catch (ClassCastException e) {
            return backup;
        }
    }

    public boolean getBoolean(String key, boolean backup) {
        try {
            return ((GordianBoolean) settings.get(key)).getValue();
        } catch (InternalNotFoundException e) {
            return backup;
        } catch (ClassCastException e) {
            return backup;
        }
    }

    public String getString(String key, String backup) {
        try {
            return ((GordianString) settings.get(key)).getValue();
        } catch (InternalNotFoundException e) {
            return backup;
        } catch (ClassCastException e) {
            return backup;
        }
    }

    public Object get(String key, Object backup) {
        try {
            Object o = settings.get(key);
            if (o instanceof GordianNumber) {
                if (((GordianNumber) o).getValue() % 1 == 0) {
                    return Integer.valueOf(((GordianNumber) o).getInt());
                } else {
                    return Double.valueOf(((GordianNumber) o).getValue());
                }
            } else if (o instanceof GordianBoolean) {
                return Boolean.valueOf(((GordianBoolean) o).getValue());
            } else if (o instanceof GordianNull) {
                return null;
            } else if (o instanceof GordianString) {
                return ((GordianString) o).getValue();
            } else {
                return o;
            }
        } catch (InternalNotFoundException e) {
            return backup;
        }
    }
}
