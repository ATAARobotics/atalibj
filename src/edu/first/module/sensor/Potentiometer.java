package edu.first.module.sensor;

/**
 * Interface that represents potentiometer sensors. Is capable of finding what
 * position the sensor is in.
 *
 * @author Team 4334
 */
public interface Potentiometer {

    /**
     * Returns the exact position that the potentiometer gives. This method
     * should give a value between 0 and 10.
     *
     * @return arbitrary unit position between 0 and 10
     */
    double getPosition();
}
