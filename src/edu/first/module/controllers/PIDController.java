package edu.first.module.controllers;

import edu.first.identifiers.Input;
import edu.first.identifiers.Output;
import edu.first.identifiers.PositionalActuator;
import edu.first.identifiers.PositionalSensor;
import edu.first.util.MathUtils;

/**
 * Controller that uses the PID algorithm to handle input and output. This class
 * is thread safe and settings can be changed while it is running.
 *
 * <p> To understand how PID works, visit:
 * <p>
 * <a href="http://youtu.be/UR0hOmjaHp0">PID Control - A brief introduction</a>
 * <p>
 * <a href="http://en.wikipedia.org/wiki/PID_controller">PID Controller</a>
 *
 * <p> This PID algorithm runs like this:
 * <pre>   P * error
 * + I * totalError
 * + D * (error - prevError)
 * </pre>
 *
 * @since June 02 13
 * @author Joel Gallant
 */
public class PIDController extends Controller implements PositionalSensor, PositionalActuator {

    private static final double defaultLoopTime = 0.02;
    private final Input input;
    private final Output output;
    private double P, I, D;
    private double minimumInput = Double.MIN_VALUE, maximumInput = Double.MAX_VALUE;
    private double minimumOutput = -1, maximumOutput = +1;
    // Controller variables
    // uses lock so user can't lock controller accidentally using "this"
    private final Object lock = new Object();
    private double setpoint = 0;
    private double totalError = 0;
    private double prevError = 0;
    private double prevResult = 0;
    private double tolerance = 0;

    /**
     * Constructs the controller using its input and output. Uses the default
     * loop time.
     *
     * @param input object to receive input from
     * @param output object to send output to
     */
    public PIDController(Input input, Output output) {
        this(input, output, defaultLoopTime);
    }

    /**
     * Constructs the controller using its input and output. Uses the default
     * loop time.
     *
     * @param input object to receive input from
     * @param output object to send output to
     * @param P proportional coefficient
     * @param I integral coefficient
     * @param D derivative coefficient
     */
    public PIDController(Input input, Output output, double P, double I, double D) {
        this(input, output);
        this.P = P;
        this.I = I;
        this.D = D;
    }

    /**
     * Constructs the controller using its input and output. Loops at a fixed
     * delay at {@code loopTime}.
     *
     * @param input object to receive input from
     * @param output object to send output to
     * @param loopTime time in seconds each loop should run
     */
    public PIDController(Input input, Output output, double loopTime) {
        super(loopTime, LoopType.FIXED_RATE);
        if (input == null) {
            throw new NullPointerException("Null input given");
        } else if (output == null) {
            throw new NullPointerException("Null output given");
        }
        this.input = input;
        this.output = output;
    }

    /**
     * Constructs the controller using its input and output. Loops at a fixed
     * delay at {@code loopTime}.
     *
     * @param input object to receive input from
     * @param output object to send output to
     * @param loopTime time in seconds each loop should run
     * @param P proportional coefficient
     * @param I integral coefficient
     * @param D derivative coefficient
     */
    public PIDController(Input input, Output output, double loopTime, double P, double I, double D) {
        this(input, output, loopTime);
        this.P = P;
        this.I = I;
        this.D = D;
    }

    /**
     * Constructs the controller using its input and output. Loops at a fixed
     * delay at {@code loopTime}.
     *
     * @param input object to receive input from
     * @param output object to send output to
     * @param loopTimeHertz the hertz value that represents how fast execution
     * will happen
     */
    public PIDController(Input input, Output output, int loopTimeHertz) {
        super(loopTimeHertz, LoopType.FIXED_RATE);
        if (input == null) {
            throw new NullPointerException("Null input given");
        } else if (output == null) {
            throw new NullPointerException("Null output given");
        }
        this.input = input;
        this.output = output;
    }

