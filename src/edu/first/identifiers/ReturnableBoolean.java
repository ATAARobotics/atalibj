package edu.first.identifiers;

/**
 * Interface representing something that has a boolean value. Can return a
 * different value at different times, which is what makes it useful.
 *
 * @author Joel Gallant
 */
public interface ReturnableBoolean {

    /**
     * Returns the value of the boolean.
     *
     * @return boolean value
     */
    boolean get();

    /**
     * A final, non-editable boolean value wrapper.
     */
    public static final class Boolean implements ReturnableBoolean {

        private final boolean value;

        /**
         * Constructs the boolean using the final value.
         *
         * @param value value to set
         */
        public Boolean(boolean value) {
            this.value = value;
        }

        public boolean get() {
            return value;
        }
    }
}
