package edu.first.robot;

import edu.first.main.GamePeriods;

/**
 * An implementation of {@link RobotMode} that catches all exceptions in every
 * method. This class is not really meant to be used by anything but
 * {@link GamePeriods}. Every robot mode is wrapped using this class, so that
 * errors do not disrupt the main thread. There is nothing wrong with using this
 * class elsewhere, but there isn't much reason to unless you are using a custom
 * {@code GamePeriods} class.
 *
 * @since May 08 13
 * @author Joel Gallant
 */
public final class SafeRobotMode implements RobotMode {

    /**
     * The wrapped mode.
     */
    private final RobotMode mode;

    /**
     * Constructs the safe robot mode with the actual robot mode underneath it.
     *
     * @param mode wrapped mode
     */
    public SafeRobotMode(RobotMode mode) {
        this.mode = mode;
    }

    /**
     * {@inheritDoc}
     */
    public void init() {
        try {
            mode.init();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void initAutonomous() {
        try {
            mode.initAutonomous();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void initTeleoperated() {
        try {
            mode.initTeleoperated();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void initDisabled() {
        try {
            mode.initDisabled();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void initTest() {
        try {
            mode.initTest();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void periodicAutonomous() {
        try {
            mode.periodicAutonomous();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void periodicTeleoperated() {
        try {
            mode.periodicTeleoperated();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void periodicDisabled() {
        try {
            mode.periodicDisabled();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void periodicTest() {
        try {
            mode.periodicTest();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void endAutonomous() {
        try {
            mode.endAutonomous();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void endTeleoperated() {
        try {
            mode.endTeleoperated();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void endDisabled() {
        try {
            mode.endDisabled();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void endTest() {
        try {
            mode.endTest();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return mode.getName();
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.mode != null ? this.mode.hashCode() : 0);
        return hash;
    }

    /**
     * Returns whether the underlying {@code RobotMode} is equal to that of
     * another safe robot mode.
     * 
     * @param obj object to compare
     * @return if mode is equivalent
     */
    public boolean equals(Object obj) {
        if (obj instanceof SafeRobotMode) {
            return mode.equals(((SafeRobotMode) obj).mode);
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return getName();
    }
}
