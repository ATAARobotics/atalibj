package edu.ata.driving.modules;

import edu.wpi.first.wpilibj.Victor;

/**
 * Module that represents VEX Victor speed controllers. This applies to all
 * versions of the Victor. Used to control the speed of certain motors.
 *
 * @author Joel Gallant
 */
public class VictorModule extends SpeedControllerModule {

    /**
     * Creates the module using the victor object.
     *
     * @param name name of the module
     * @param victor victor object to use
     */
    public VictorModule(String name, Victor victor) {
        super(name, victor);
    }

    /**
     * Creates the module using the victor object.
     *
     * @param victor victor object to use
     */
    public VictorModule(Victor victor) {
        super("Victor Port " + victor.getChannel(), victor);
    }

    /**
     * Creates victor speed controller object on a port and an automatic slot.
     *
     * @see Victor#Victor(int)
     * @param name name of the module
     * @param port channel on the digital sidecar
     */
    public VictorModule(String name, int port) {
        super(name, new Victor(port));
    }

    /**
     * Creates victor speed controller object on a port and an automatic slot.
     *
     * @see Victor#Victor(int)
     * @param port channel on the digital sidecar
     */
    public VictorModule(int port) {
        super("Victor Port " + port, new Victor(port));
    }

    /**
     * Creates victor speed controller object on a port and a slot.
     *
     * @see Victor#Victor(int, int)
     * @param name name of the module
     * @param slot slot in the CRIO
     * @param port channel on the digital sidecar
     */
    public VictorModule(String name, int slot, int port) {
        super(name, new Victor(slot, port));
    }

    /**
     * Creates victor speed controller object on a port and a slot.
     *
     * @see Victor#Victor(int, int)
     * @param slot slot in the CRIO
     * @param port channel on the digital sidecar
     */
    public VictorModule(int slot, int port) {
        super("Victor Slot " + slot + " Port " + port, new Victor(slot, port));
    }
}