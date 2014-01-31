/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ata2014.controllers;

import edu.first.module.actuators.VictorModule;
import edu.first.module.sensors.DigitalInput;

/**
 *
 * @author Skyler
 */
public final class ArmReset extends edu.first.module.controllers.Controller {

    private final DigitalInput armSensor;
    private final VictorModule armMotor;

    public ArmReset(DigitalInput armSensor, VictorModule armMotor) {
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
