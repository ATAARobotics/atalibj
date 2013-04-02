package edu.ata.commands;

import edu.first.module.sensor.DigitalLimitSwitchModule;
import edu.first.module.speedcontroller.SpikeRelayModule;
import edu.wpi.first.wpilibj.Relay;

public class CompressorCommand extends ThreadableCommand {

    private final DigitalLimitSwitchModule psiSwitch;
    private final SpikeRelayModule compressor;
    private final boolean reversed;

    public CompressorCommand(DigitalLimitSwitchModule psiSwitch, SpikeRelayModule compressor, boolean reversed, boolean newThread) {
        super(newThread);
        this.psiSwitch = psiSwitch;
        this.compressor = compressor;
        this.reversed = reversed;
    }

    public CompressorCommand(DigitalLimitSwitchModule psiSwitch, SpikeRelayModule compressor, boolean newThread) {
        this(psiSwitch, compressor, false, newThread);
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if (reversed) {
                    if (!psiSwitch.isPushed()) {
                        compressor.set(Relay.Value.kForward);
                    } else {
                        compressor.set(Relay.Value.kOff);
                    }
                } else {
                    if (psiSwitch.isPushed()) {
                        compressor.set(Relay.Value.kForward);
                    } else {
                        compressor.set(Relay.Value.kOff);
                    }
                }
            }
        };
    }
}