package edu.gordian.scopes;

import edu.gordian.Strings;
import edu.gordian.elements.methods.MethodBase;
import edu.gordian.values.ReturningMethodBase;
import edu.gordian.values.Value;

/**
 * The storage mechanism for variables, returning methods and normal methods.
 *
 * @author Joel Gallant
 */
public final class ScopeStorage {

    private final DualList variables;
    private final DualList returning;
    private final DualList methods;

    /**
     * Constructs an empty scope storage. This does not inherit any storage.
     */
    public ScopeStorage() {
        variables = new Variables();
        returning = new Returning();
        methods = new Methods();
    }

    /**
     * Constructs a scope storage.
     *
     * @param parent parent storage to inherit elements from
     */
    public ScopeStorage(ScopeStorage parent) {
        variables = new Variables(parent.variables);
        returning = new Returning(parent.returning);
        methods = new Methods(parent.methods);
    }

    /**
     * Returns all of the variables.
     *
     * @return declared variables
     */
    DualList getVariables() {
        return variables;
    }

    /**
     * Returns all of the returning methods.
     *
     * @return returning methods
     */
    DualList getReturning() {
        return returning;
    }

    /**
     * Returns all of the void methods.
     *
     * @return methods
     */
    DualList getMethods() {
        return methods;
    }

    /**
     * Returns a representation of all the variables within the storage.
     *
     * @return all variables
     */
    public String toString() {
        return "Variables=" + variables + " Returning=" + returning + " Methods=" + methods;
    }

    private static boolean isValidKey(String key) {
        try {
            Double.parseDouble(key);
            return false;
        } catch (NumberFormatException ex) {
            return !(Strings.isEmpty(key) || Strings.contains(key, "\"")
                    || key.equalsIgnoreCase("true") || key.equalsIgnoreCase("false")
                    || key.startsWith("!") || Strings.contains(key, "||") || Strings.contains(key, "&&")
                    || Strings.contains(key, '=') || Strings.contains(key, '>') || Strings.contains(key, '<')
                    || Strings.contains(key, '(') || Strings.contains(key, ')') || Strings.contains(key, '+')
                    || Strings.contains(key, '-') || Strings.contains(key, '*') || Strings.contains(key, '/'));
        }
    }

    private static boolean isValidMethod(String key) {
        return !Strings.contains(key, '=');
    }

    private static final class Variables extends DualList {

        public Variables() {
        }

        public Variables(DualList parent) {
            super(parent);
        }

        public void setPrivateValue(String key, Object v) {
            if (!(v instanceof Value)) {
                throw new IllegalArgumentException("Saving a non-value");
            }
            checkKey(key);
            super.setPrivateValue(key, v);
        }

        public void setPublicValue(String key, Object v) {
            if (!(v instanceof Value)) {
                throw new IllegalArgumentException("Saving a non-value");
            }
            checkKey(key);
            super.setPublicValue(key, v);
        }

        public void checkKey(String key) {
            if (!isValidKey(key)) {
                throw new RuntimeException(key + " is an illegal key for a variable (conflicts with parsing)"
                        + "\n\tNames cannot be empty or contain any of these characters: \",(equal)true,(equal)false,"
                        + "(starts with)!, ||, &&, =, >, <, (, ), +, -, *, /");
            }
        }
    }

    private static final class Returning extends DualList {

        public Returning() {
        }

        public Returning(DualList parent) {
            super(parent);
        }

        public void setPrivateValue(String key, Object v) {
            if (!(v instanceof ReturningMethodBase)) {
                throw new IllegalArgumentException("Saving a non-value");
            }
            checkKey(key);
            super.setPrivateValue(key, v);
        }

        public void setPublicValue(String key, Object v) {
            if (!(v instanceof ReturningMethodBase)) {
                throw new IllegalArgumentException("Saving a non-value");
            }
            checkKey(key);
            super.setPublicValue(key, v);
        }

        public void checkKey(String key) {
            if (!isValidKey(key)) {
                throw new RuntimeException(key + " is an illegal key for a returning method (conflicts with parsing)"
                        + "\n\tNames cannot be empty or contain any of these characters: \",(equal)true,(equal)false,"
                        + "(starts with)!, ||, &&, =, >, <, (, ), +, -, *, /");
            }
        }
    }

    private static final class Methods extends DualList {

        public Methods() {
        }

        public Methods(DualList parent) {
            super(parent);
        }

        public void setPrivateValue(String key, Object v) {
            if (!(v instanceof MethodBase)) {
                throw new IllegalArgumentException("Saving a non-value");
            }
            checkKey(key);
            super.setPrivateValue(key, v);
        }

        public void setPublicValue(String key, Object v) {
            if (!(v instanceof MethodBase)) {
                throw new IllegalArgumentException("Saving a non-value");
            }
            checkKey(key);
            super.setPublicValue(key, v);
        }

        public void checkKey(String key) {
            if (!isValidMethod(key)) {
                throw new RuntimeException(key + " is an illegal name for a method (cannot contain =)");
            }
        }
    }
}