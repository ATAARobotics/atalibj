package edu.ATA.bindings;

/**
 * Interface representing classes that give access to bindable buttons. This
 * means that the class has buttons assignable to binds.
 *
 * @see Bindable
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface BindableButtons {

    /**
     * Checks if the button on the port is pressed or not.
     *
     * @param port port on the joystick
     * @return if button is pressed
     */
    public boolean getPressed(int port);
}
