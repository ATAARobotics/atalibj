package edu.first.bindings;

/**
 * Interface representing classes that give access to bindable axises. This
 * means that the class has axises assignable to binds.
 *
 * @see Bindable
 * @author Joel Gallant
 */
public interface BindableAxises {

    /**
     * Returns the axis value on the specified port.
     *
     * @param port port on the joystick
     * @return value of axis on the port
     */
    public double getAxisValue(int port);
}
