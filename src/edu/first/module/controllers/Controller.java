package edu.first.module.controllers;

import java.util.Timer;
import java.util.TimerTask;
import edu.first.module.Module;

/**
 * The general module for virtual controllers. Runs the {@link #run()} method in
 * a loop whenever the module is enabled.
 *
 * <p>
 * The two options for execution are {@link LoopType#FIXED_DELAY} and
 * {@link LoopType#FIXED_RATE}. A fixed-rate execution tries to ensure your
 * {@code run()} method is called at the rate given at all times. It will
 * dynamically adjust to try and run the method at the specified rate. See
 * {@link Timer#scheduleAtFixedRate(java.util.TimerTask, long, long)} for more
 * info. A fixed-delay execution waits the specified time after every execution
 * of {@code run()}. There is no compensation when threads get slow.
 *
 * @since May 30 2013
 * @author Joel Gallant
 */
public abstract class Controller extends Module.StandardModule implements
        Runnable {

    private final int loopTime;
    private final LoopType loopType;
    private Timer loopController;

    /**
     * Constructs the controller with the loop time and the type of loop to run.
     * Loop types are static instances in {@link LoopType}.
     *
     * @param loopTime time in seconds each loop should run
     * @param loopType kind of execution of the loop
     */
    public Controller(double loopTime, LoopType loopType) {
        this.loopTime = (int) Math.round(loopTime * 1000.0);
        this.loopType = loopType;
    }

    /**
     * Constructs the controller with the loop time and the type of loop to run.
     * Loop types are static instances in {@link LoopType}.
     *
     * @param loopTimeHertz the hertz value that represents how fast execution
     * will happen
     * @param loopType kind of execution of the loop
     */
    public Controller(int loopTimeHertz, LoopType loopType) {
        this(1.0 / (double) loopTimeHertz, loopType);
    }

    /**
     * Starts the loop of the controller.
     */
    @Override
    protected void enableModule() {
        if (loopType.equals(LoopType.FIXED_DELAY)) {
            (loopController = new Timer()).schedule(task(), 0, loopTime);
        } else if (loopType.equals(LoopType.FIXED_RATE)) {
            (loopController = new Timer()).scheduleAtFixedRate(task(), 0,
                    loopTime);
        }
    }

    /**
     * Stops the loop of the controller. If it is in the middle of an execution,
     * that execution will complete before the controller turns off.
     */
    @Override
    protected void disableModule() {
        if (loopController != null) {
            loopController.cancel();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
    }

    private TimerTask task() {
        return new TimerTask() {
            @Override
            public void run() {
                Controller.this.run();
            }
        };
    }

    /**
     * Enum representing the different types of loops that a controller can run
     * in.
     */
    public static enum LoopType {

        FIXED_DELAY, FIXED_RATE;
    }
}
