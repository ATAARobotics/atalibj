package edu.ata.binds;

import edu.ata.subsystems.Winch;
import edu.first.bindings.AxisBind;

public final class SetWinchSpeed implements AxisBind {

    private final Winch winch;

    public SetWinchSpeed(Winch winch) {
        this.winch = winch;
    }

    public void set(double axisValue) {
        winch.move(axisValue);
    }
}
