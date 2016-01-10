package edu.first.module.controllers;

import edu.first.identifiers.Input;
import edu.first.identifiers.Output;
import edu.first.identifiers.RateActuator;
import edu.first.identifiers.RateSensor;

/**
 * Controller that uses an on-off strategy to achieve a desired input.
 *
 * <ul>
 * <li> Simpler and more robust than PID (for this application); requires no
 * tuning; provides fastest spin-up and recovery time
 * <li> Works with Victor (and Talon) motor controller. Jag should be avoided
 * for this application
 * <li> Motor Controller should be jumpered for coast mode (not brake mode)
 * <li> A wheel speed sensor is required. You can use any device which provides
 * a usable speed signal
 * <li> This method requires that the wheel has sufficient moment of inertia
 * (many shooter wheels do)
 * </ul>
 *
 * <p>
 * Speed Sensor and Decoding
 * <ul>
 * <li> Use an encoder or a one-per-rev home-brew optical or magnetic sensor
 * <li> If using an encoder, connect only one channel of the encoder to the
 * Digital Sidecar. Configure FPGA to read one channel only (no quadrature)
 * <li> Configure the FPGA to read rising edges only
 * <li> Let the FPGA compute the period in hardware with its 153KHz polling rate
 * and 1MHz clock
 * <li> Do a quick calculation to determine how many samples you should
 * configure the FPGA to use for its sample ring buffer, or experiment to find
 * the value which gives the best tradeoff between noise and phase lag (whatever
 * gives you fast and stable operation at your setpoints)
 * </ul>
 *
 * <b>Note:</b>
 * The Jag overcurrent protection may activate on initial startup due to high
 * command at low rpm. This can be worked around by adding a little additional
 * code and doing some tuning, but for this application it's more convenient and
 * less fuss to use a motor controller that doesn't have this limitation. If you
 * plan to use a Jag, use {@link #setSpeedUp(boolean)}.
 *
 * <p>
 * For more information, see:
 * <p>
 * <a href="http://en.wikipedia.org/wiki/Bang%E2%80%93bang_control">Bang-Bang
 * Control</a>
 * <p>
 * <a href="http://www.chiefdelphi.com/media/papers/2663">Shooter Wheel Speed
 * Control</a>
 *
 * @since June 02 13
 * @author Joel Gallant
 */
public class BangBangController extends Controller implements RateSensor, RateActuator {

    private static final double defaultLoopTime = 0.02;
    private final Input input;
    private final Output output;
    // Controller variables
    // uses lock so user can't lock controller accidentally using "this"
    private final Object lock = new Object();
    private boolean coast;
    private boolean speedUp;
    private boolean reversed = false;
    private double setpoint = 0;
    private double maxOutput = 1;
    private double spinupInput = 0;
    private double spinupOutput = 1;
    private double tolerance = 0;
    private double prevResult;
    private double prevInput;

    /**
     * Constructs the controller using its input and output. Uses the default
     * loop time.
     *
     * @param input object to receive input from
     * @param output object to send output to
     */
    public BangBangController(Input input, Output output) {
        this(input, output, defaultLoopTime);
    }

