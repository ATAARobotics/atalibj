package edu.ata.driving.modules;

import edu.wpi.first.wpilibj.Jaguar;

/**
 * Module that represents AndyMark Jaguar speed controllers. Used to change the
 * speed of certain motors.
 *
 * @author Joel Gallant
 */
public class JaguarModule extends SpeedControllerModule {

    /**
     * Creates a jaguar and sets the object up to be enabled.
     *
     * @see Jaguar#Jaguar(int)
     * @param port port of the digital sidecar
     */
    public JaguarModule(int port) {
        super("Jaguar Port " + port, new Jaguar(port));
    }

    public void feed() {
        getJaguar().Feed();
    }

    /**
     * Returns the jaguar object. Is just a type-casted
     * {@link JaguarModule#getSpeedController()}.
     *
     * @return jaguar object underneath
     */
    public Jaguar getJaguar() {
        return (Jaguar) getSpeedController();
    }
}