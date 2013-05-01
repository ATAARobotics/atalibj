package edu.first.bindings;

/**
 * Interface representing binds with joystick buttons and axises. Has no actual
 * required functionality, and acts like {@code Serializable} in Java SE - to
 * identify binds as such. Binds need to be explicitly declared as binds, which
 * is why this interface exists. To be a bind, a class should be something that
 * a {@link Bindable} can use to bind to something (button, axis, etc.).
 *
 * @author Joel Gallant
 */
public interface Bind {
}
