package edu.ATA.bindings;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public abstract class Bindable implements BindableAxises, BindableButtons {

    protected static final String WHEN_PRESSED = "WNP", WHILE_PRESSED = "WLP", WHEN_RELEASED = "WNR", WHILE_RELEASED = "WLR", AXIS = "AXS";
    private final Hashtable bindings = new Hashtable();
    private final Hashtable pressed = new Hashtable();

    public final void bindWhenPressed(int port, CommandBind command) {
        addBind(new BindKey(WHEN_PRESSED, port), command);
    }

    public final void bindWhilePressed(int port, CommandBind command) {
        addBind(new BindKey(WHILE_PRESSED, port), command);
    }

    public final void bindWhenReleased(int port, CommandBind command) {
        addBind(new BindKey(WHEN_RELEASED, port), command);
    }

    public final void bindWhileReleased(int port, CommandBind command) {
        addBind(new BindKey(WHILE_RELEASED, port), command);
    }

    public final void bindAxis(int port, AxisBind axisBind) {
        addBind(new BindKey(AXIS, port), axisBind);
    }

    public final void doBinds() {
        Enumeration e = bindings.keys();
        while (e.hasMoreElements()) {
            doBind((BindKey) e.nextElement());
        }
    }

    public final void removeBinds(int port) {
        Enumeration e = bindings.keys();
        while (e.hasMoreElements()) {
            BindKey key = (BindKey) e.nextElement();
            if (key.port == port) {
                bindings.remove(key);
                pressed.remove(key);
            }
        }
    }

    public final void removeAllBinds() {
        Enumeration e = bindings.keys();
        while (e.hasMoreElements()) {
            BindKey key = (BindKey) e.nextElement();
            bindings.remove(key);
            pressed.remove(key);
        }
    }

    private void addBind(BindKey key, Bind bind) {
        bindings.put(key, bind);
        if (key.pressedNeeded) {
            pressed.put(key, Boolean.FALSE);
        }
    }

    private void doBind(BindKey key) {
        Bind bind = (Bind) bindings.get(key);
        String type = key.type;
        if (type.equals(WHEN_PRESSED)) {
            doWhenPressed(key, (CommandBind) bind);
        } else if (type.equals(WHILE_PRESSED)) {
            doWhilePressed(key, (CommandBind) bind);
        } else if (type.equals(WHEN_RELEASED)) {
            doWhenReleased(key, (CommandBind) bind);
        } else if (type.equals(WHILE_RELEASED)) {
            doWhileReleased(key, (CommandBind) bind);
        } else if (type.equals(AXIS)) {
            doAxisBind(key, (AxisBind) bind);
        }
    }

    private void doWhenPressed(BindKey key, CommandBind commandBind) {
        if (pressed.get(key).equals(Boolean.FALSE)) {
            if (getPressed(key.port)) {
                commandBind.run();
                pressed.put(key, Boolean.TRUE);
            }
        } else if (!getPressed(key.port)) {
            pressed.put(key, Boolean.FALSE);
        }
    }

    private void doWhilePressed(BindKey key, CommandBind commandBind) {
        if (getPressed(key.port)) {
            commandBind.run();
        }
    }

    private void doWhenReleased(BindKey key, CommandBind commandBind) {
        if (pressed.get(key).equals(Boolean.TRUE)) {
            if (!getPressed(key.port)) {
                commandBind.run();
                pressed.put(key, Boolean.FALSE);
            }
        } else if (getPressed(key.port)) {
            pressed.put(key, Boolean.TRUE);
        }
    }

    private void doWhileReleased(BindKey key, CommandBind commandBind) {
        if (!getPressed(key.port)) {
            commandBind.run();
        }
    }

    private void doAxisBind(BindKey key, AxisBind axisBind) {
        axisBind.set(getAxisValue(key.port));
    }

    private final class BindKey {

        private final String type;
        private final int port;
        private final boolean pressedNeeded;

        public BindKey(String type, int port) {
            this.type = type;
            this.port = port;
            pressedNeeded = type.equals(WHEN_PRESSED) || type.equals(WHEN_RELEASED);
        }

        public int hashCode() {
            int hash = 5;
            hash = 79 * hash + (this.type != null ? this.type.hashCode() : 0);
            hash = 79 * hash + this.port;
            return hash;
        }

        public boolean equals(Object obj) {
            if (obj instanceof BindKey) {
                BindKey opp = (BindKey) obj;
                return type.equals(opp.type) && port == opp.port;
            } else {
                return false;
            }
        }
    }
}
