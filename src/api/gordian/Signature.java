package api.gordian;

/**
 * The signature for arguments. A signature verifies that the user has given the
 * correct types of arguments.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class Signature {

    private final api.gordian.Class[] types;

    /**
     * Constructs an empty signature - does not accept any arguments.
     */
    public Signature() {
        this(new Class[0]);
    }

    /**
     * Constructs a signature with a given classes as argument types.
     *
     * @param types the possible types for arguments
     */
    public Signature(Class[] types) {
        this.types = types;
    }

    /**
     * Returns the array of types given in construction. These are the
     * acceptable types of arguments in each position.
     *
     * @return types for the arguments in the order they are given
     */
    public Class[] getTypes() {
        return types;
    }

    /**
     * Returns whether the signature matches this one.
     *
     * @param signature signature to test
     * @return if signature fits under requirements of this one
     */
    public boolean matches(Signature signature) {
        try {
            if (signature.types.length != types.length) {
                return false;
            }
            for (int x = 0; x < types.length; x++) {
                if (!types[x].equals(signature.types[x])) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
