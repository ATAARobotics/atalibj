package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.sensor.DigitalLimitSwitchModule;
import edu.first.module.speedcontroller.SpikeRelayModule;
import edu.first.module.subsystem.Subsystem;
import edu.wpi.first.wpilibj.Relay;

public final class Compressor extends Subsystem {

    private static final long delay = 50L;
    private final DigitalLimitSwitchModule PSISwitch;
    private final SpikeRelayModule relayModule;

    public Compressor(DigitalLimitSwitchModule PSISwitch, SpikeRelayModule relayModule) {
        super(new Module[]{PSISwitch, relayModule});
        this.PSISwitch = PSISwitch;
        this.relayModule = relayModule;
    }

    public void start() {
        startAtFixedDelay(delay);
    }

    public void run() {
<<<<<<< HEAD
        if(PSISwitch.isPushed()) {
=======
        if(!PSISwitch.isPushed()) {
>>>>>>> master
            relayModule.set(Relay.Value.kForward);
        } else {
            relayModule.set(Relay.Value.kOff);
        }
    }
    
    public boolean isAtPressure() {
        return !PSISwitch.isPushed();
    }
}