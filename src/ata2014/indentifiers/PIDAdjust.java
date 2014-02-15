package ata2014.indentifiers;

import edu.first.identifiers.Output;
import edu.first.module.controllers.PIDController;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class PIDAdjust implements Output {

    private final PIDController controller;

    public PIDAdjust(PIDController controller) {
        this.controller = controller;
    }

    public void set(double value) {
        controller.setSetpoint(controller.getSetpoint() + value);
    }
}
