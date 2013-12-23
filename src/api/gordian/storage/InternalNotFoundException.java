package api.gordian.storage;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class InternalNotFoundException extends Exception {

    public InternalNotFoundException(String name) {
        super("The internal data named \"" + name + "\" does not appear to exist in the current gordian runtime.");
    }

}
