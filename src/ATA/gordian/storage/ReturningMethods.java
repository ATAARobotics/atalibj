package ATA.gordian.storage;

import ATA.gordian.Data;
import ATA.gordian.StringUtils;
import ATA.gordian.data.ReturningMethod;

/**
 * Storage for all returning methods.
 *
 * @author Joel Gallant
 */
public class ReturningMethods extends Storage {

    /**
     * Returns the value of a method based on its string value. Deciphers
     * arguments and looks at the storage, then returns the value.
     *
     * @param data literal value of the method in the code
     * @return value based on arguments
     */
    public static Data getMethodValue(String data) {
        String[] argsLiteral = StringUtils.split(data.substring(data.indexOf("(") + 1,
                data.lastIndexOf(')')), ',');
        Data[] args = new Data[argsLiteral.length];
        for (int x = 0; x < args.length; x++) {
            args[x] = Data.get(argsLiteral[x]);
        }
        return ((ReturningMethod) Data.RETURNING_METHODS.get(data.substring(0, data.indexOf("(")))).getValue(args);
    }

    /**
     * Adds an object to the storage. If the object is not an instance of
     * {@link ReturningMethod}, will throw a runtime exception.
     *
     * @param key key to save under
     * @param value value to save
     */
    protected void add(String key, Object value) {
        if (!(value instanceof ReturningMethod)) {
            throw new RuntimeException("Added new returning method to "
                    + "ReturningMethods that WAS NOT a ReturningMethod.");
        } else {
            getStorage().put(key, value);
        }
    }

    /**
     * Type-safe way of adding methods to the storage. Is equivalent to calling
     * {@code add(methodName, method)}.
     *
     * @param methodName name of the method
     * @param method method to save under the method name
     */
    public void addMethod(String methodName, ReturningMethod method) {
        add(methodName, method);
    }
}
