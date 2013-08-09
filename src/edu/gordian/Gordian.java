package edu.gordian;

import edu.gordian.scopes.Scope;
import edu.gordian.elements.methods.UserMethod;
import edu.gordian.values.UserReturningMethod;

/**
 * The Gordian programming language beginning point. Runs scripts by extension
 * of {@link Scope}. For more dynamic running of programs (running multiple
 * scripts, accessing internals, etc.), use {@link Scope}.
 *
 * @author Joel Gallant
 */
public final class Gordian {

    private Gordian() {
    }

    /**
     * Runs the script.
     *
     * @param methods the methods that can be accessed by the program
     * @param returning the returning methods that can be accessed by the
     * program
     * @param script the script to run
     * @throws Exception when script running encounters any kind of error
     */
    public static void run(UserMethod[] methods, UserReturningMethod[] returning, String script) throws Exception {
        new Scope(methods, returning).run(script);
    }

    /**
     * Runs the script.
     *
     * @param methods the methods that can be accessed by the program
     * @param script the script to run
     * @throws Exception when script running encounters any kind of error
     */
    public static void run(UserMethod[] methods, String script) throws Exception {
        run(methods, new UserReturningMethod[0], script);
    }

    /**
     * Runs the script.
     *
     * @param script the script to run
     * @throws Exception when script running encounters any kind of error
     */
    public static void run(String script) throws Exception {
        new Scope().run(script);
    }
}
