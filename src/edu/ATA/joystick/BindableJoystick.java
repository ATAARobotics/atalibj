package edu.ATA.joystick;

import edu.ATA.commands.Command;
import edu.ATA.commands.ConcurrentCommandGroup;
import edu.wpi.first.wpilibj.Joystick;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * A class meant to "bind" buttons with commands. This allows the user to press
 * a button, and have it perform a command. You can also bind multiple commands
 * to one button, making them execute concurrently. The purpose of this class is
 * to make is extremely easy to perform actions with joysticks.
 *
 * The implementation of this class currently uses
 * {@link Joystick#getRawButton(int)}, but is completely prone to change.
 *
 * To operate a bindable joystick, add binds with
 * {@link BindableJoystick#bindWhenPressed(int, edu.ATA.commands.Command) bindWhenPressed}
 * and
 * {@link BindableJoystick#bindWhilePressed(int, edu.ATA.commands.Command) bindWhilePressed}.
 * They will automatically be called whenever
 * {@link BindableJoystick#doBinds() doBinds()} is called. If {@code doBinds()}
 * is not called often enough, it could distort whenPressed bindings, because
 * input is not given about when the button state is changed.
 *
 * This class operates in a similar fashion to that of the command-based
 * program. This is meant as a superior alternative with more functionality and
 * flexibility. It has very little outer class requirements.
 *
 * @author joel
 */
public final class BindableJoystick {

    private static final String WHEN_PRESSED = "WNP", WHILE_PRESSED = "WLP";

    private final class Key {

        private final String type;
        private final int port;

        public Key(String type, int port) {
            this.type = type;
            this.port = port;
        }

        public int hashCode() {
            int hash = 5;
            hash = 79 * hash + (this.type != null ? this.type.hashCode() : 0);
            hash = 79 * hash + this.port;
            return hash;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Key) {
                Key opp = (Key) obj;
                return type.equals(opp.type) && port == opp.port;
            } else {
                return false;
            }
        }
    }
    private final Hashtable bindings = new Hashtable();
    private final Hashtable pressed = new Hashtable();
    private final Joystick joystick;

    /**
     * Constructs the joystick using composition to get input from it. It is
     * typically safe to use the given object elsewhere, but do not try to
     * change the inner workings of it.
     *
     * @param joystick joystick to get input from
     */
    public BindableJoystick(Joystick joystick) {
        if (joystick == null) {
            throw new NullPointerException();
        }
        this.joystick = joystick;
    }

    /**
     * Performs the necessary checks and commands that were assigned in binding.
     * This method should be called periodically.
     */
    public void doBinds() {
        Enumeration keys = bindings.keys();
        while (keys.hasMoreElements()) {
            Key key = (Key) keys.nextElement();

            doBind(key);
        }
    }

    /**
     * Binds a command to run whenever a button is pressed. Maps the button to
     * the command. Multiple commands are safe to assign to one button.
     *
     * @param button button port in {@link Joystick#getRawAxis(int)}
     * @param command command to run when button is pressed
     */
    public void bindWhenPressed(final int button, Command command) {
        if (command == null) {
            throw new NullPointerException();
        }
        bindButton(new Key(WHEN_PRESSED, button), command);
    }

    /**
     * Binds a command to run while a button is pressed. This means it will run
     * every time {@link BindableJoystick#doBinds()} is called (if the button is
     * pressed). Maps the button to the command. Multiple commands are safe to
     * assign to one button.
     *
     * @param button button port in {@link Joystick#getRawAxis(int)}
     * @param command command to run while button is pressed
     */
    public void bindWhilePressed(final int button, Command command) {
        if (command == null) {
            throw new NullPointerException();
        }
        bindButton(new Key(WHILE_PRESSED, button), command);
    }

    private void bindButton(Key key, Command command) {
        if (bindings.containsKey(key)) {
            Command prev = (Command) bindings.get(key);
            command = new ConcurrentCommandGroup(new Command[]{prev, command});
        }
        bind(key, command);
    }

    private void bind(final Key key, final Command command) {
        bindings.put(key, command);
        pressed.put(key, Boolean.FALSE);
    }

    private void doBind(Key key) {
        if (key.type.equals(WHEN_PRESSED)) {
            if (pressed.get(key).equals(Boolean.FALSE)) {
                pressed.put(key, Boolean.valueOf(checkAndDo(key.port, (Command) bindings.get(key))));
            } else if (!check(key.port)) {
                pressed.put(key, Boolean.FALSE);
            }
        } else {
            checkAndDo(key.port, (Command) bindings.get(key));
        }
    }

    private boolean check(int port) {
        return joystick.getRawButton(port);
    }

    private boolean checkAndDo(int port, Command command) {
        if (check(port)) {
            command.run();
            return true;
        } else {
            return false;
        }
    }
}
