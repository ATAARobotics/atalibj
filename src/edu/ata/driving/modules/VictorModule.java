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
     * Creates victor and sets the object up to be enabled.
     *
     * @see Victor#Victor(int)
     * @param port port on the digital sidecar
     */
    public VictorModule(int port) {
        super("Victor Port " + port, new Victor(port));
    }

    public void feed() {
        getVictor().Feed();
    }

    /**
     * Returns the victor object. Is just a type-casted
     * {@link VictorModule#getSpeedController()}.
     *
     * @return victor object underneath
     */
    public Victor getVictor() {
        return (Victor) getSpeedController();
    }
}