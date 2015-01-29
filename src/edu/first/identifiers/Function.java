package edu.first.identifiers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * General interface for classes that perform some kind of change to a number.
 *
 * @since June 01 13
 * @author Joel Gallant
 */
public interface Function {

    /**
     * Transforms the number to a different number.
     *
     * @param in input number
     * @return output number
     */
    public double F(double in);

    /**
     * A placement function that returns the value given.
     */
    public static final class DefaultFunction implements Function {

        @Override
        public double F(double start) {
            return start;
        }
    }

    /**
     * Function that returns the value multiplied by a coefficient.
     */
    public static final class ProductFunction implements Function {

        private final Input coefficient;

        /**
         * Constructs the function using the coefficient.
         *
         * @param coefficient number to multiply by
         */
        public ProductFunction(double coefficient) {
            this.coefficient = new StaticInput(coefficient);
        }

        /**
         * Constructs the function using the coefficient.
         *
         * @param coefficient number to multiply by
         */
        public ProductFunction(Input coefficient) {
            this.coefficient = coefficient;
        }

        @Override
        public double F(double start) {
            return start * coefficient.get();
        }
    }

    /**
     * Function that returns the value divided by a divisor.
     */
    public static final class QuotientFunction implements Function {

        private final Input divisor;

        /**
         * Constructs the function using the divisor.
         *
         * @param divisor number to divide by
         */
        public QuotientFunction(double divisor) {
            this.divisor = new StaticInput(divisor);
        }

        /**
         * Constructs the function using the divisor.
         *
         * @param divisor number to divide by
         */
        public QuotientFunction(Input divisor) {
            this.divisor = divisor;
        }

        @Override
        public double F(double start) {
            return start / divisor.get();
        }
    }

    /**
     * Function that returns the value added with a number.
     */
    public static final class SumFunction implements Function {

        private final Input add;

        /**
         * Constructs the function using a number to add.
         *
         * @param add number to add
         */
        public SumFunction(double add) {
            this.add = new StaticInput(add);
        }

        public SumFunction(Input add) {
            this.add = add;
        }

        @Override
        public double F(double start) {
            return start + add.get();
        }
    }

    /**
     * Function that returns the value minus another number.
     */
    public static final class DifferenceFunction implements Function {

        private final Input subtract;

        /**
         * Constructs the function using a number to subtract.
         *
         * @param subtract number to subtract
         */
        public DifferenceFunction(double subtract) {
            this.subtract = new StaticInput(subtract);
        }

        /**
         * Constructs the function using a number to subtract.
         *
         * @param subtract number to subtract
         */
        public DifferenceFunction(Input subtract) {
            this.subtract = subtract;
        }

        @Override
        public double F(double start) {
            return start - subtract.get();
        }
    }

    /**
     * Function that returns the opposite (+ > -, - > +) of the original number.
     */
    public static final class OppositeFunction implements Function {

        @Override
        public double F(double start) {
            return -start;
        }
    }

    /**
     * Function that returns the inverse of the original number. (1 / x)
     */
    public static final class InverseFunction implements Function {

        @Override
        public double F(double start) {
            return 1 / start;
        }
    }

    /**
     * Function that returns the square of the original number. (x ^ 2)
     */
    public static final class SquaredFunction implements Function {

        @Override
        public double F(double start) {
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

        @Override
        public double F(double start) {
            for (Function function : functions) {
                start = function.F(start);
            }
            return start;
        }
    }

    /**
     * Function that contains a list of functions that can be changed.
     */
    public static final class DynamicFunction implements Function {

        private final List<Function> functions = new ArrayList<>();

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

        @Override
        public double F(double start) {
            Iterator<Function> i = functions.iterator();
            while (i.hasNext()) {
                start = ((Function) i.next()).F(start);
            }
            return start;
        }
    }
}
