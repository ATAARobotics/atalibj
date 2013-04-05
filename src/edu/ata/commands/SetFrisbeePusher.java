package edu.ata.commands;

import edu.ata.subsystems.Shooter;
import edu.first.bindings.AxisBind;

public class SetFrisbeePusher implements AxisBind {

    private final Shooter shooter;

    public SetFrisbeePusher(Shooter shooter) {
        this.shooter = shooter;
    }

    public void set(double axisValue) {
        shooter.setFrisbeePusher(axisValue);
    }
}