package edu.first.identifiers;

import edu.first.bindings.AxisBind;

/**
 * Interface representing anything that can be set to a double value.
 *
 * @author Joel Gallant
 */
public interface SetteableNumber extends AxisBind {

    public void set(double value);
}
