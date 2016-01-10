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
    @Override
    public double get() {
        double total = 0;
        for (Input input1 : input) {
            total += input1.get();
        }
        return total / (double) input.length;
    }

}
