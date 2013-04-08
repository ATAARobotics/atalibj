package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.GroupSolenoid;
import edu.first.module.actuator.Solenoid;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.subsystem.Subsystem;

public final class AlignmentSystem extends Subsystem {

    private final Solenoid back;
    private final Solenoid backLeft;
    private final Solenoid backRight;

    public AlignmentSystem(SolenoidModule back) {
        super(new Module[]{back});
        this.back = back;
        this.backLeft = new NullSolenoid();
        this.backRight = new NullSolenoid();
    }

    public AlignmentSystem(SolenoidModule backLeft, SolenoidModule backRight) {
        super(new Module[]{backLeft, backRight});
        this.back = new GroupSolenoid(new Solenoid[]{backLeft, backRight});
        this.backLeft = backLeft;
        this.backRight = backRight;
    }

    public void start() {
        // No thread needed
    }

    public void run() {
        // No thread needed
    }

    public void setOut() {
        back.set(true);
    }

    public void setIn() {
        back.set(false);
    }

    public void setLeft() {
        backLeft.set(true);
        backRight.set(false);
    }

    public void setRight() {
        backLeft.set(false);
        backRight.set(true);
    }
    
    public boolean isOut() {
        return back.get();
    }
    
    public boolean isLeft() {
        return backLeft.get();
    }
    
    public boolean isRight() {
        return backRight.get();
    }

    private static class NullSolenoid implements Solenoid {

        public void set(boolean on) {
        }

        public boolean get() {
            return false;
        }
    }
}