package edu.ata.commands;

import edu.ata.subsystems.ReversingSolenoids;
import edu.first.module.actuator.SolenoidModule;

public class ReversingSolenoidsCommand extends ThreadableCommand {

    public static final SwitchType IN = new SwitchType(SwitchType.IN),
            OUT = new SwitchType(SwitchType.OUT),
            SWITCH = new SwitchType(SwitchType.SWITCH);
    private final ReversingSolenoids solenoids;
    private final SwitchType type;

    public ReversingSolenoidsCommand(SolenoidModule in, SolenoidModule out, SwitchType type, boolean newThread) {
        super(newThread);
        this.solenoids = new ReversingSolenoids(in, out);
        this.type = type;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                solenoids.enable();
                if (type.type == IN.type) {
                    solenoids.setIn();
                } else if (type.type == OUT.type) {
                    solenoids.setOut();
                } else if (type.type == SWITCH.type) {
                    solenoids.switchPosition();
                }
            }
        };
    }

    public final static class SwitchType {

        private static final int IN = 1, OUT = 2, SWITCH = 3;
        private final int type;

        public SwitchType(int type) {
            this.type = type;
        }
    }
}