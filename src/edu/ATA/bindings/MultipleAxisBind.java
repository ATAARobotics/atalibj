package edu.ATA.bindings;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class MultipleAxisBind implements AxisBind {

    private final AxisBind[] prev;

    public MultipleAxisBind(AxisBind[] prev) {
        this.prev = prev;
    }

    public void set(double axisValue) {
        // Preference to newer binds
        for(int x = prev.length - 1; x >= 0; x--) {
            prev[x].set(axisValue);
        }
    }
}
