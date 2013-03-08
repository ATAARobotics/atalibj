package edu.ata.commands;

import edu.ata.subsystems.ReversingSolenoids;

/**
 * Command to switch the bitch bar's position.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class SwitchBitchBar extends ThreadableCommand {

    private final ReversingSolenoids bitchBar;

    /**
     * Constructs the command using the bitch bar.
     *
     * @param bitchBar system to switch the bitch bar with
     * @param newThread if command should run in a new thread
     */
    public SwitchBitchBar(ReversingSolenoids bitchBar, boolean newThread) {
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
