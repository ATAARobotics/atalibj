package edu.ata.script.instructions;

import edu.ata.script.Data;
import edu.ata.script.storage.Methods;

/**
 * Basic method body used to add new methods to {@link Methods#METHODS_STORAGE}.
 *
 * @author Joel Gallant
 */
public interface MethodBody {

    /**
     * Runs the method when called. Uses an array of {@link Data} to represent
     * the arguments given. When no arguments are present, it's size is 0.
     * {@code args} will never equal null.
     *
     * @param args arguments given to method
     */
    void run(Data[] args);
}
