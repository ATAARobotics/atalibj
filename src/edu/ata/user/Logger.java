package edu.ata.user;

import com.sun.squawk.microedition.io.FileConnection;
import edu.ata.main.DriverstationInfo;
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
    private static FileConnection logFile;

    /**
     * Class representing the urgency of the log. The reason for this
     * implementation is to ensure that logging does not encounter an error when
     * looking at its urgency. (Ex. int could be out of range, etc.) This keeps
     * the only possible values to be pre-approved by this class. (No errors
     * when logging)
     *
     * <p> <b> Null urgency objects are possible, but should throw an error
     * anyways. </b>
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
     * variables in the {@link Urgency} class.
     * @param msg Message to send to the user.
     */
    public static void log(Urgency urgency, String msg) {
        String usrMsg = null;
        if (urgency == Urgency.URGENT) {
            usrMsg = "Urgent@" + DriverstationInfo.getMatchTime() + "- " + msg;
            displayLCDMessage(usrMsg);
        } else if (urgency == Urgency.WARNING) {
            usrMsg = "Warning@" + DriverstationInfo.getMatchTime() + "- " + msg;
            displayLCDMessage(usrMsg);
        } else if (urgency == Urgency.USERMESSAGE) {
            usrMsg = "User Message - " + msg;
            displayLCDMessage(usrMsg);
        } else if (urgency == Urgency.STATUSREPORT) {
            usrMsg = "Status@" + DriverstationInfo.getMatchTime() + "- " + msg;
        } else if (urgency == Urgency.LOG) {
            usrMsg = "Log@" + DriverstationInfo.getMatchTime() + "- " + msg;
        }
        System.out.print(usrMsg.concat("\n"));
        try {
            if (fileLoggingOn) {
                logFile(msg.concat("\n"));
            }
        } catch (IOException ex) {
            System.err.println("!!!ERROR WHILE WRITING TO LOG FILE!!!\n" + ex.getMessage());
        }
    }

    /**
     * Logs a message to the log file.
     *
     * @param msg message to write
     * @throws IOException thrown when writing throws an error
     */
    public static void logFile(String msg) throws IOException {
        if (logFile == null) {
            logFile = (FileConnection) Connector.open(PATH, Connector.READ_WRITE);
        }
        appendToFile(msg, logFile);
    }

    /**
     * Adds a new part to a text file.
     *
     * @param msg message to add
     * @param fileConnection connection to the file
     * @throws IOException thrown when problem writing occurs
     */
    public static void appendToFile(String msg, FileConnection fileConnection) throws IOException {
        fileConnection.create();
        DataOutputStream outputStream = fileConnection.openDataOutputStream();
        byte[] data = msg.getBytes();
        try {
            // Need to test to find out if offset should be saved to append
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
        if (logFile == null) {
            logFile = (FileConnection) Connector.open(PATH, Connector.READ_WRITE);
        }
        return getTextFromFile(logFile);
    }

    /**
     * Returns text from a file, using {@link FileConnection}.
     *
     * @param fileConnection connection to the file
     * @return string representation of contents of the file
     * @throws IOException thrown when file occurs a problem
     */
    public static String getTextFromFile(FileConnection fileConnection) throws IOException {
        fileConnection.create();
        DataInputStream inputStream = fileConnection.openDataInputStream();
        String tmp = "";
        byte cur;
        try {
            while ((cur = (byte) inputStream.read()) > 0) {
                tmp += (char) cur;
            }
            return tmp;
        } finally {
            inputStream.close();
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
     * <p> As of 2012, the DriverStation has a limit of 20 characters.
     *
     * @param msg message to display to the user
     */
    public static void displayLCDMessage(String msg) {
        DriverStationLCD.Line line;
        switch (lineNum) {
            case (1):
                line = DriverStationLCD.Line.kUser1;
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
                line = DriverStationLCD.Line.kUser1;
        }
        DriverStationLCD.getInstance().println(line, lineNum, msg);
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
