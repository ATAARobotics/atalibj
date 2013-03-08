package edu.ata.commands;

import edu.first.module.sensor.EncoderModule;

/**
 * Command to reset the encoder distance count.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class ResetEncoderCommand extends ThreadableCommand {

    private final EncoderModule encoder;

    /**
     * Constructs the command using the encoder to reset.
     *
     * @param encoder encoder to reset
     * @param newThread if command should run in a new thread
     */
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
