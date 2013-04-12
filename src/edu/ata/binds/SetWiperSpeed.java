package edu.ata.binds;

import edu.ata.subsystems.WindshieldWiper;
import edu.first.bindings.AxisBind;

public final class SetWiperSpeed implements AxisBind {

    private final WindshieldWiper windshieldWiper;

    public SetWiperSpeed(WindshieldWiper windshieldWiper) {
        this.windshieldWiper = windshieldWiper;
    }

    public void set(double axisValue) {
        windshieldWiper.setSpeed(axisValue);
    }
}
