package edu.ATA.module.target;

import edu.ATA.main.DriverstationInfo;
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
public class BangBangModule implements Module.DisableableModule {

    private boolean enabled;
    private double setpoint = 0;
    private final PIDSource source;
    private final PIDOutput output;
    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        public void run() {
            if (enabled) {
                if (!DriverstationInfo.getGamePeriod().equals(DriverstationInfo.DISABLED)) {
                    if (source.pidGet() > setpoint) {
                        output.pidWrite(0);
                    } else {
                        output.pidWrite(1);
                    }
                }
            }
        }
    };

    public BangBangModule(PIDSource source, PIDOutput output) {
        this.source = source;
        this.output = output;
        timer.scheduleAtFixedRate(task, (long) 0.0, (long) 0.02);
    }

    public synchronized boolean disable() {
        return !(enabled = false);
    }

    public synchronized boolean enable() {
        return (enabled = true);
    }

    public synchronized boolean isEnabled() {
        return enabled;
    }

    public synchronized void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }
}
