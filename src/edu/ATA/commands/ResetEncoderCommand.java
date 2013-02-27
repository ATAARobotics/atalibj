package edu.ATA.commands;

import edu.first.module.sensor.EncoderModule;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class ResetEncoderCommand extends ThreadableCommand {

    private final EncoderModule encoder;

    public ResetEncoderCommand(EncoderModule encoder, boolean newThread) {
        super(newThread);
        this.encoder = encoder;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                encoder.reset();
            }
        };
    }
}
