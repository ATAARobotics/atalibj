package edu.first.identifiers;

/**
 * An input group that averages multiple inputs.
 * 
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class AverageInputGroup implements Input {

    private final Input[] input;

    /**
     * Construct the group with multiple inputs.
     * 
     * @param input array of inputs to use
     */
    public AverageInputGroup(Input[] input) {
        this.input = input;
    }

    /**
     * Returns the average of all inputs.
     * 
     * @return average value
     */
    public double get() {
        double total = 0;
        for (int x = 0; x < input.length; x++) {
            total += input[x].get();
        }
        return total / (double) input.length;
    }

}
