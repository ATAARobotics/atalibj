package edu.ata.commands;

import edu.ata.subsystems.Winch;
import edu.first.identifiers.ReturnableNumber;

public final class SetWinch extends ThreadableCommand {

    public static final WinchType SPEED = new WinchType(1);
    public static final WinchType POSITION = new WinchType(2);
    private final Winch winch;
    private final WinchType type;
    private final ReturnableNumber number;
    
    public SetWinch(Winch winch, WinchType type, double number, boolean newThread) {
        this(winch, type, new ReturnableNumber.Number(number), newThread);
    }

    public SetWinch(Winch winch, WinchType type, ReturnableNumber number, boolean newThread) {
        super(newThread);
        this.winch = winch;
        this.type = type;
        this.number = number;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if (type.equals(SPEED)) {
                    winch.move(number.get());
                } else if (type.equals(POSITION)) {
                    winch.set(number.get());
                }
            }
        };
    }

    public static final class WinchType {

        private final int type;

        private WinchType(int type) {
            this.type = type;
        }

        public int hashCode() {
            int hash = 5;
            hash = 71 * hash + this.type;
            return hash;
        }

        public boolean equals(Object obj) {
            return (obj instanceof WinchType) ? (type == ((WinchType) obj).type) : false;
        }
    }
}