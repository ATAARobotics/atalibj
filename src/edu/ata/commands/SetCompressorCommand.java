package edu.ata.commands;

import edu.first.module.speedcontroller.SpikeRelayModule;
import edu.wpi.first.wpilibj.Relay;

public class SetCompressorCommand extends ThreadableCommand {

    private final SpikeRelayModule compressor;
    private final Relay.Value value;

    public SetCompressorCommand(SpikeRelayModule compressor, Relay.Value value, boolean newThread) {
        super(newThread);
        this.compressor = compressor;
        this.value = value;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                compressor.set(value);
            }
        };
    }
}