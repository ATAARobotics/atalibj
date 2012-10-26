package edu.ata.user;

import com.sun.squawk.microedition.io.FileConnection;
import edu.ata.main.Driverstation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.Connector;

/**
 * Logging class. Used to give messages to user in different ways depending on
 * urgency.
 *
 * <p> There is a fake {@link Urgency} class that holds urgency types. As we use
 * java source 1.3/1.4, we cannot use enums.
 *
 * <p> Note that all logs are automatically sent to file:///log.txt unless
 * otherwise set. See {@link Logger#setFileLoggingOn(boolean)}.
 *
 * @author Joel Gallant
 */
public class Logger {

    private static final String PATH = "file:///log.txt";
    private static boolean fileLoggingOn = true;
    private static int lineNum = 1;
    private static DataOutputStream outputStream;
    private static DataInputStream inputStream;
    private static FileConnection fileConnection;

    /**
     * Class representing the urgency of the log. The reason for this
     * implementation is to ensure that logging does not encounter an error when
     * looking at its urgency. (Ex. int could be out of range, etc.) This keeps
     * the only possible values to be pre-approved by this class. (No errors
     * when logging)
     */
    public final static class Urgency {

        private Urgency() {
        }
        /**
         * Urgent message. Displayed on DriverStation.
         */
        public final static Urgency URGENT = new Urgency();
        /**
         * Warning message. Displayed on DriverStation.
         */
        public final static Urgency WARNING = new Urgency();
        /**
         * User message. Displayed on DriverStation.
         */
        public final static Urgency USERMESSAGE = new Urgency();
        /**
         * Status report for logs. Is not displayed to user.
         */
        public final static Urgency STATUSREPORT = new Urgency();
        /**
         * Log message. Is not displayed to user.
         */
        public final static Urgency LOG = new Urgency();
    }

    /**
     * Logs a message, and depending on the urgency given, reacts appropriately.
     *
     * <p> By default, sends messages to the log file at file:///log.txt.
     *
     * @param urgency Urgency of the log. Urgencies are available as static
     * variables in the {@link Logger Log} class.
     * @param msg Message to send to the user.
     */
    public static void log(Urgency urgency, String msg) {
        String usrMsg = null;
        if (urgency == Urgency.URGENT) {
            usrMsg = "Urgent@" + Driverstation.getMatchTime() + "- " + msg;
            displayLCDMessage(usrMsg);
        } else if (urgency == Urgency.WARNING) {
            usrMsg = "Warning@" + Driverstation.getMatchTime() + "- " + msg;
            displayLCDMessage(usrMsg);
        } else if (urgency == Urgency.USERMESSAGE) {
            usrMsg = "User Message - " + msg;
            displayLCDMessage(usrMsg);
        } else if (urgency == Urgency.STATUSREPORT) {
            usrMsg = "Status@" + Driverstation.getMatchTime() + "- " + msg;
        } else if (urgency == Urgency.LOG) {
            usrMsg = "Log@" + Driverstation.getMatchTime() + "- " + msg;
        }
        System.out.println(usrMsg.concat("\n"));
        try {
            if (fileLoggingOn) {
                logFile(msg.concat("\n"));
            }
        } catch (IOException ex) {
            System.err.println("!!!ERROR WHILE WRITING TO LOG FILE!!!\n" + ex.getMessage());
        }
    }

    private static void logFile(String msg) throws IOException {
        if (fileConnection == null) {
            fileConnection = (FileConnection) Connector.open(PATH, Connector.READ_WRITE);
        }
        fileConnection.create();
        outputStream = fileConnection.openDataOutputStream();
        byte[] data = msg.getBytes();
        try {
            outputStream.write(data, 0, data.length);
        } finally {
            outputStream.close();
        }
    }

    /**
     * Gets the contents of the log file. Goes through each character and
     * concats it to the string. Is inefficient, so feel free to make it work
     * faster (<i>Test changes, reading without parsing is difficult</i>).
     *
     * @return contents of log
     * @throws IOException thrown when problem connecting occurs
     */
    public static String getLog() throws IOException {
        if (fileConnection == null) {
            fileConnection = (FileConnection) Connector.open(PATH, Connector.READ_WRITE);
        }
        fileConnection.create();
        inputStream = fileConnection.openDataInputStream();
        String tmp = "";
        byte cur;
        try {
            while ((cur = (byte) inputStream.read()) > 0) {
                tmp += (char) cur;
            }
            return tmp;
        } finally {
            outputStream.close();
        }
    }

    /**
     * Displays a message on the DriverStation LCD. Is convenience method for
     * {@link DriverStationLCD}.
     *
     * <p> This method should usually not be called.
     * {@link Logger#log(edu.ata.user.Logger.Urgency, java.lang.String)}
     * provides a better solution to displaying information to the user. This
     * method is available for public use just in case (It can't hurt).
     *
     * @param msg message to display to the user
     */
    public static void displayLCDMessage(String msg) {
        DriverStationLCD.Line line;
        switch (lineNum) {
            case (1):
                line = DriverStationLCD.Line.kMain6;
                break;
            case (2):
                line = DriverStationLCD.Line.kUser2;
                break;
            case (3):
                line = DriverStationLCD.Line.kUser3;
                break;
            case (4):
                line = DriverStationLCD.Line.kUser4;
                break;
            case (5):
                line = DriverStationLCD.Line.kUser5;
                break;
            case (6):
                line = DriverStationLCD.Line.kUser6;
                break;
            default:
                line = DriverStationLCD.Line.kMain6;
        }
        DriverStationLCD.getInstance().println(line, lineNum, msg + "\n");
        DriverStationLCD.getInstance().updateLCD();
        lineNum++;
        if (lineNum > 6) {
            lineNum = 1;
        }
    }

    /**
     * Sets {@link Logger#log(edu.ata.user.Logger.Urgency, java.lang.String)} to
     * automatically send messages to the log file (file:///log.txt). By
     * default, is on.
     *
     * @param fileLoggingOn whether to log files
     */
    public static void setFileLoggingOn(boolean fileLoggingOn) {
        Logger.fileLoggingOn = fileLoggingOn;
    }

    /**
     * Returns whether
     * {@link Logger#log(edu.ata.user.Logger.Urgency, java.lang.String)} is
     * automatically sending messages to the log file (file:///log.txt). By
     * default, is on.
     *
     * @return whether logs are being saved to the log file
     */
    public static boolean isFileLoggingOn() {
        return fileLoggingOn;
    }
}
