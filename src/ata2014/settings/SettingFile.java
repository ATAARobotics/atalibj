package ata2014.settings;

import api.gordian.storage.InternalNotFoundException;
import api.gordian.storage.Variables;
import edu.first.identifiers.Input;
import edu.first.identifiers.Position;
import edu.first.identifiers.StringInput;
import edu.first.util.File;
import edu.first.util.TextFiles;
import edu.first.util.list.List;
import edu.first.util.log.Logger;
import java.util.Hashtable;
import org.gordian.scope.GordianScope;
import org.gordian.value.GordianBoolean;
import org.gordian.value.GordianNumber;
import org.gordian.value.GordianString;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class SettingFile {

    private final File file;
    private Hashtable values;

    public SettingFile(File file) {
        this.file = file;
        reload();
    }

    public SettingFile reload() {
        String fileContents = TextFiles.getTextFromFile(file);
        if (fileContents != null) {
            GordianScope scope = new GordianScope();
            scope.run(fileContents);
            Variables variables = scope.variables();
            List keys = variables.keys();
            this.values = new Hashtable();
            for (int x = 0; x < keys.size(); x++) {
                try {
                    this.values.put(keys.get(x), variables.get((String) keys.get(x)));
                } catch (InternalNotFoundException ex) {
                    // should never happen...
                    Logger.getLogger(this).error(keys.get(x) + " could not be found", ex);
                }
            }
        } else {
            Logger.getLogger(this).warn(file + " was not found");
            this.values = new Hashtable();
        }
        return this;
    }

    public double getDouble(String key, double backup) {
        if (values.containsKey(key)) {
            return ((GordianNumber) values.get(key)).getValue();
        } else {
            return backup;
        }
    }

    public Input getDoubleSetting(final String key, final double backup) {
        return new Input() {
            public double get() {
                return getDouble(key, backup);
            }
        };
    }

    public int getInt(String key, int backup) {
        if (values.containsKey(key)) {
            return ((GordianNumber) values.get(key)).getInt();
        } else {
            return backup;
        }
    }

    public Input getIntSetting(final String key, final int backup) {
        return new Input() {
            public double get() {
                return getInt(key, backup);
            }
        };
    }

    public boolean getBoolean(String key, boolean backup) {
        if (values.containsKey(key)) {
            return ((GordianBoolean) values.get(key)).getValue();
        } else {
            return backup;
        }
    }

    public Position getBooleanSetting(final String key, final boolean backup) {
        return new Position() {
            public boolean getPosition() {
                return getBoolean(key, backup);
            }
        };
    }

    public String getString(String key, String backup) {
        if (values.containsKey(key)) {
            return ((GordianString) values.get(key)).getValue();
        } else {
            return backup;
        }
    }

    public StringInput getStringSetting(final String key, final String backup) {
        return new StringInput() {
            public String getValue() {
                return getString(key, backup);
            }
        };
    }

    public Object get(String key, Object backup) {
        if (values.containsKey(key)) {
            return values.get(key);
        } else {
            return backup;
        }
    }
}
