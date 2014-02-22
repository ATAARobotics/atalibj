package ata2014.controllers;

import ata2014.modules.WinchMotor;
import edu.first.module.controllers.Controller;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class WinchBack extends Controller {

    private final WinchMotor motor;

    public WinchBack(WinchMotor motor) {
        super(0.02, LoopType.FIXED_RATE);
        this.motor = motor;
    }

    public void run() {
        if (motor.atLimit()) {
            motor.set(0);
            disable();
        } else {
            motor.set(1);
        }
    }
}
