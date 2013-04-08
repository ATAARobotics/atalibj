package edu.first.identifiers;

import edu.wpi.first.wpilibj.networktables2.util.List;

public interface Function {

    public static final class DefaultFunction implements Function {

        public double apply(double start) {
            return start;
        }
    }

    public static final class ProductFunction implements Function {

        private final double coefficient;

        public ProductFunction(double coefficient) {
            this.coefficient = coefficient;
        }

        public double apply(double start) {
            return start * coefficient;
        }
    }

    public static final class QuotientFunction implements Function {

        private final double divisor;

        public QuotientFunction(double divisor) {
            this.divisor = divisor;
        }

        public double apply(double start) {
            return start / divisor;
        }
    }

    public static final class SumFunction implements Function {

        private final double add;

        public SumFunction(double add) {
            this.add = add;
        }

        public double apply(double start) {
            return start + add;
        }
    }

    public static final class DifferenceFunction implements Function {

        private final double subtract;

        public DifferenceFunction(double subtract) {
            this.subtract = subtract;
        }

        public double apply(double start) {
            return start - subtract;
        }
    }

    public static final class OppositeFunction implements Function {

        public double apply(double start) {
            return -start;
        }
    }

    public static final class InverseFunction implements Function {

        public double apply(double start) {
            return 1 / start;
        }
    }

    public static final class CompoundFunction implements Function {

        private final Function[] functions;

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

    public static final class DynamicFunction implements Function {

        private final List functions = new List();

        public DynamicFunction() {
        }

        public void add(Function function) {
            functions.add(function);
        }

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

    double apply(double start);
}
