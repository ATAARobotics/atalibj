package edu.first.module.sensor;

import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.parsing.ISensor;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * VEX Integrated Motor Encoder.
 *
 * This class allows access to a VEX Integrated Motor Encoder on an I2C bus.
 *
 * Details on the sensor can be found here:
 * http://www.vexforum.com/wiki/index.php/Intergrated_Motor_Encoders
 *
 * our experience is that this cannot run at the same time as a HiTechnic Colour
 * Sensor plugged into the NXT port
 *
 * the first physically connected IME has to be accessed using the default
 * address, then have its address changed, then unset it termination state so it
 * can pass into to the next in the chain connected device. For the next device
 * you follow the same steps, but leave it terminated if it is last in the
 * chain.
 *
 */
public final class VexIntegratedMotorEncoder extends SensorBase implements ISensor, LiveWindowSendable {

    /**
     * An exception dealing with connecting to and communicating with the device
     */
    public class IntegratedMotorEncoderException extends RuntimeException {

        /**
         * Create a new exception with the given message
         *
         * @param message the message to pass with the exception
         */
        public IntegratedMotorEncoderException(String message) {
            super(message);
        }
    }
    private static final boolean mVerbose = false; // false to suppress development console messages
    private static final byte kDefaultAddress = 0x60;
    private byte m_address = kDefaultAddress;
    private static final byte kDeviceStatusRegister = 0x23;
    private static final byte kManufacturerBaseRegister = 0x08;
    private static final byte kManufacturerSize = 0x8;
    private static final byte kSensorTypeBaseRegister = 0x10;  // was 0x10 for color sensor;
    private static final byte kSensorTypeSize = 0x08;
    private static final byte kSignedVelocityRegisterMSB = 0x3e;
    private static final byte kSignedVelocityRegisterLSB = 0x3f;
    private static final byte kUnSignedVelocityRegisterMSB = 0x44;
    private static final byte kUnSignedVelocityRegisterLSB = 0x45;
    private static final byte kRotationRegisterLowLSB = 0x41;
    private static final byte kRotationRegisterLowMSB = 0x40;
    private static final byte kRotationRegisterMiddleMSB = 0x42;
    private static final byte kRotationRegisterMiddleLSB = 0x43;
    private static final byte kRotationRegisterHignMSB = 0x47;
    private static final byte kRotationRegisterHighLSB = 0x46;
    private static final byte kClearDeviceCounters = 0x4a;
    private static final byte kDisableTerminator = 0x4b; // so next device in chain can be seen
    private static final byte kEnableTerminator = 0x4c; // default, device is last in chain
    private static final byte kChangeDeviceAddress = 0x4d;
    private static final byte kLowestDeviceAddress = 0x20;
    private static final byte kHighestDeviceAddress = 0x5e;
    // see http://www.vexforum.com/showthread.php?p=255691
    // says 39.2 times per output revolution for high torque and 24.5 times per output revolution high speed
    // also from http://www.vexrobotics.com/276-1321.html get 
    // Measures 627.2 ticks per revolution of the output shaft. (High Torque Configuration)
    // Measures 392 ticks per revolution of the output shaft (High Speed Configuration)
    private static final double kTicksPerRevHighTorque = 627.2; // factor with normal high torque gearing
    private static final double kTicksPerRevHighSpeed = 392; // factor with optional high speed gearing
    private double m_TicksPerRev;
    private I2C m_i2c;
    private int m_Version;
    private int m_Type;
    private int m_BoardID;
    private byte m_DeviceStatus;
    private boolean m_Terminated;
    private boolean m_Overflow;
    private boolean m_Diagnostic;
    private int m_Direction;

    /**
     * Constructor.
     *
     * @param slot The slot of the digital module that the sensor is plugged
     * into.
     * @param newAddress1 The new address of the first encoder.
     * @param newAddress2 The new address of the second encoder.
     * @param gearing Whether the gearing is set to standard high torque or
     * optional high speed.
     */
    public VexIntegratedMotorEncoder(int slot, byte newAddress, String gearing, boolean setTerminated) {
        Timer myTimer = new Timer();
        myTimer.start();
        DigitalModule module = DigitalModule.getInstance(slot);
        //m_i2c = module.getI2C(kAddressRegister);
        m_i2c = module.getI2C(kDefaultAddress);
        System.out.println("device is " + (m_i2c.addressOnly() ? "not " : "") + "present at address 0x" + Integer.toHexString(kDefaultAddress));
        if (newAddress != kDefaultAddress) { // change address if not using the default address
            Timer.delay(0.5); // put in a delay to see if we can get the second chain device to be connected
            setAddress(newAddress);
            m_i2c.free(); // release the previously addressed object
            m_i2c = module.getI2C(m_address);
            System.out.println("device is " + (m_i2c.addressOnly() ? "not " : "") + "present at address 0x" + Integer.toHexString(m_address));
        }

        setTicksPerRev(gearing);

        // terminate device as required
        if (setTerminated) { // requesting device to be terminated
            setTerminated();
        } else { // requesting device to be un-terminated
            unSetTerminated();
        }
    }

    /**
     * Destructor.
     */
    public final void free() {
        if (m_i2c != null) {
            m_i2c.free();
        }
        m_i2c = null;
    }

    public final void reset() {
        m_i2c.write(kClearDeviceCounters, (int) 1); // can write any value to the register
    }