    /**
     * Constructs the controller using its input and output. Loops at a fixed
     * delay at {@code loopTime}.
     *
     * @param input object to receive input from
     * @param output object to send output to
     * @param loopTimeHertz the hertz value that represents how fast execution
     * will happen
     * @param P proportional coefficient
     * @param I integral coefficient
     * @param D derivative coefficient
     */
    public PIDController(Input input, Output output, int loopTimeHertz, double P, double I, double D) {
        this(input, output, loopTimeHertz);
        this.P = P;
        this.I = I;
        this.D = D;
    }

    /**
     * Sets the setpoint (or goal) or the controller. The PID algorithm should
     * attempt to bring input as close as possible to this goal.
     *
     * @param setpoint desired point that the input should reach
     */
    public final void setSetpoint(double setpoint) {
        synchronized (lock) {
            if (setpoint < minimumInput) {
                this.setpoint = minimumInput;
            } else if (setpoint > maximumInput) {
                this.setpoint = maximumInput;
            } else {
                this.setpoint = setpoint;
            }
        }
    }

    /**
     * Sets the Proportional coefficient in the PID algorithm.
     *
     * @param P proportional coefficient
     * @see PIDController for PID algorithm
     */
    public final void setP(double P) {
        synchronized (lock) {
            this.P = P;
        }
    }

    /**
     * Sets the Integral coefficient in the PID algorithm.
     *
     * @param I integral coefficient
     * @see PIDController for PID algorithm
     */
    public final void setI(double I) {
        synchronized (lock) {
            this.I = I;
        }
    }

    /**
     * Sets the Derivative coefficient in the PID algorithm.
     *
     * @param D derivative coefficient
     * @see PIDController for PID algorithm
     */
    public final void setD(double D) {
        synchronized (lock) {
            this.D = D;
        }
    }

    /**
     * Sets the Proportional, Integral and Derivative coefficients in the PID
     * algorithm.
     *
     * @param P proportional coefficient
     * @param I integral coefficient
     * @param D derivative coefficient
     * @see PIDController for PID algorithm
     */
    public final void setPID(double P, double I, double D) {
        synchronized (lock) {
            this.P = P;
            this.I = I;
            this.D = D;
        }
    }

    /**
     * Returns the Proportional coefficient in the PID algorithm.
     *
     * @return P proportional coefficient
     * @see PIDController for PID algorithm
     */
    public final double getP() {
        synchronized (lock) {
            return P;
        }
    }

    /**
     * Returns the Integral coefficient in the PID algorithm.
     *
     * @return I integral coefficient
     * @see PIDController for PID algorithm
     */
    public final double getI() {
        synchronized (lock) {
            return I;
        }
    }

    /**
     * Returns the Derivative coefficient in the PID algorithm.
     *
     * @return D derivative coefficient
     * @see PIDController for PID algorithm
     */
    public final double getD() {
        synchronized (lock) {
            return D;
        }
    }

    /**
     * Sets the range of values that are acceptable for input and setpoints.
     *
     * @param minimumInput lowest possible input
     * @param maximumInput highest possible input
     */
    public final void setInputRange(double minimumInput, double maximumInput) {
        if (minimumInput > maximumInput) {
            throw new IllegalArgumentException(minimumInput + " is larger than " + maximumInput);
        }
        synchronized (lock) {
            this.minimumInput = minimumInput;
            this.maximumInput = maximumInput;
        }
    }

    /**
     * Sets the range of values that are acceptable for output. By default,
     * these values are -1 and +1.
     *
     * @param minimumOutput lowest possible output
     * @param maximumOutput highest possible output
     */
    public final void setOutputRange(double minimumOutput, double maximumOutput) {
        if (minimumOutput > maximumOutput) {
            throw new IllegalArgumentException(minimumOutput + " is larger than " + maximumOutput);
        }
        synchronized (lock) {
            this.minimumOutput = minimumOutput;
            this.maximumOutput = maximumOutput;
        }
    }

