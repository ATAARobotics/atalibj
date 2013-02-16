package edu.first.module.driving;

/**
 * Interface for driving functions that change the speed.
 *
 * @author Joel Gallant
 */
public interface Function {

    /**
     * Returns the speed based on a function.
     *
     * @param input original speed
     * @return transformed speed
     */
    public double F(double input);
}
