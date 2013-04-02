package edu.first.module.target;

import edu.first.module.Module;
import edu.first.module.speedcontroller.SpeedControllerModule;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Module to control a bang-bang virtual controller. This follows the basic use
 * as http://www.chiefdelphi.com/media/papers/2663 and
 * http://en.wikipedia.org/wiki/Bang%E2%80%93bang_control.
 *
 * @author Joel Gallant
 */
public final class BangBangModule implements Module.DisableableModule, BangBangController {

    private boolean enabled;
    private boolean coast;
    private boolean reversed = false;
    private double setpoint = 0;
    private double maxSpeed = 1;
    private double pastSetpoint = 0;
    private double defaultSpeed;
    private final PIDSource source;
    private final PIDOutput output;
    private Timer timer = new Timer();
    private final TimerTask task = new BangBangTask();
    private final Object lock = new Object();

    private class BangBangTask extends TimerTask {

        public void run() {
            synchronized (lock) {
                if (enabled) {
                    if (coast) {
                        output.pidWrite(0);
                    } else {
                        if (source.pidGet() >= setpoint) {
                            if (setpoint == 0) {
                                output.pidWrite(0);
                            } else {
                                output.pidWrite(defaultSpeed * (reversed ? -1 : 1));
                            }
                        } else {
                            output.pidWrite(maxSpeed * (reversed ? -1 : 1));
                        }
                    }
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
        this(source, output, defaultSpeed, false);
    }

    /**
     * Constructs and begins the thread. This will not start moving, but rather
     * be "ready" to move when enabled.
     *
     * @param source the source to check setpoints
     * @param output output of the controller
     * @param defaultSpeed speed to run when above setpoint
     * @param reverse if output should be reversed
     */
    public BangBangModule(PIDSource source, PIDOutput output, double defaultSpeed, boolean reverse) {
        if (source == null || output == null) {
            throw new NullPointerException();
        }
        this.source = source;
        this.output = output;
        this.defaultSpeed = defaultSpeed;
        this.reversed = reverse;
        timer.scheduleAtFixedRate(task, 0L, 10L);
    }

    /**
     * Constructs and begins the thread. This will not start moving, but rather
     * be "ready" to move when enabled.
     *
     * @param source the source to check setpoints
     * @param output output of the controller
     * @param defaultSpeed speed to run when above setpoint
     * @param pastSetpoint what tolerance pastSetpoint() should have
     * @param reverse if output should be reversed
     */
    public BangBangModule(PIDSource source, PIDOutput output, double defaultSpeed, double pastSetpoint, boolean reverse) {
        if (source == null || output == null) {
            throw new NullPointerException();
        }
        this.source = source;
        this.output = output;
        this.defaultSpeed = defaultSpeed;
        this.pastSetpoint = pastSetpoint;
        this.reversed = reverse;
        timer.scheduleAtFixedRate(task, 0L, 10L);
    }

    /**
     * Stops the controller from changing output on the output object. If the
     * input or output are a {@link DisableableModule} or
     * {@link SpeedControllerModule}, they are disabled as well.
     *
     * @return if module is disabled successfully
     */
    public boolean disable() {
        synchronized (lock) {
            boolean d = true;
            if (source instanceof DisableableModule) {
                d = d && ((DisableableModule) source).disable();
            }
            if (output instanceof DisableableModule) {
                d = d && ((DisableableModule) output).disable();
            } else if (output instanceof SpeedControllerModule) {
                ((SpeedControllerModule) output).disable();
            }
            return !(enabled = !d);
        }
    }

    /**
     * Starts the controller. If and when a setpoint is set, the controller will
     * set the corresponding output. If the input or output are a
     * {@link Module}, they are disabled.
     *
     * @return if module is enabled successfully
     */
    public boolean enable() {
        synchronized (lock) {
            boolean e = true;
            if (source instanceof Module) {
                e = e && ((Module) source).enable();
            }
            if (output instanceof Module) {
                e = e && ((Module) output).enable();
            }
            return (enabled = e);
        }
    }

    /**
     * Checks to see if the controller is currently actively pursuing the
     * setpoint. Even if the module is enabled, it will not work in disabled
     * mode.
     *
     * @return if module is enabled
     */
    public boolean isEnabled() {
        synchronized (lock) {
            return enabled;
        }
    }

    /**
     * Sets the speed / position that the controller is aiming for.
     *
     * @param setpoint position / speed to stop going at
     */
    public void setSetpoint(double setpoint) {
        synchronized (lock) {
            this.setpoint = setpoint;
        }
    }

    /**
     * Returns the currently set setpoint.
     *
     * @return the current setpoint
     */
    public double getSetpoint() {
        synchronized (lock) {
            return setpoint;
        }
    }

    /**
     * Returns whether the input is <b>currently</b> above the setpoint. Does
     * not mean bang bang is not on, because this method gets the most recent
     * input, rather than getting the last used input from the bang bang thread.
     *
     * @return if input is higher than setpoint
     */
    public boolean pastSetpoint() {
        synchronized (lock) {
            return source.pidGet() > (setpoint - pastSetpoint);
        }
    }

    /**
     * Sets how close the input need to be for
     * {@link BangBangModule#pastSetpoint()} to return true.
     *
     * @param pastSetpoint how close input should be
     */
    public void setPastSetpoint(double pastSetpoint) {
        synchronized (lock) {
            this.pastSetpoint = pastSetpoint;
        }
    }

    /**
     * Returns the value of the input. Is not necessarily a value used by the
     * controller.
     *
     * @return input from given {@link PIDSource}
     */
    public double getInput() {
        return source.pidGet();
    }

    /**
     * Sets the maximum speed to set the output. If this value is negative, the
     * absolute value is used. To reverse the output direction, use
     * {@code reverse()}.
     *
     * @param maxSpeed maximum speed of output
     */
    public void setMaxSpeed(double maxSpeed) {
        synchronized (lock) {
            if (this.maxSpeed < 0) {
                this.maxSpeed = -Math.abs(maxSpeed);
            } else {
                this.maxSpeed = Math.abs(maxSpeed);
            }
        }
    }

    /**
     * Sets the speed to set the output when the input is over the setpoint.
     * This should be slightly below the needed speed to reach the setpoint. Use
     * {@link BangBangModule#reverse()} to reverse the output.
     *
     * @param defaultSpeed speed to bring near setpoint
     */
    public void setDefaultSpeed(double defaultSpeed) {
        synchronized (lock) {
            this.defaultSpeed = Math.abs(defaultSpeed);
        }
    }

    /**
     * If given a true value, sets the output to zero until told otherwise
     * (setting to false). This allows an override of the output to make sure
     * signals are not sent, without disabling the module and re-enabling, which
     * would be costly for performance.
     *
     * @param coast if output should be zero
     */
    public void setCoast(boolean coast) {
        synchronized (lock) {
            this.coast = coast;
        }
    }

    /**
     * Reverses the direction of the output. This is useful because bang bang
     * only goes in one direction.
     */
    public void reverse() {
        synchronized (lock) {
            reversed = !reversed;
        }
    }
}
