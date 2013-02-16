package edu.first.utils;

/**
 * Info class that keeps track of how quickly packets arrive. This can be useful
 * if packets seem to be lagging or being lost.
 *
 * @author Joel Gallant
 */
public final class TransferRateCalculator {

    /**
     * "Official" transfer rate calculator. This does not prevent someone from
     * creating a different instance for different uses.
     */
    public static final TransferRateCalculator INSTANCE = new TransferRateCalculator();
    private int packageCountAtStart = DriverstationInfo.getPacketCount();
    private long lastPPSCheck = System.currentTimeMillis();
    private int lastPacketsCheck = 0;

    /**
     * Returns the amount of packets being sent to the DriverStation every
     * millisecond. This should ideally return 0.05 all of the time. (0.05 PPM *
     * 20 MS = 1 packet per 20 milliseconds) If this method returns 0, it
     * usually means you are calling it too last (not enough time to receive new
     * packets).
     *
     * @return how many packets are being sent per millisecond
     */
    public double packetsPerMillisecond() {
        // NaN guarantee
        long s = millisecondsSinceLastCheck();
        if (s == 0) {
            return 0;
        }
        return (double) packetsSinceLastCheck() / (double) s;
    }

    /**
     * Resets the previous values and acts as if never checked.
     */
    public void reset() {
        lastPPSCheck = System.currentTimeMillis();
        lastPacketsCheck = 0;
    }

    private long millisecondsSinceLastCheck() {
        long s = (System.currentTimeMillis() - lastPPSCheck);
        lastPPSCheck = System.currentTimeMillis();
        return s;
    }

    private int packetsSinceLastCheck() {
        int packetCount = DriverstationInfo.getPacketCount();
        int s = packetCount - lastPacketsCheck - packageCountAtStart;
        lastPacketsCheck = packetCount - packageCountAtStart;
        return s;
    }
}
