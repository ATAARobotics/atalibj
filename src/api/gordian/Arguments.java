package api.gordian;

/**
 * Representation of arguments given to methods, constructors, etc.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class Arguments {

    private final Object[] args;
    private final Signature signature;

    /**
     * Constructs empty arguments. Has no objects.
     */
    public Arguments() {
        this(new Object[0]);
    }

    /**
     * Constructs arguments with given values.
     *
     * @param args arguments to use
     */
    public Arguments(Object[] args) {
        this.args = args;

        Class[] types = new Class[args.length];
        for (int x = 0; x < types.length; x++) {
            if (args[x] == null) {
                throw new NullPointerException("Null argument given! - " + args[x] + " index " + x + " in " + args.length);
            }
            types[x] = args[x].parentClass();
        }

        this.signature = new Signature(types);
    }

    /**
     * Returns the values of all the arguments.
     *
     * @return arguments to use
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * Returns an exact signature, using {@link Object#parentClass()} as the
     * classes.
     *
     * @return signature using exact classes of the arguments
     */
    public Signature getSignature() {
        return signature;
    }

}
