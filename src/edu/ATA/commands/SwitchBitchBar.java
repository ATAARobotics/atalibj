package edu.ATA.commands;

import edu.ATA.twolf.subsystems.BitchBar;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class SwitchBitchBar extends ThreadableCommand {

    private final BitchBar bitchBar;

    public SwitchBitchBar(BitchBar bitchBar, boolean newThread) {
        super(newThread);
        this.bitchBar = bitchBar;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                bitchBar.switchPosition();
            }
        };
    }
}
