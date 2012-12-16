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
     * Creates the module using the {@link Jaguar} object.
     *
     * @param name name of the module
     * @param jaguar jaguar to use
     */
    public JaguarModule(String name, Jaguar jaguar) {
        super(name, jaguar);
    }

    /**
     * Creates the module using the {@link Jaguar} object.
     *
     * @param jaguar jaguar to use
     */
    public JaguarModule(Jaguar jaguar) {
        super("Jaguar Port " + jaguar.getChannel(), jaguar);
    }

    /**
     * Creates jaguar speed controller object on a port and an automatic slot.
     *
     * @see Jaguar#Jaguar(int)
     * @param name name of the module
     * @param port channel on the digital sidecar
     */
    public JaguarModule(String name, int port) {
        super(name, new Jaguar(port));
    }

    /**
     * Creates jaguar speed controller object on a port and an automatic slot.
     *
     * @see Jaguar#Jaguar(int)
     * @param port channel on the digital sidecar
     */
    public JaguarModule(int port) {
        super("Jaguar Port " + port, new Jaguar(port));
    }

    /**
     * Creates jaguar speed controller object on a port and a slot.
     *
     * @see Jaguar#Jaguar(int, int)
     * @param name name of the module
     * @param slot slot in the CRIO
     * @param port channel on the digital sidecar
     */
    public JaguarModule(String name, int slot, int port) {
        super(name, new Jaguar(slot, port));
    }

    /**
     * Creates jaguar speed controller object on a port and a slot.
     *
     * @see Jaguar#Jaguar(int, int)
     * @param slot slot in the CRIO
     * @param port channel on the digital sidecar
     */
    public JaguarModule(int slot, int port) {
        super("Jaguar Slot " + slot + " Port " + port, new Jaguar(slot, port));
    }
}