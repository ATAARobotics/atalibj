package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.GroupSolenoid;
import edu.first.module.actuator.Solenoid;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.subsystem.Subsystem;

/**
 * Pneumatic alignment system to aim shots. Contains two pistons, and can be
 * used in multiple configurations depending on what is most efficient for the
 * purpose.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class AlignmentSystem extends Subsystem {

    private final Solenoid leftIn, leftOut, rightIn, rightOut, in, out;

    /**
     * Constructs the alignment system with the solenoids used to control the
     * pistons.
     *
     * @param leftIn piston on left to push in
     * @param leftOut piston on left to push out
     * @param rightIn piston on right to push in
     * @param rightOut piston on right to push out
     */
    public AlignmentSystem(SolenoidModule leftIn, SolenoidModule leftOut,
            SolenoidModule rightIn, SolenoidModule rightOut) {
        super(new Module[]{leftIn, leftOut, rightIn, rightOut});
        this.leftIn = leftIn;
        this.leftOut = leftOut;
        this.rightIn = rightIn;
        this.rightOut = rightOut;
        this.in = new GroupSolenoid(new Solenoid[]{leftIn, rightIn});
        this.out = new GroupSolenoid(new Solenoid[]{leftOut, rightOut});
        setIn();
    }

    /**
     * Constructs the alignment system with the solenoids used to control the
     * pistons.
     *
     * @param left left side piston
     * @param right right side piston
     */
    public AlignmentSystem(SolenoidModule left, SolenoidModule right) {
        super(new Module[]{left, right});
        this.leftIn = new NullSolenoid();
        this.leftOut = left;
        this.rightIn = new NullSolenoid();
        this.rightOut = right;
        this.in = new NullSolenoid();
        this.out = new GroupSolenoid(new Solenoid[]{left, right});
        setIn();
    }

    /**
     * Constructs the alignment system with the solenoids used to control the
     * pistons.
     *
     * @param out piston to push out
     */
    public AlignmentSystem(SolenoidModule out) {
        super(new Module[]{out});
        this.leftIn = new NullSolenoid();
        this.leftOut = new NullSolenoid();
        this.rightIn = new NullSolenoid();
        this.rightOut = new NullSolenoid();
        this.in = new NullSolenoid();
        this.out = out;
        setIn();
    }

    /**
     * Sets system to the right side. Does nothing if right solenoid was not
     * set.
     */
    public void setRight() {
        leftOut.set(false);
        rightIn.set(false);
        leftIn.set(true);
        rightOut.set(true);
    }

    /**
     * Sets the system to the left side. Does nothing if left solenoid was not
     * set.
     */
    public void setLeft() {
        rightOut.set(false);
        leftIn.set(false);
        rightIn.set(true);
        leftOut.set(true);
    }

    /**
     * Sets all pistons out.
     */
    public void setOut() {
        out.set(true);
        in.set(false);
    }

    /**
     * Sets all pistons in.
     */
    public void setIn() {
        out.set(false);
        in.set(true);
    }

    public boolean isOut() {
        try {
            // out is always non-null
            return out.get();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isRight() {
        return rightOut.get();
    }

    private final class NullSolenoid implements Solenoid {

        public void set(boolean on) {
        }

        public boolean get() {
            return false;
        }
    }
}