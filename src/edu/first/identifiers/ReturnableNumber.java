package edu.first.identifiers;

/**
 * Interface representing something that has a double value. Can return a
 * different value at different times, which is what makes it useful.
 *
 * @author Joel Gallant
 */
public interface ReturnableNumber {

    /**
     * Returns the value of the number.
     *
     * @return double value
     */
    double get();

    /**
     * A final, non-editable double value wrapper.
     */
    public static class Number implements ReturnableNumber {

        private final double number;

        /**
         * Constructs the number using the final value.
         *
         * @param number value to set
         */
        public Number(double number) {
            this.number = number;
        }

        public double get() {
            return number;
        }
    }
}
