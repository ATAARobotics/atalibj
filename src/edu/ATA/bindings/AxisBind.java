package edu.ATA.bindings;

/**
 * Bind that lets you bind an axis to an output. This is the output portion,
 * where the axis value is sent. A {@link Bindable} will use this object to set
 * values from an axis. Typically classes that are axis binds are classes that
 * "set" something, like the speed of a motor.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface AxisBind extends Bind {

    /**
     * Sends the value of the axis to the binded object. This is used to "set"
     * the object to a speed or similar value usually ranging from -1 to +1.
     *
     * @param axisValue value of the axis
     */
    public void set(double axisValue);
}
