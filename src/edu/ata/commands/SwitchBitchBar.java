package edu.ata.commands;

import edu.ata.subsystems.ReversingSolenoids;

/**
 * Command to switch the bitch bar's position.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class SwitchBitchBar extends ThreadableCommand {

    public static final int IN = 1, OUT = 2, SWITCH = 3;
    private final ReversingSolenoids bitchBar;
    private final int type;

    public SwitchBitchBar(ReversingSolenoids bitchBar, int type, boolean newThread) {
        super(newThread);
        this.bitchBar = bitchBar;
        this.type = type;
    }
    
    public SwitchBitchBar(ReversingSolenoids bitchBar, boolean newThread) {
        this(bitchBar, SWITCH, false);
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if(type == IN) {
                    bitchBar.setIn();
                } else if(type == OUT) {
                    bitchBar.setOut();
                } else {
                    bitchBar.switchPosition();
                }
            }
        };
    }
}
