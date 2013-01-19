package edu.ATA.module.sensor;

import edu.ATA.module.Module;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 * Module that represents potentiometer sensors. Potentiometers use variable
 * resistance to measure the position of something. They are analog sensors.
 * When enabled, {@link PotentiometerModule#getPosition()} will return values
 * from 0 to 10, and when it is disabled it will return -1.
 *
 * @author Team 4334
 */
public final class PotentiometerModule extends ForwardingPotentiometer implements Module.DisableableModule {
    
    private boolean enabled;

    /**
     * Constructs the object by using composition, using the given analog
     * channel object to control methods in this class.
     *
     * @param controller actual underlying object used
     */
    public PotentiometerModule(AnalogChannel potentiometer) {
        super(potentiometer);
    }

    /**
     * Disables the module, causing the
     * {@link PotentiometerModule#getPosition()} method to always return -1.
     *
     * @return whether the module was successfully disabled
     */
    public boolean disable() {
        return !(enabled = false);
    }

    /**
     * Enables the module, letting it return proper values in
     * {@link PotentiometerModule#getPosition()}.
     *
     * @return whether the module was successfully enabled
     */
    public boolean enable() {
        return (enabled = true);
    }

    /**
     * Returns whether the module is currently enabled. If it is not enabled,
     * {@link PotentiometerModule#getPosition()} will always return -1.
     *
     * @return whether module is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns a value between 0 and 10 when the module is enabled. If it is not
     * enabled, it will return -1, to make it obvious that the module has not
     * been enabled. Returns the position of the potentiometer depending on the
     * voltage returned. Different positions will change the resistance, making
     * them give different voltages. Returns a value from 0 to 10.
     *
     * @return value of the potentiometer
     */
    public double getPosition() {
        return isEnabled() ? super.getPosition() : -1;
    }
}

/**
 * Forwarding class, as described in Effective Java: Second Edition, Item 16.
 * Forwards {@link edu.wpi.first.wpilibj.AnalogChannel}.
 *
 * @author Joel Gallant
 */
class ForwardingPotentiometer implements Potentiometer {
    
    private final AnalogChannel potentiometer;

    /**
     * Constructs the object by using composition, using the given analog
     * channel object to control methods in this class.
     *
     * @param controller actual underlying object used
     */
    ForwardingPotentiometer(AnalogChannel potentiometer) {
        if(potentiometer == null) {
            throw new NullPointerException();
        }
        this.potentiometer = potentiometer;
    }

    /**
     * Returns the instance of the underlying
     * {@link edu.wpi.first.wpilibj.AnalogChannel}.
     *
     * @return composition object under this one
     */
    protected AnalogChannel getPotentiometer() {
        return potentiometer;
    }

    /**
     * Returns the position of the potentiometer depending on the voltage
     * returned. Different positions will change the resistance, making them
     * give different voltages. Returns a value from 0 to 10.
     *
     * @return position of the potentiometer from 0 to 10
     */
    public double getPosition() {
        // Potentiometer gives 0-5
        return potentiometer.getVoltage() * 2;
    }
}
