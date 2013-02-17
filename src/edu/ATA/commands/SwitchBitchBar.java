package edu.ATA.commands;

import edu.ATA.twolf.subsystems.BitchBar;
import edu.first.command.Command;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class SwitchBitchBar implements Command {

    private final BitchBar bitchBar;

    public SwitchBitchBar(BitchBar bitchBar) {
        this.bitchBar = bitchBar;
    }

    public void run() {
        bitchBar.switchPosition();
    }
}
