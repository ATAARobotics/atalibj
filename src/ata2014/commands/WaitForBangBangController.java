package ata2014.commands;

import edu.first.command.Command;
import edu.first.module.controllers.BangBangController;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Skyler
 */
public class WaitForBangBangController implements Command {

    private final BangBangController Bang;
    private final double delay;

    public WaitForBangBangController(BangBangController Bang) {
        this.Bang = Bang;
        this.delay = 0.02;
    }

    public WaitForBangBangController(BangBangController Bang, double delay) {
        this.Bang = Bang;
        this.delay = delay;
    }

    public void run() {
        while (Bang.onTarget()) {
            Timer.delay(delay);
        }
    }
}
