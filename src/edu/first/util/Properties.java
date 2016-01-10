package edu.first.util;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import edu.first.util.log.Logger;

/**
 * The class representation of files on the cRIO that contain "properties". This
 * means that there are different values that are accessible by a key. Property
 * files contain the values separated by lines, with an equals sign to indicate
 * what the value is.
 *
 * Ex.
 * <pre>
 * Key = Example Value
 * ShooterSpeed = 42
 * ShooterSpeedFar = 60
 * EncoderDistance = 12.32
 * </pre>
 *
 * Properties are stored as strings only, and cannot be verified as
 * doubles/ints/longs/etc. That has to be done in client code.
 *
 * @since May 13 13
 * @author Joel Gallant
 */
public final class Properties {

    private final String propertiesContent;
    private final Property[] properties;

    /**
     * Constructs the object that will read the properties file. No processing
     * or I/O is done inside of the constructor, so construction is not resource
     * heavy.
     *
     * For all intents and purposes, {@code fileName} should be the same as the
     * argument in {@link TextFiles#getTextFromFile(java.lang.String)}. That
     * definition is subject to change outside of this class' control.
     *
     * @param file file to get data from
     */
    public Properties(File file) {
        String f;
        try {
            f = TextFiles.getTextFromFile(file);
        } catch (IOException ex) {
            f = null;
        }
        propertiesContent = (f == null ? "" : f);

        StringTokenizer tokenizer = new StringTokenizer(propertiesContent, "\n\r=");
        Property[] p = new Property[tokenizer.countTokens() / 2];
        for (int x = 0; x < p.length; x++) {
            p[x] = new Property(tokenizer.nextToken(), tokenizer.nextToken());
        }
        // Buffer the array so less chance of accessing it mid-construction
        properties = p;
    }

    /**
     * Returns the string with the file's entire contents. Does no parse it into
     * properties.
     *
     * @return the contents of the file
     * @throws IOException when reading from the file throws an exception
     */
    public String getFileContents() throws IOException {
        return propertiesContent;
    }

    /**
     * Returns an array of properties which are suitable for using.
     *
     * Specifications of property files that are needed for this method to
     * return valid results:
     * <ul>
     * <li> All properties are separated by new lines (Windows / Unix Line
     * Endings)
     * <li> All keys are separated by their values using an equals sign
     * <li> No other equals signs are present
     * </ul>
     *
     * Spaces around the key and value (ex. key = value) are are disregarded.
     *
     * @return all properties in the file
     * @throws IOException when reading from the file throws an exception
     */
    public Property[] getProperties() throws IOException {
        return properties;
    }

    /**
     * Returns the property object that has the specific key.
     *
     * Returns null if no property under the name exists.
     *
     * @param key string used to declare the property
     * @return {@code Property} object corresponding to key
     */
    public Property getProperty(String key) {
        for (Property property : properties) {
            if (property.key.equals(key)) {
                return property;
            }
        }
        return null;
    }

    /**
     * Returns the actual value designated in the file under the key.
     *
     * @param key string used to declare the property
     * @return value given to the key in the file
     * @throws NullPointerException when key does not exist
     */
    public String getValue(String key) {
        return getProperty(key).value;
    }

    /**
     * Converts the {@link #getValue(java.lang.String)} value to an integer.
     *
     * @param key string used to declare the property
     * @return value given to the key in the file
     * @throws NumberFormatException when value is not an int
     * @throws NullPointerException when key does not exist
     */
    public int toInt(String key) {
        return Integer.parseInt(getValue(key));
    }

    /**
     * Converts the {@link #getValue(java.lang.String)} value to a double.
     *
     * @param key string used to declare the property
     * @return value given to the key in the file
     * @throws NumberFormatException when value is not a double
     * @throws NullPointerException when key does not exist
     */
    public double toDouble(String key) {
        return Double.parseDouble(getValue(key));
    }

    /**
     * Converts the {@link #getValue(java.lang.String)} value to a boolean.
     *
     * @param key string used to declare the property
     * @return value given to the key in the file (returns false unless
     * specifically "true")
     * @throws NullPointerException when key does not exist
     */
    public boolean toBoolean(String key) {
        return Boolean.parseBoolean(getValue(key));
    }

    /**
     * Returns the actual value designated in the file under the key.
     *
     * @param key string used to declare the property
     * @param backup value used if it did not exist in the file
     * @return value given to the key in the file
     */
    public String getValue(String key, String backup) {
        Property p = getProperty(key);
        if (p != null) {
            return p.value;
        } else {
            Logger.getLogger(getClass()).debug(key + " property not found - using " + backup);
            return backup;
        }
    }

    /**
     * Converts the {@link #getValue(java.lang.String)} value to an integer.
     *
     * @param key string used to declare the property
     * @param backup value used if it did not exist in the file
     * @return value given to the key in the file
     */
    public int toInt(String key, int backup) {
        try {
            return toInt(key);
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug(key + " property not found - using " + backup);
            return backup;
        }
    }

    /**
     * Converts the {@link #getValue(java.lang.String)} value to a double.
     *
     * @param key string used to declare the property
     * @param backup value used if it did not exist in the file
     * @return value given to the key in the file
     */
    public double toDouble(String key, double backup) {
        try {
            return toDouble(key);
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug(key + " property not found - using " + backup);
            return backup;
        }
    }

    /**
     * Converts the {@link #getValue(java.lang.String)} value to a boolean.
     *
     * @param key string used to declare the property
     * @param backup value used if it did not exist in the file
     * @return value given to the key in the file (returns false unless
     * specifically "true")
     */
    public boolean toBoolean(String key, boolean backup) {
        try {
            return toBoolean(key);
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug(key + " property not found - using " + backup);
            return backup;
        }
    }

    /**
     * A class used to represent properties inside of a file. Uses an internal
     * key to identify itself.
     */
    public static final class Property {

        private final String key;
        private final String value;

        private Property(String key, String value) {
            this.key = key.trim();
            this.value = value.trim();
        }

        /**
         * Returns the key that was used to store this value.
         *
         * @return key that property is saved as
         */
        public String getKey() {
            return key;
        }

        /**
         * Returns the value that is saved.
         *
         * @return value that property is in the file
         */
        public String getValue() {
            return value;
        }

        /**
         * Returns the equivalent of {@code key} + " = " + {@code value}.
         *
         * @return string as would appear in a file
         */
        @Override
        public String toString() {
            return key + " = " + value;
        }
    }
}
