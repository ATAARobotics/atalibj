package ATA.gordian.storage;

import ATA.gordian.Data;
import ATA.gordian.data.NumberData;
import ATA.gordian.instructions.MethodBody;

/**
 * Method storage that stores all methods. print() and wait(double) are added
 * statically.
 *
 * @author Joel Gallant
 */
public class Methods extends Storage {

    /**
     * Static storage of methods. Please be careful with using this field.
     */
    public static final Methods METHODS_STORAGE = new Methods();

    static {
        METHODS_STORAGE.addMethod("print", new MethodBody() {
            public void run(Data[] args) {
                String s = "";
                for (int x = 0; x < args.length; x++) {
                    s += args[x].getValue();
                }
                System.out.println(s);
            }
        });
        METHODS_STORAGE.addMethod("wait", new MethodBody() {
            public void run(Data[] args) {
                double t = 0;
                if (args[0] instanceof NumberData) {
                    t = ((NumberData) args[0]).doubleValue();
                }
                if (t != 0) {
                    try {
                        Thread.sleep((long) (t * 1e3));
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }

    /**
     * Adds an object to the storage. Throws a runtime exception is it is not a
     * {@link MethodBody}.
     *
     * @param key key to save under
     * @param value value to save
     */
    protected void add(String key, Object value) {
        if (!(value instanceof MethodBody)) {
            throw new RuntimeException("Added a method that WAS NOT A METHOD - " + key);
        } else {
            getStorage().put(key, value);
        }
    }

    /**
     * Type-safe way of adding methods. Is equivalent to
     * {@code add(name, method)}.
     *
     * @param name key to save under
     * @param method method body
     */
    public void addMethod(String name, MethodBody method) {
        add(name, method);
    }
}