    /**
     * Constructs the controller using its input and output. Loops at a fixed
     * delay at {@code loopTime}.
     *
     * @throws NullPointerException when input or output are null
     * @param input object to receive input from
     * @param output object to send output to
     * @param loopTime time in seconds each loop should run
     */
    public BangBangController(Input input, Output output, double loopTime) {
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
     * @throws NullPointerException when input or output are null
     * @param input object to receive input from
     * @param output object to send output to
     * @param loopTimeHertz the hertz value that represents how fast execution
     * will happen
     */
    public BangBangController(Input input, Output output, int loopTimeHertz) {
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
     * Sets the desired input that is to be achieved through this controller.
     *
     * @param setpoint desired point that the input should reach
     */
    public void setSetpoint(double setpoint) {
        synchronized (lock) {
            this.setpoint = setpoint;
        }
    }

    /**
     * Sets whether the controller should turn off the motor. As long as the
     * speed controller is on "coast" mode, sending {@code true} to this method
     * will coast the motor. Turning coast off will return to the normal state
     * of the controller.
     *
     * @param coast if motor should coast
     */
    public void setCoast(boolean coast) {
        synchronized (lock) {
            this.coast = coast;
        }
    }

    /**
     * Sets whether the controller should be in "speed up" mode. Speed up mode
     * means that the controller will set the output to
     * {@link #getSpinupOutput()} while the input is lower than
     * {@link #getSpinupInput()}.
     *
     * <p>
     * This is usually only useful if there is overcurrent protection on the
     * controller that will not allow it to go at fast speeds quickly.
     *
     * @param speedUp if controller should be in "speed up" mode
     */
    public void setSpeedUp(boolean speedUp) {
        synchronized (lock) {
            this.speedUp = speedUp;
        }
    }

    /**
     * If the controller is in "speed up" mode, sets the maximum input that
     * {@link #getSpinupOutput()} will be needed for output. While the input is
     * lower than {@code spinupInput}, the controller will set the output to
     * {@link #getSpinupOutput()}.
     *
     * @param spinupInput maximum input to use {@link #getSpinupOutput()} for
     */
    public void setSpinupInput(double spinupInput) {
        synchronized (lock) {
            this.spinupInput = spinupInput;
        }
    }

    /**
     * If the controller is in "speed up" mode, sets the output to use whenever
     * the input is below {@link #getSpinupInput()}. While the input is lower
     * than {@link #getSpinupInput()}, the controller will set the output to
     * {@code spinupOutput}.
     *
     * @param spinupOutput output for when input is lower than
     * {@link #getSpinupInput()}
     */
    public void setSpinupOutput(double spinupOutput) {
        synchronized (lock) {
            this.spinupOutput = spinupOutput;
        }
    }

    /**
     * Sets whether the output should be reversed. If output is reversed, it
     * will be negative values.
     *
     * @param reversed if controller should reverse output
     */
    public void setReversed(boolean reversed) {
        synchronized (lock) {
            this.reversed = reversed;
        }
    }

    /**
     * Sets the absolute maximum output of the controller.
     *
     * <p>
     * If you want to reverse output, use {@link #setReversed(boolean)}.
     *
     * @param maxOutput maximum output to send
     */
    public void setMaxOutput(double maxOutput) {
        synchronized (lock) {
            this.maxOutput = Math.abs(maxOutput);
        }
    }

    /**
     * Sets the tolerance of {@link #onTarget()}. If the error is less than the
     * tolerance, {@code onTarget()} returns true.
     *
     * @param tolerance how far off input can be to be considered "on target"
     */
    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    /**
     * Returns whether the controller is in "speed up" mode. Speed up mode means
     * that the controller will set the output to {@link #getSpinupOutput()}
     * while the input is lower than {@link #getSpinupInput()}.
     *
     * @return if controller is in "speed up" mode
     */
    public boolean isSpeedUp() {
        synchronized (lock) {
            return speedUp;
        }
    }

    /**
     * Returns the maximum input that {@link #getSpinupOutput()} will be needed
     * for output. While the input is lower than this, the controller will set
     * the output to {@link #getSpinupOutput()}.
     *
     * <p>
     * <i> This value will only have effect in
     * {@link #isSpeedUp() "speed up" mode}. </i>
     *
     * @return maximum input that will be considered "spinning up"
     */
    public double getSpinupInput() {
        synchronized (lock) {
            return spinupInput;
        }
    }

    /**
     * Returns the output to use whenever the input is below
     * {@link #getSpinupInput()}. While the input is lower than
     * {@link #getSpinupInput()}, the controller will set the output to this.
     *
     * <p>
     * <i> This value will only have effect in
     * {@link #isSpeedUp() "speed up" mode}. </i>
     *
     * @return output to use when controller is "spinning up"
     */
    public double getSpinupOutput() {
        synchronized (lock) {
            return spinupOutput;
        }
    }

    /**
     * Returns the absolute maximum output of the controller. Output should
     * never be higher than this number.
     *
     * @return highest possible output
     */
    public double getMaxOutput() {
        synchronized (lock) {
            return maxOutput;
        }
    }

    /**
     * Returns the currently set setpoint that the controller is aimed towards.
     *
     * @return current goal
     */
    public double getSetpoint() {
        synchronized (lock) {
            return setpoint;
        }
    }

    /**
     * Returns the current difference between the setpoint and the previous
     * input.
     *
     * @return how far off input is from setpoint
     */
    public double getError() {
        synchronized (lock) {
            return setpoint - prevInput;
        }
    }

    /**
     * Returns the previous output sent from the controller.
     *
     * @return last set output
     */
    public double getPrevResult() {
        synchronized (lock) {
            return prevResult;
        }
    }

    /**
     * Returns the previous input from the controller.
     *
     * @return last input received
     */
    public double getPrevInput() {
        synchronized (lock) {
            return prevInput;
        }
    }

    /**
     * Returns the currently set tolerance for {@link #onTarget()}. If the error
     * is less than the tolerance, {@code onTarget()} returns true.
     *
     * @return how far off input can be to be considered "on target"
     */
    public double getTolerance() {
        return tolerance;
    }

    /**
     * Returns whether the {@link #getError() error} is below the current
     * {@link #getTolerance() tolerance}.
     *
     * @return if controller is close enough to target
     */
    public boolean onTarget() {
        return Math.abs(getError()) < tolerance;
    }

    /**
     * Runs the bang-bang algorithm.
     */
    @Override
    public final void run() {
        double in = input.get();
        double result;

        boolean sCoast;
        boolean sSpeedUp;
        boolean sReversed;
        double sSetpoint;
        double sSpinupInput;
        double sSpinupOutput;
        double sMaxOutput;

        // Snapshot values to reduce time spent in synchronized blocks
        synchronized (lock) {
            sCoast = this.coast;
            sSpeedUp = this.speedUp;
            sReversed = this.reversed;
            sSetpoint = this.setpoint;
            sSpinupInput = this.spinupInput;
            sSpinupOutput = this.spinupOutput;
            sMaxOutput = this.maxOutput;
        }

        if (sCoast) {
            result = 0;
        } else if (sSpeedUp) {
            if (in >= sSetpoint) {
                result = 0;
            } else if (in >= sSpinupInput) {
                result = sReversed ? -sMaxOutput : sMaxOutput;
            } else {
                result = sReversed ? -sSpinupOutput : sSpinupOutput;
            }
        } else {
            if (in >= sSetpoint) {
                result = 0;
            } else {
                result = sReversed ? -sMaxOutput : sMaxOutput;
            }
        }

        output.set(result);

        synchronized (lock) {
            prevInput = in;
            prevResult = result;
        }
    }

    /**
     * Returns the previous input from the controller.
     *
     * @return last input received
     * @see #getPrevInput()
     */
    @Override
    public double get() {
        synchronized (lock) {
            return prevInput;
        }
    }

    /**
     * Sets the desired input that is to be achieved through this controller.
     *
     * @param value desired point that the input should reach
     * @see #setSetpoint(double)
     */
    @Override
    public void set(double value) {
        synchronized (lock) {
            this.setpoint = value;
        }
    }

    /**
     * Returns the previous input from the controller.
     *
     * @return last input received
     * @see #getPrevInput()
     */
    @Override
    public double getRate() {
        synchronized (lock) {
            return prevInput;
        }
    }

    /**
     * Sets the desired input that is to be achieved through this controller.
     *
     * @param rate desired point that the input should reach
     * @see #setSetpoint(double)
     */
    @Override
    public void setRate(double rate) {
        synchronized (lock) {
            this.setpoint = rate;
        }
    }
}
