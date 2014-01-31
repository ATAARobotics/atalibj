package ata2014.controllers;

import edu.first.identifiers.Position;
import edu.first.module.actuators.VictorModule;
import edu.first.module.controllers.Controller;
import edu.first.module.controllers.Controller.LoopType;

/**
 * Controller to bring arms back until they hit limit switches. This ensures
 * that the arms are parallel.
 *
 * @author Skyler
 */
public final class ArmReset extends Controller {

    private final Position armSensor;
    private final VictorModule armMotor;

    public ArmReset(Position armSensor, VictorModule armMotor) {
        super(0.02, LoopType.FIXED_DELAY);
        this.armSensor = armSensor;
        this.armMotor = armMotor;
    }

    public void run() {
        if (armSensor.getPosition()) {
            armMotor.setSpeed(1);
        } else {
            armMotor.setSpeed(0);
        }
    }
}
