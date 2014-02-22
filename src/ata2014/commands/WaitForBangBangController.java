package ata2014.commands;

import edu.first.command.Command;
import edu.first.module.controllers.BangBangController;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Skyler
 */
public class WaitForBangBangController implements Command {

    private final BangBangController controller;
    private final double delay;

    public WaitForBangBangController(BangBangController controller) {
        this.controller = controller;
        this.delay = 0.02;
    }

    public WaitForBangBangController(BangBangController controller, double delay) {
        this.controller = controller;
        this.delay = delay;
    }

    public void run() {
        while (controller.onTarget()) {
            Timer.delay(delay);
        }
    }
}
