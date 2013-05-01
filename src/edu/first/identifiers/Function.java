package edu.first.identifiers;

import edu.wpi.first.wpilibj.networktables2.util.List;

/**
 * A function that can be applied to a double.
 *
 * @author Joel Gallant
 */
public interface Function {

    /**
     * A placement function that returns the value given.
     */
    public static final class DefaultFunction implements Function {

        public double apply(double start) {
            return start;
        }
    }

    /**
     * Function that returns the value multiplied by a coefficient.
     */
    public static final class ProductFunction implements Function {

        private final double coefficient;

        /**
         * Constructs the function using the coefficient.
         *
         * @param coefficient number to multiply by
         */
        public ProductFunction(double coefficient) {
            this.coefficient = coefficient;
        }

        public double apply(double start) {
            return start * coefficient;
        }
    }

    /**
     * Function that returns the value divided by a divisor.
     */
    public static final class QuotientFunction implements Function {

        private final double divisor;

        /**
         * Constructs the function using the divisor.
         *
         * @param divisor number to divide by
         */
        public QuotientFunction(double divisor) {
            this.divisor = divisor;
        }

        public double apply(double start) {
            return start / divisor;
        }
    }

    /**
     * Function that returns the value added with a number.
     */
    public static final class SumFunction implements Function {

        private final double add;

        /**
         * Constructs the function using a number to add.
         *
         * @param add number to add
         */
        public SumFunction(double add) {
            this.add = add;
        }

        public double apply(double start) {
            return start + add;
        }
    }

    /**
     * Function that returns the value minus another number.
     */
    public static final class DifferenceFunction implements Function {

        private final double subtract;

        /**
         * Constructs the function using a number to subtract.
         *
         * @param subtract number to subtract
         */
        public DifferenceFunction(double subtract) {
            this.subtract = subtract;
        }

        public double apply(double start) {
            return start - subtract;
        }
    }

    /**
     * Function that returns the opposite (+ > -, - > +) of the original number.
     */
    public static final class OppositeFunction implements Function {

        public double apply(double start) {
            return -start;
        }
    }

    /**
     * Function that returns the inverse of the original number. (1 / x)
     */
    public static final class InverseFunction implements Function {

        public double apply(double start) {
            return 1 / start;
        }
    }

    /**
     * Function that returns the square of the original number. (x ^ 2)
     */
    public static final class SquaredFunction implements Function {

        public double apply(double start) {
            return start * start;
        }
    }

    /**
     * Function that returns the result of an array of functions applied to it.
     */
    public static final class CompoundFunction implements Function {

        private final Function[] functions;

        /**
         * Constructs the function using all the functions to apply.
         *
         * @param functions all the functions to apply to the original number
         */
        public CompoundFunction(Function[] functions) {
            this.functions = functions;
        }

        public double apply(double start) {
            for (int x = 0; x < functions.length; x++) {
                start = functions[x].apply(start);
            }
            return start;
        }
    }

    /**
     * Function that contains a list of functions that can be changed.
     */
    public static final class DynamicFunction implements Function {

        private final List functions = new List();

        /**
         * Constructs the function with no functions within it.
         */
        public DynamicFunction() {
        }

        /**
         * Adds a function to the dynamic function, which will be applied.
         *
         * @param function new function to apply to the original number
         */
        public void add(Function function) {
            functions.add(function);
        }

        /**
         * Removes a function from the dynamic function.
         *
         * @param function function to remove from the dynamic function
         */
        public void remove(Function function) {
            functions.remove(function);
        }

        public double apply(double start) {
            for (int x = 0; x < functions.size(); x++) {
                start = ((Function) functions.get(x)).apply(start);
            }
            return start;
        }
    }

    /**
     * Applies the function to the number.
     *
     * @param start original value
     * @return transformed value
     */
    double apply(double start);
}
