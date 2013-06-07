package edu.first.module.sensors;

import edu.first.module.Module;
import edu.first.util.Enum;
import edu.wpi.first.wpilibj.ADXL345_I2C;

/**
 * The ADXL345 I2C bus accelerometer that is included in the KoP. Has 3
 * different axises that can be read.
 *
 * @since June 07 13
 * @author Joel Gallant
 */
public class ADXL345_Accelerometer extends Module.StartardModule implements Accelerometer {

    private final ADXL345_I2C accelerometer;
    private Axis axis = Axis.X;

    /**
     * Constructs the module using the accelerometer object to call functions
     * from.
     *
     * @param servo the composing instance which perform the functions
     */
    protected ADXL345_Accelerometer(ADXL345_I2C accelerometer) {
        this.accelerometer = accelerometer;
    }

    /**
     * Constructs the module using the slot of the digital module that the
     * sensor is plugged into.
     *
     * @param moduleNumber slot on cRIO (default = 1)
     * @param formatRange the range (+ or -) that the accelerometer will measure
     */
    public ADXL345_Accelerometer(int moduleNumber, DataFormatRange formatRange) {
        this(new ADXL345_I2C(moduleNumber, convert(formatRange)));
    }

    /**
     * Constructs the module using the format range.
     *
     * @param formatRange the range (+ or -) that the accelerometer will measure
     */
    public ADXL345_Accelerometer(DataFormatRange formatRange) {
        this(1, formatRange);
    }

    /**
     * {@inheritDoc}
     */
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     */
    protected void disableModule() {
    }

    /**
     * {@inheritDoc}
     */
    public void init() {
    }

    /**
     * Sets the axis that {@link #getAcceleration()} will return. It is by
     * default {@link Axis#X}.
     *
     * @param axis the axis to read by default
     */
    public void setAxis(Axis axis) {
        this.axis = axis;
    }

    /**
     * Reads the axis that was given in
     * {@link #setAxis(edu.first.module.sensors.ADXL345_Accelerometer.Axis)}. If
     * no axis has been set, defaults to {@link Axis#X}.
     *
     * @return current acceleration of selected axis
     */
    public double getAcceleration() {
        ensureEnabled();
        return accelerometer.getAcceleration(convertAxis(axis));
    }

    /**
     * Returns the acceleration of the given axis.
     *
     * @param axis which axis to read
     * @return acceleration measured on axis
     */
    public double getAcceleration(Axis axis) {
        ensureEnabled();
        return accelerometer.getAcceleration(convertAxis(axis));
    }

    /**
     * Returns the acceleration of the given axis.
     *
     * @param axis which axis to read
     * @return acceleration measured on axis
     * @see #getAcceleration()
     */
    public double get() {
        ensureEnabled();
        return accelerometer.getAcceleration(convertAxis(axis));
    }

    private static ADXL345_I2C.DataFormat_Range convert(DataFormatRange formatRange) {
        if (formatRange.equals(DataFormatRange.TWO_G)) {
            return ADXL345_I2C.DataFormat_Range.k2G;
        } else if (formatRange.equals(DataFormatRange.FOUR_G)) {
            return ADXL345_I2C.DataFormat_Range.k4G;
        } else if (formatRange.equals(DataFormatRange.EIGHT_G)) {
            return ADXL345_I2C.DataFormat_Range.k8G;
        } else if (formatRange.equals(DataFormatRange.SIXTEEN_G)) {
            return ADXL345_I2C.DataFormat_Range.k16G;
        } else {
            throw new IllegalArgumentException(formatRange + " is illegal format range");
        }
    }

    private static ADXL345_I2C.Axes convertAxis(Axis axis) {
        if (axis.equals(Axis.X)) {
            return ADXL345_I2C.Axes.kX;
        } else if (axis.equals(Axis.Y)) {
            return ADXL345_I2C.Axes.kY;
        } else if (axis.equals(Axis.Z)) {
            return ADXL345_I2C.Axes.kZ;
        } else {
            throw new IllegalArgumentException(axis + " is illegal axis");
        }
    }

    /**
     * The range (+ or -) that the accelerometer will measure. The higher the
     * number, the higher the reading can be. It can have the side effect of
     * reducing preciseness.
     */
    public static final class DataFormatRange extends Enum {

        /**
         * Measures +/- 2 G's.
         */
        public static final DataFormatRange TWO_G = new DataFormatRange("TWO_G");
        /**
         * Measures +/- 4 G's.
         */
        public static final DataFormatRange FOUR_G = new DataFormatRange("FOUR_G");
        /**
         * Measures +/- 8 G's.
         */
        public static final DataFormatRange EIGHT_G = new DataFormatRange("EIGHT_G");
        /**
         * Measures +/- 16 G's.
         */
        public static final DataFormatRange SIXTEEN_G = new DataFormatRange("SIXTEEN_G");

        private DataFormatRange(String name) {
            super(name);
        }
    }

    /**
     * The axis to read from the accelerometer.
     */
    public static final class Axis extends Enum {

        /**
         * Reads the X axis. (Labeled on device)
         */
        public static final Axis X = new Axis("X");
        /**
         * Reads the Y axis. (Labeled on device)
         */
        public static final Axis Y = new Axis("Y");
        /**
         * Reads the Z axis. (Labeled on device)
         */
        public static final Axis Z = new Axis("Z");

        private Axis(String name) {
            super(name);
        }
    }
}
