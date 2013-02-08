package edu.ATA.module.target;

import edu.ATA.module.Module;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Module to control a bang-bang virtual speed controller. This follows the
 * basic use as http://www.chiefdelphi.com/media/papers/2663 and
 * http://en.wikipedia.org/wiki/Bang%E2%80%93bang_control.
 *
 * @author Team 4334
 */
public class BangBangModule implements Module.DisableableModule, BangBangController {

    private boolean enabled;
    private double setpoint = 0;
    private double maxSpeed = 1;
    private final double defaultSpeed;
    private final PIDSource source;
    private final PIDOutput output;
    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        public void run() {
            if (enabled) {
                if (source.pidGet() >= setpoint) {
                    output.pidWrite(defaultSpeed);
                } else {
                    output.pidWrite(maxSpeed);
                }
            }
        }
    };

    /**
     * Constructs and begins the thread. This will not start moving, but rather
     * be "ready" to move when enabled.
     *
     * @param source the source to check setpoints
     * @param output output of the controller
     */
    public BangBangModule(PIDSource source, PIDOutput output) {
        this(source, output, 0);
    }

    /**
     * Constructs and begins the thread. This will not start moving, but rather
     * be "ready" to move when enabled.
     *
     * @param source the source to check setpoints
     * @param output output of the controller
     * @param defaultSpeed speed to run when above setpoint
     */
    public BangBangModule(PIDSource source, PIDOutput output, double defaultSpeed) {
        if (source == null || output == null) {
            throw new NullPointerException();
        }
        this.source = source;
        this.output = output;
        this.defaultSpeed = defaultSpeed;
        timer.scheduleAtFixedRate(task, 0L, 20L);
    }

    /**
     * Stops the controller from changing output on the output object.
     *
     * @return if module is disabled successfully
     */
    public synchronized boolean disable() {
        boolean d = true;
        if (source instanceof Module) {
            d = d && ((Module) source).enable();
        }
        if (output instanceof Module) {
            d = d && ((Module) output).enable();
        }
        return !(enabled = false) && d;
    }

    /**
     * Starts the controller. If and when a setpoint is set, the controller will
     * set the corresponding output.
     *
     * @return if module is enabled successfully
     */
    public synchronized boolean enable() {
        boolean e = true;
        if (source instanceof Module) {
            e = e && ((Module) source).enable();
        }
        if (output instanceof Module) {
            e = e && ((Module) output).enable();
        }
        return (enabled = true) && e;
    }

    /**
     * Checks to see if the controller is currently actively pursuing the
     * setpoint. Even if the module is enabled, it will not work in disabled
     * mode.
     *
     * @return if module is enabled
     */
    public synchronized boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the speed / position that the controller is aiming for.
     *
     * @param setpoint position / speed to stop going at
     */
    public synchronized void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    /**
     * Returns whether the input is <b>currently</b> above the setpoint. Does
     * not mean bang bang is off. (gets the most recent input)
     *
     * @return if input is higher than setpoint
     */
    public synchronized boolean pastSetpoint() {
        return source.pidGet() > setpoint;
    }

    /**
     * Sets the maximum speed to set the output. If this value is negative, the
     * absolute value is used. To reverse the output direction, use
     * {@code reverse()}.
     *
     * @param maxSpeed maximum speed of output
     */
    public synchronized void setMaxSpeed(double maxSpeed) {
        if (this.maxSpeed < 0) {
            this.maxSpeed = -Math.abs(maxSpeed);
        } else {
            this.maxSpeed = Math.abs(maxSpeed);
        }
    }

    /**
     * Reverses the direction of the output. This is useful because bang bang
     * only goes in one direction.
     */
    public synchronized void reverse() {
        maxSpeed = -maxSpeed;
    }
}
