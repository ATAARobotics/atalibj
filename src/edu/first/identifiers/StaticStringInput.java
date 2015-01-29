package edu.first.identifiers;

/**
 * String input that will always return the same value.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class StaticStringInput implements StringInput {

    private final String input;

    /**
     * Constructs the input with the value to always return.
     *
     * @param input value to return
     */
    public StaticStringInput(String input) {
        this.input = input;
    }

    @Override
    public String getValue() {
        return input;
    }
}
