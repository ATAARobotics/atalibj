package edu.first.util.log;

import edu.first.main.Constants;
import edu.wpi.first.wpilibj.DriverStationLCD;

public final class Logging implements Constants {

    private static final DriverStationLCD LCD = DriverStationLCD.getInstance();
    private static final String[] buf = new String[6];
    private static final DashboardSender[] senders = new DashboardSender[6];

    public static void logToConsole(String msg) {
        System.out.println(msg);
    }

    public static void setDashboardLine(DashboardSender sender) {
        senders[sender.index] = sender;
    }

    public static void updateDashboard() {
        boolean updated = false;
        for (int x = 0; x < senders.length; x++) {
            String msg = senders[x] != null ? senders[x].getMessage() : "";
            if (!msg.equals(buf[x])) {
                sendDashboard(x, msg);
                updated = true;
                buf[x] = msg;
            }
        }
        if (updated) {
            LCD.updateLCD();
        }
    }

    private static void sendDashboard(int index, String msg) {
        DriverStationLCD.Line line;
        switch (index) {
            case (0):
                line = DriverStationLCD.Line.kUser1;
                break;
            case (1):
                line = DriverStationLCD.Line.kUser2;
                break;
            case (2):
                line = DriverStationLCD.Line.kUser3;
                break;
            case (3):
                line = DriverStationLCD.Line.kUser4;
                break;
            case (4):
                line = DriverStationLCD.Line.kUser5;
                break;
            case (5):
                line = DriverStationLCD.Line.kUser6;
                break;
            default:
                line = DriverStationLCD.Line.kUser1;
        }
        if (msg.length() < 21) {
            StringBuffer b = new StringBuffer(msg);
            while (b.length() < 21) {
                b.append(' ');
            }
            msg = b.toString();
        }
        LCD.println(line, 1, msg);
    }

    public static final class Logger extends DashboardSender {

        private String msg;

        public Logger(int index) {
            super(index);
        }

        public String getMessage() {
            return msg;
        }

        public void log(String msg) {
            this.msg = msg;
            Logging.logToConsole(msg);
        }
    }

    public static abstract class DashboardSender {

        private final int index;

        public DashboardSender(int index) {
            this.index = index;
        }

        public abstract String getMessage();
    }
}