    public final double getRaw() {
        byte[] buffer = new byte[1];
        m_i2c.read(kRotationRegisterLowMSB, (byte) buffer.length, buffer);
        byte low_msb = buffer[0];
        m_i2c.read(kRotationRegisterLowLSB, (byte) buffer.length, buffer);
        byte low_lsb = buffer[0];
        m_i2c.read(kRotationRegisterMiddleMSB, (byte) buffer.length, buffer);
        byte mid_msb = buffer[0];
        m_i2c.read(kRotationRegisterMiddleLSB, (byte) buffer.length, buffer);
        byte mid_lsb = buffer[0];
        return combine4Bytes(low_lsb, low_msb, mid_lsb, mid_msb);

    }

    public final double getRawHigh() {
        byte[] buffer = new byte[1];
        m_i2c.read(kRotationRegisterHignMSB, (byte) buffer.length, buffer);
        byte high_msb = buffer[0];
        m_i2c.read(kRotationRegisterHighLSB, (byte) buffer.length, buffer);
        byte high_lsb = buffer[0];
        return combine2Bytes(high_lsb, high_msb);
    }

    public final double combine4Bytes(byte low_lsb, byte low_msb, byte mid_lsb, byte mid_msb) {
        long m_low_lsb = (long) (low_lsb & 0xff);
        long m_low_msb = (long) ((low_msb << 8) & 0xff00);
        long m_mid_lsb = (long) ((mid_lsb << 16) & 0xff0000);
        long m_mid_msb = (long) ((mid_msb << 24) & 0xff000000);
        return (m_low_lsb | m_low_msb | m_mid_lsb | m_mid_msb);
    }

    public final double combine2Bytes(byte mid_lsb, byte mid_msb) {
        short m_mid_lsb = (short) (mid_lsb & 0xff);
        short m_mid_msb = (short) ((mid_msb << 8) & 0xff00);
        return (m_mid_lsb | m_mid_msb);
    }

    /**
     * Set the Address of the encoder This method is used to set the encoder to
     * an even address within range of 0x20 to 0x5E, from its initial default of
     * 0x60 by writing to address 0x4D
     *
     * @param mode The address to set
     */
    public final void setAddress(byte address) {
        if (address < kLowestDeviceAddress || address > kHighestDeviceAddress) {
            throw new IntegratedMotorEncoderException("Encoder Address Out Of Range");
        } else if (address % 2 != 0) {
            throw new IntegratedMotorEncoderException("Encoder Address Must Be Even");
        } else {
            int m_old_address = m_address;
            m_i2c.write(kChangeDeviceAddress, (int) address);
            m_address = address;
            if (mVerbose) {
                System.out.println("setAddress(): Address changed from 0x" + Integer.toHexString(m_old_address) + " to 0x" + Integer.toHexString(m_address));
            }
        }
    }

    public final boolean getTerminated() {
        byte[] address = new byte[1];
        m_i2c.read(kDeviceStatusRegister, (byte) address.length, address);
        m_Terminated = ((int) address[0] & 0x1) == 1 ? true : false;
        if (mVerbose) {
            System.out.println("getTerminated() kDeviceStatusRegister = : " + Integer.toHexString(address[0]) + " raw and m_Terminated is " + m_Terminated);
        }
        return m_Terminated;
    }

    public final void setTerminated() {
        if (!this.getTerminated()) { // if device is not already terminated then write bytes to terminate
            m_i2c.write(kEnableTerminator, 1);
            m_Terminated = true;
            System.out.println("setTerminated(): device status changed to terminated");
        } else {
            System.out.println("setTerminated(): device status already set to terminated");
        }
    }

    public final void unSetTerminated() {
        if (this.getTerminated()) { // if device is already terminated then write bytes to terminate
            m_i2c.write(kDisableTerminator, 1);
            m_Terminated = false;
            System.out.println("unSetTerminated(): device status changed to not terminated");
        } else {
            System.out.println("unSetTerminated(): device status already set to not terminated");
        }
    }

    public final double getRevs() {
        return this.getRaw() / m_TicksPerRev;
    }

    public final double get() {
        return (this.getRevs() % 1) * 360.0;
    }

    public final void setTicksPerRev(String gearing) {
        // adjust ticks per revolution depending on gearing
        if (gearing.toLowerCase().equals("torque")) {
            m_TicksPerRev = kTicksPerRevHighTorque;
        } else if (gearing.toLowerCase().equals("speed")) {
            m_TicksPerRev = kTicksPerRevHighSpeed;
        } else {
            System.out.println("Invalid Gear Ratio Input As: " + gearing);
        }
        if (mVerbose) {
            System.out.println("setTicksPerRev(): " + gearing + " resulted in ticks per revolution set to " + m_TicksPerRev);

        }
    }

    /*
     * Live Window code, only does anything if live window is activated.
     */
    public final String getSmartDashboardType() {
        return "VexEncoder";
    }
    private ITable m_table;

    /**
     * {@inheritDoc}
     */
    public final void initTable(ITable subtable) {
        m_table = subtable;
        updateTable();
    }

    /**
     * {@inheritDoc}
     */
    public final ITable getTable() {
        return m_table;
    }

    /**
     * {@inheritDoc}
     */
    public final void updateTable() {
        if (m_table != null) {
            m_table.putNumber("Rotation", get());
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void startLiveWindowMode() {
    }

    /**
     * {@inheritDoc}
     */
    public final void stopLiveWindowMode() {
    }
}