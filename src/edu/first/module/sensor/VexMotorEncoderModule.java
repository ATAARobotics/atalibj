package edu.first.module.sensor;

import edu.first.identifiers.ReturnableNumber;
import edu.first.module.Module;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * Vex encoder for Vex motors.
 *
 * @author Joel Gallant
 */
public class VexMotorEncoderModule extends ForwardingVexMotorEncoder implements Module.DisableableModule {

    private boolean enabled;

    /**
     * Constructs encoder module.
     *
     * @param encoder underlying object
     */
    public VexMotorEncoderModule(VexIntegratedMotorEncoder encoder) {
        super(encoder);
    }

    public final boolean disable() {
        return !(enabled = false);
    }

    public final boolean enable() {
        reset();
        return (enabled = true);
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public final double get() {
        return isEnabled() ? super.get() : 0;
    }

    /**
     * Returns the value if the module is enabled.
     *
     * @return raw value of encoder (position)
     */
    public final double getRaw() {
        return isEnabled() ? super.getRaw() : 0;
    }

    /**
     * Returns the revolutions if the module is enabled.
     *
     * @return revolutions of encoder
     */
    public double getRevs() {
        return isEnabled() ? super.getRevs() : 0;
    }
}

class ForwardingVexMotorEncoder implements PIDSource, ReturnableNumber {

    private final VexIntegratedMotorEncoder encoder;

    ForwardingVexMotorEncoder(VexIntegratedMotorEncoder encoder) {
        this.encoder = encoder;
    }

    public final void reset() {
        encoder.reset();
    }

    public double get() {
        return encoder.get();
    }

    public double getRaw() {
        return encoder.getRaw();
    }

    public double getRevs() {
        return encoder.getRevs();
    }

    public final void setAddress(byte address) {
        encoder.setAddress(address);
    }

    public final void setTerminated() {
        encoder.setTerminated();
    }

    public final void unsetTerminated() {
        encoder.unSetTerminated();
    }

    public final void setTickPerRev(String gearing) {
        encoder.setTicksPerRev(gearing);
    }

    public double pidGet() {
        return getRevs();
    }
}