package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.subsystem.Subsystem;
import edu.first.module.target.BangBangModule;

public final class ShooterWheel extends Subsystem {

    private final BangBangModule bangBang;

    public ShooterWheel(BangBangModule bangBang) {
        super(new Module[]{bangBang});
        this.bangBang = bangBang;
    }

    public void start() {
        // No thread needed
    }

    public void run() {
        // No thread needed
    }

    public void setRPM(double RPM) {
        bangBang.setSetpoint(RPM);
        bangBang.setDefaultSpeed(defSpeed(RPM));
    }

    public double getRPM() {
        return bangBang.getInput();
    }
    
    public double getSetpointRPM() {
        return bangBang.getSetpoint();
    }

    public boolean isPastSetpoint() {
        return bangBang.pastSetpoint();
    }

    private double defSpeed(double RPM) {
        double s;
        if (RPM < 2500) {
            s = 0;
        } else if (RPM < 3000) {
            s = 0.07;
        } else if (RPM < 3500) {
            s = 0.2;
        } else if (RPM < 4000) {
            s = 0.3;
        } else if (RPM < 4500) {
            s = 0.4;
        } else if (RPM < 5000) {
            s = 0.5;
        } else {
            s = 0.6;
        }
        return s;
    }
}