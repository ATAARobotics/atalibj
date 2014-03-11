package ata2014.controllers;

import ata2014.modules.WinchMotor;
import edu.first.module.controllers.Controller;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class WinchBack extends Controller {

    private final WinchMotor motor;
    private double start;

    public WinchBack(WinchMotor motor) {
        super(0.02, LoopType.FIXED_RATE);
        this.motor = motor;
    }

    protected void enableModule() {
        super.enableModule();
        start = System.currentTimeMillis();
    }

    public void run() {
        // 10 second safety limit
        if (motor.atLimit() || System.currentTimeMillis() - start > 10000) {
            motor.set(0);
            disable();
        } else {
            motor.set(1);
        }
    }
}
