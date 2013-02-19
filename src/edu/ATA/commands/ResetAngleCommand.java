
package edu.ATA.commands;

import edu.first.module.sensor.GyroModule;

/**
 *
 * @author denis
 */
public class ResetAngleCommand extends ThreadableCommand{
    
    private final GyroModule gyro;

    public ResetAngleCommand(GyroModule gyro, boolean newThread) {
        super(newThread);
        this.gyro = gyro;
    }
    
     public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                gyro.reset();
            }
        };
    }
    
    
}
