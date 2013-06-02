package edu.first.module.controllers;

import edu.first.identifiers.Input;
import edu.first.identifiers.Output;

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
public class PIDController extends Controller implements Input, Output {

    private static final double defaultLoopTime = 0.02;
    private final Input input;
    private final Output output;
    private double P, I, D;
    private double minimumInput = Double.MIN_VALUE, maximumInput = Double.MAX_VALUE;
    private double minimumOutput = -1, maximumOutput = +1;
    // Controller variables
    private double setpoint = 0;
    private double totalError = 0;
    private double prevError = 0;
    private double prevResult = 0;

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
     * Constructs the controller using its input and output. Loops at a fixed
     * delay at {@code loopTime}.
     *
     * @param input object to receive input from
     * @param output object to send output to
     * @param loopTime time in seconds each loop should run
     */
    public PIDController(Input input, Output output, double loopTime) {
        super(loopTime, LoopType.FIXED_RATE);
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
     */
    public PIDController(Input input, Output output, int loopTimeHertz) {
        super(loopTimeHertz, LoopType.FIXED_RATE);
        this.input = input;
        this.output = output;
    }

    /**
     * Sets the setpoint (or goal) or the controller. The PID algorithm should
     * attempt to bring input as close as possible to this goal.
     *
     * @param setpoint desired point that the input should reach
     */
    public final void setSetpoint(double setpoint) {
        synchronized (this) {
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
        synchronized (this) {
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
        synchronized (this) {
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
        synchronized (this) {
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
        synchronized (this) {
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
        synchronized (this) {
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
        synchronized (this) {
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
        synchronized (this) {
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
        synchronized (this) {
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
        synchronized (this) {
            this.minimumOutput = minimumOutput;
            this.maximumOutput = maximumOutput;
        }
    }

    /**
     * Returns the last computed output value.
     *
     * @return last result of PID algorithm
     */
    public final double getPrevResult() {
        synchronized (this) {
            return prevResult;
        }
    }

    /**
     * Runs the PID algorithm.
     *
     * @see PIDController for PID algorithm
     */
    public final void run() {
        double in = input.get();
        double result;

        synchronized (this) {

            if (in < minimumInput) {
                in = minimumInput;
            } else if (in > maximumInput) {
                in = maximumInput;
            }

            double error = setpoint - in;

            if (I != 0) {
                double potentialIGain = (totalError + error) * I;
                if (potentialIGain < maximumOutput) {
                    if (potentialIGain > minimumOutput) {
                        totalError += error;
                    } else {
                        totalError = minimumOutput / I;
                    }
                } else {
                    totalError = maximumOutput / I;
                }
            }

            result = (P * error)
                    + (I * totalError)
                    + (D * (error - prevError));
            prevError = error;

            if (result > maximumOutput) {
                result = maximumOutput;
            } else if (result < minimumOutput) {
                result = minimumOutput;
            }

            prevResult = result;
        }

        output.set(result);
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
}
