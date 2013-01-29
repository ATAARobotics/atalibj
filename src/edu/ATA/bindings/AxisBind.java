package edu.ATA.bindings;

/**
 * Bind that lets you bind an axis to an output. This is the output portion,
 * where the axis value is sent.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface AxisBind extends Bind {

    /**
     * Sends the axis bind to the binded object.
     *
     * @param axisValue value of the axis
     */
    public void set(double axisValue);
}
