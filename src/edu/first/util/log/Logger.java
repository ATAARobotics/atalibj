package edu.first.util.log;

import edu.first.util.Arrays;
import edu.first.util.DriverstationInfo;
import edu.first.util.Enum;
import edu.first.util.File;
import edu.first.util.TextFiles;
import edu.first.util.list.ArrayList;
import edu.first.util.list.Iterator;
import edu.first.util.list.List;
import edu.first.util.list.SafeArrayList;
import edu.wpi.first.wpilibj.DriverStationLCD;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * The logging abstraction that allows users to log messages at different
 * levels. This class allows the programmer to add custom logs that accompany
 * the console to tell the user about things.
 *
 * @since June 10 13
 * @author Joel Gallant
 */
public final class Logger {

    /**
     * The log that sends messages to the console. This log object is added to
     * every {@code Logger} by default, so there is no need to do so yourself.
     */
    public static final Log CONSOLE_LOG = new Log() {
        public void send(String msg) {
            System.out.println(msg);
        }
    };
    private static final List DEFAULT_LOGS = Arrays.asList(new Object[] {
        CONSOLE_LOG
    });
    private static final Hashtable loggers = new Hashtable();
    private static int lineNum = 1;
    private final Class origin;
    private final List logs = new SafeArrayList(DEFAULT_LOGS, Log.class);

    /**
     * Returns the current logger for the origin. All loggers with the same
     * origin are technically the same logger.
     *
     * @param origin class that the logger is sending messages from
     * @return an instance of {@code Logger} that can send messages from the
     * origin
     */
    public static Logger getLogger(Class origin) {
        if (!loggers.containsKey(origin)) {
            loggers.put(origin, new Logger(origin));
        }
        return (Logger) loggers.get(origin);
    }

    /**
     * Returns the current logger for the class of the given object. Typically,
     * you can use it like this:
     * <pre>
     * getLogger(this);
     * </pre> It will deduce the class of the {@code this} object.
     *
     * @param o object whose class is the origin
     * @return an instance of {@code Logger} that can send messages from the
     * origin
     */
    public static Logger getLogger(Object o) {
        return getLogger(o.getClass());
    }

    /**
     * Adds the {@code log} to every logger, regardless of origin.
     *
     * @param log the output to send messages to
     * @see #addLog(edu.first.util.log.Logger.Log)
     */
    public static void addLogToAll(Log log) {
        Enumeration e = loggers.elements();
        while (e.hasMoreElements()) {
            ((Logger) e.nextElement()).addLog(log);
        }
    }

    /**
     * Displays the message on the next line on the DriverStation LCD screen.
     * This method rotates through every line, and displays the message on the
     * line after the previous message.
     *
     * @throws NullPointerException when message is null
     * @param msg message to display
     */
    public static void displayLCDMessage(String msg) {
        if (msg == null) {
            throw new NullPointerException();
        }
        if (msg.length() > DriverStationLCD.kLineLength) {
            displayLCDMessage(msg.substring(0, DriverStationLCD.kLineLength));
            displayLCDMessage(msg.substring(DriverStationLCD.kLineLength));
            return;
        }
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
        StringBuffer buf = new StringBuffer(msg);
        while (buf.length() < DriverStationLCD.kLineLength) {
            buf.append(' ');
        }
        DriverStationLCD.getInstance().println(line, 1, buf);
        DriverStationLCD.getInstance().updateLCD();
        if (++lineNum > 6) {
            lineNum = 1;
        }
    }

    // Use static factory instead
    private Logger(Class origin) {
        this.origin = origin;
    }

    /**
     * Adds a new output for log messages to be sent to. Previously given logs
     * will still be sent to. {@link #CONSOLE_LOG} is already added by default.
     *
     * @param log the output to send messages to
     */
    public void addLog(Log log) {
        logs.add(log);
    }

    /**
     * Sends a debug message. This only sends the message to the known logs.
     *
     * @see #addLog(edu.first.util.log.Logger.Log)
     * @param msg message to send user
     */
    public void debug(String msg) {
        send(new Message(Level.DEBUG, origin, msg));
    }

    /**
     * Sends an info message. This only sends the message to the known logs.
     *
     * @see #addLog(edu.first.util.log.Logger.Log)
     * @param msg message to send user
     */
    public void info(String msg) {
        send(new Message(Level.INFO, origin, msg));
    }

    /**
     * Sends a warning message. This sends the message to the known logs and to
     * the DriverStation LCD screen.
     *
     * @see #addLog(edu.first.util.log.Logger.Log)
     * @see #displayLCDMessage(java.lang.String)
     * @param msg message to send user
     */
    public void warn(String msg) {
        send(new Message(Level.WARN, origin, msg));
        displayLCDMessage(msg);
    }

    /**
     * Sends an error message. This sends the message to the known logs, to the
     * DriverStation LCD screen and prints a stack trace for the error.
     *
     * @see #addLog(edu.first.util.log.Logger.Log)
     * @see #displayLCDMessage(java.lang.String)
     * @param msg message to send user
     * @param error the error to print stack trace of
     */
    public void error(String msg, Throwable error) {
        send(new Message(Level.ERROR, origin, msg));
        displayLCDMessage(msg);
        error.printStackTrace();
    }

    /**
     * Sends a fatal message. This sends the message to the known logs, to the
     * DriverStation LCD screen and prints a stack trace for the error.
     *
     * <p> This method <b>QUITS</b> the entire program with an error code of
     * 8001. (after logging the problem)
     *
     * @see #addLog(edu.first.util.log.Logger.Log)
     * @see #displayLCDMessage(java.lang.String)
     * @param msg message to send user
     * @param error the error to print stack trace of
     */
    public void fatal(String msg, Throwable error) {
        send(new Message(Level.FATAL, origin, msg));
        displayLCDMessage(msg);
        error.printStackTrace();
        System.exit(8001);
    }

    private void send(Message msg) {
        Iterator i = logs.iterator();
        while (i.hasNext()) {
            ((Log) i.next()).send(msg.toString());
        }
    }

    /**
     * A log that sends messages to the user in some way. Usually this is a
     * {@link #CONSOLE_LOG console}, {@link FileLog text file} or something
     * similar.
     */
    public static interface Log {

        /**
         * Sends the message to the log.
         *
         * @param msg message to send to the user
         */
        public void send(String msg);
    }

    /**
     * An implementation of {@link Log} that logs to a text file.
     */
    public static final class FileLog implements Log {

        private final File file;

        /**
         * Constructs the log with the file to log to.
         *
         * @param file which file messages should be logged to
         */
        public FileLog(File file) {
            this.file = file;
        }

        /**
         * {@inheritDoc}
         *
         * Inserts the message at the very end of the file.
         */
        public void send(String msg) {
            TextFiles.appendToNewLine(file, msg);
        }
    }

    private static final class Level extends Enum {

        public static final Level DEBUG = new Level("DEBUG");
        public static final Level INFO = new Level("INFO");
        public static final Level WARN = new Level("WARN");
        public static final Level ERROR = new Level("ERROR");
        public static final Level FATAL = new Level("FATAL");

        private Level(String name) {
            super(name);
        }
    }

    private static final class Message {

        private final Level l;
        private final Class o;
        private final String m;

        Message(Level l, Class o, String m) {
            this.l = l;
            this.o = o;
            this.m = m;
        }

        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(DriverstationInfo.getGamePeriod()).append(" - ");
            buffer.append('[').append(l).append("] ");
            buffer.append('@').append(o.getName()).append(' ');
            buffer.append(" - ").append(m);
            return buffer.toString();
        }
    }
}
