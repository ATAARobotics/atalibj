package ata2014.main;

import edu.first.module.joysticks.XboxController;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Joysticks {

    XboxController joystick1 = new XboxController(1),
            joystick2 = new XboxController(2);
}
