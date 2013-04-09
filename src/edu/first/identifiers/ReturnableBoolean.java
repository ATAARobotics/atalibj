package edu.first.identifiers;

public interface ReturnableBoolean {

    boolean get();

    public static class Boolean implements ReturnableBoolean {

        private final boolean value;

        public Boolean(boolean value) {
            this.value = value;
        }

        public boolean get() {
            return value;
        }
    }
}
