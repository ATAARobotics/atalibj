package ata2014.commands;

import edu.first.command.Command;
import edu.first.module.controllers.PIDController;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Skyler
 */
public class WaitForPIDContoller implements Command {

    private final PIDController controller;
    private final double delay;

    public WaitForPIDContoller(PIDController PID) {
        this.controller = PID;
        this.delay = 0.02;
    }

    public WaitForPIDContoller(PIDController PID, double delay) {
        this.controller = PID;
        this.delay = delay;
    }

    public void run() {
        while (controller.onTarget()) {
            Timer.delay(delay);
        }
    }
}
