package edu.first.identifiers;

public interface ReturnableNumber {

    double get();

    public static class Number implements ReturnableNumber {

        private final double number;

        public Number(double number) {
            this.number = number;
        }

        public double get() {
            return number;
        }
    }
}
