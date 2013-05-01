package edu.ata.modules;

import edu.first.binding.Bindable;
import edu.first.module.joystick.BindableJoystick;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Joystick module used to represent XboxControllers, which team 4334 usually
 * uses in competition.
 *
 *
 * <p> Raw buttons:
 *
 * <pre>
 * 1: A
 * 2: B
 * 3: X
 * 4: Y
 * 5: Left Bumper
 * 6: Right Bumper
 * 7: Back
 * 8: Start
 * 9: Left Joystick
 * 10: Right Joystick
 * </pre>
 *
 * Raw axes:
 *
 * <pre>
 * 1: Left Stick X Axis
 *     Left:Negative; Right: Positive
 * 2: Left Stick Y Axis
 *     Up: Positive; Down: Negative
 * 3: Triggers
 *     Left: Negative; Right: Positive
 * 4: Right Stick X Axis
 *     Left: Negative; Right: Positive
 * 5: Right Stick Y Axis
 *     Up: Positive; Down: Negative
 * 6: Directional Pad (Not recommended, buggy)
 * 7: Right distance from the middle
 *     Up: Positive; Down: Negative
 * 8: Left distance from the middle
 *     Up: Positive; Down: Negative
 * </pre>
 *
 *
 * @author Joel Gallant
 */
public final class XboxController extends BindableJoystick {

    /**
     * The absolute smallest value for the all axises to return. If it is
     * smaller than this value, it will revert to 0.
     */
    public static final double DEADZONE = 0.16;
    public static final int A = 1, B = 2, X = 3,
            Y = 4, LEFT_BUMPER = 5, RIGHT_BUMPER = 6,
            BACK = 7, START = 8, LEFT_STICK = 9, RIGHT_STICK = 10;
    public static final int LEFT_X = 1, LEFT_Y = 2, TRIGGERS = 3, RIGHT_X = 4,
            RIGHT_Y = 5, DIRECTIONAL_PAD = 6, RIGHT_FROM_MIDDLE = 7, LEFT_FROM_MIDDLE = 8;

    /**
     * Constructs the object by using composition, using the given joystick
     * object to control methods in this class.
     *
     * @param joystick actual underlying object used
     */
    public XboxController(Joystick joystick) {
        super(joystick);
    }

    public double getRawAxis(int port) {
        double v;
        if (port == RIGHT_FROM_MIDDLE) {
            v = RightDistanceFromMiddle();
        } else if (port == LEFT_FROM_MIDDLE) {
            v = LeftDistanceFromMiddle();
        } else {
            v = super.getRawAxis(port);
        }
        if (port == LEFT_Y || port == RIGHT_Y || port == TRIGGERS) {
            v = -v;
        }
        if (Math.abs(v) > DEADZONE) {
            return v;
        } else {
            return 0;
        }
    }

    public Button getAButton() {
        return getButton(A);
    }

    public boolean A() {
        return getRawButton(A);
    }

    public Button getBButton() {
        return getButton(B);
    }

    public boolean B() {
        return getRawButton(B);
    }

    public Button getXButton() {
        return getButton(X);
    }

    public boolean X() {
        return getRawButton(X);
    }

    public Button getYButton() {
        return getButton(Y);
    }

    public boolean Y() {
        return getRawButton(Y);
    }

    public Button getLeftBumper() {
        return getButton(LEFT_BUMPER);
    }

    public boolean LeftBumper() {
        return getRawButton(LEFT_BUMPER);
    }

    public Button getRightBumper() {
        return getButton(RIGHT_BUMPER);
    }

    public boolean RightBumper() {
        return getRawButton(RIGHT_BUMPER);
    }

    public Button getBackButton() {
        return getButton(BACK);
    }

    public boolean BackButton() {
        return getRawButton(BACK);
    }

    public Button getStartButton() {
        return getButton(START);
    }

    public boolean StartButton() {
        return getRawButton(START);
    }

    public Button getLeftJoystickButton() {
        return getButton(LEFT_STICK);
    }

    public boolean LeftJoystickButton() {
        return getRawButton(LEFT_STICK);
    }

    public Button getRightJoystickButton() {
        return getButton(RIGHT_STICK);
    }

    public boolean RightJoystickButton() {
        return getRawButton(RIGHT_STICK);
    }

    public Axis getLeftX() {
        return getAxis(LEFT_X);
    }

    public double LeftX() {
        return getRawAxis(LEFT_X);
    }

    public Axis getLeftY() {
        return getAxis(LEFT_Y);
    }

    public double LeftY() {
        return getRawAxis(LEFT_Y);
    }

    public Axis getRightX() {
        return getAxis(RIGHT_X);
    }

    public double RightX() {
        return getRawAxis(RIGHT_X);
    }

    public Axis getRightY() {
        return getAxis(RIGHT_Y);
    }

    public double RightY() {
        return getRawAxis(RIGHT_Y);
    }

    public Axis getTriggers() {
        return getAxis(TRIGGERS);
    }

    public double Triggers() {
        return getRawAxis(TRIGGERS);
    }

    public Axis getDirectionalPad() {
        return getAxis(DIRECTIONAL_PAD);
    }

    public double DirectionalPad() {
        return getRawAxis(DIRECTIONAL_PAD);
    }

    public Bindable.Axis getRightDistanceFromMiddle() {
        return new Bindable.Axis("Right from middle") {
            public double getValue() {
                double distance = Math.sqrt((RightX() * RightX()) + (RightY() * RightY()));
                return (RightY() > 0) ? distance : -distance;
            }
        };
    }

    public double RightDistanceFromMiddle() {
        double distance = Math.sqrt((RightX() * RightX()) + (RightY() * RightY()));
        return (RightY() > 0) ? distance : -distance;
    }

    public Bindable.Axis getLeftDistanceFromMiddle() {
        return new Bindable.Axis("Left from middle") {
            public double getValue() {
                double distance = Math.sqrt((LeftX() * LeftX()) + (LeftY() * LeftY()));
                return (LeftY() > 0) ? distance : -distance;
            }
        };
    }

    public double LeftDistanceFromMiddle() {
        double distance = Math.sqrt((LeftX() * LeftX()) + (LeftY() * LeftY()));
        return (LeftY() > 0) ? distance : -distance;
    }
}
