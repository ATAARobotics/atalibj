package edu.first.bindings;

/**
 * Axis bind that binds one axis with multiple binds. Sets every axis bind given
 * to the same value.
 *
 * @author Joel Gallant
 */
public final class MultipleAxisBind implements AxisBind {

    private final AxisBind[] binds;

    /**
     * Constructs the bind with all of the axis binds needed.
     *
     * @param binds all binds to set
     */
    public MultipleAxisBind(AxisBind[] binds) {
        this.binds = binds;
    }

    /**
     * Sets all of the binds to the axis value.
     *
     * @param axisValue value to set all binds to
     */
    public void set(double axisValue) {
        // Preference to newer binds
        for (int x = binds.length - 1; x >= 0; x--) {
            binds[x].set(axisValue);
        }
    }
}