    /**
     * Sets the tolerance of {@link #onTarget()}. If the error is less than the
     * tolerance, {@code onTarget()} returns true.
     *
     * @param tolerance how far off input can be to be considered "on target"
     */
    public final void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    /**
     * Returns the last computed output value.
     *
     * @return last result of PID algorithm
     */
    public final double getPrevResult() {
        synchronized (lock) {
            return prevResult;
        }
    }

    /**
     * Returns the currently set setpoint that the controller is aimed towards.
     *
     * @return current goal
     */
    public final double getSetpoint() {
        synchronized (lock) {
            return setpoint;
        }
    }

    /**
     * Returns the difference between the last computed setpoint and the input.
     *
     * @return how far off input is from setpoint
     */
    public final double getError() {
        synchronized (lock) {
            return prevError;
        }
    }

    /**
     * Returns the currently set tolerance for {@link #onTarget()}. If the error
     * is less than the tolerance, {@code onTarget()} returns true.
     *
     * @return how far off input can be to be considered "on target"
     */
    public final double getTolerance() {
        return tolerance;
    }

    /**
     * Returns whether the {@link #getError() error} is below the current
     * {@link #getTolerance() tolerance}.
     *
     * @return if controller is close enough to target
     */
    public final boolean onTarget() {
        return MathUtils.abs(getError()) < tolerance;
    }

    /**
     * Runs the PID algorithm.
     *
     * @see PIDController for PID algorithm
     */
    public final void run() {
        double in = input.get();
        double result;
        double prevErr;

        double sMinInput;
        double sMaxInput;
        double sSetpoint;
        double sP;
        double sI;
        double sD;
        double sTotalError;
        double sMaxOutput;
        double sMinOutput;

        // Snapshot values to reduce time spent in synchronized blocks
        synchronized (lock) {
            sMinInput = this.minimumInput;
            sMaxInput = this.maximumInput;
            sSetpoint = this.setpoint;
            sP = this.P;
            sI = this.I;
            sD = this.D;
            sTotalError = this.totalError;
            sMaxOutput = this.maximumOutput;
            sMinOutput = this.minimumOutput;
            prevErr = this.prevError;
        }

        if (in < sMinInput) {
            in = sMinInput;
        } else if (in > sMaxInput) {
            in = sMaxInput;
        }

        double error = sSetpoint - in;

        if (sI != 0) {
            double potentialIGain = (sTotalError + error) * sI;
            if (potentialIGain < sMaxOutput) {
                if (potentialIGain > sMinOutput) {
                    sTotalError += error;
                } else {
                    sTotalError = sMinOutput / sI;
                }
            } else {
                sTotalError = sMaxOutput / sI;
            }
        }

        result = (sP * error)
                + (sI * sTotalError)
                + (sD * (error - prevErr));

        if (result > sMaxOutput) {
            result = sMaxOutput;
        } else if (result < sMinOutput) {
            result = sMinOutput;
        }

        output.set(result);

        synchronized (lock) {
            prevError = error;
            prevResult = result;
        }
    }

    /**
     * Returns the last computed output value.
     *
     * @return last result of PID algorithm
     * @see #getPrevResult()
     */
    public final double get() {
        return getPrevResult();
    }

    /**
     * Sets the setpoint (or goal) or the controller. The PID algorithm should
     * attempt to bring input as close as possible to this goal.
     *
     * @param value desired point that the input should reach
     * @see #setSetpoint(double)
     */
    public final void set(double value) {
        setSetpoint(value);
    }

    /**
     * Returns the last computed output value.
     *
     * @return last result of PID algorithm
     * @see #getPrevResult()
     */
    public final double getPosition() {
        return getPrevResult();
    }

    /**
     * Sets the setpoint (or goal) or the controller. The PID algorithm should
     * attempt to bring input as close as possible to this goal.
     *
     * @param position desired point that the input should reach
     * @see #setSetpoint(double)
     */
    public final void setPosition(double position) {
        setSetpoint(position);
    }
}
