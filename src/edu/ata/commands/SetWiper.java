package edu.ata.commands;

import edu.ata.subsystems.WindshieldWiper;
import edu.first.identifiers.ReturnableNumber;

public final class SetWiper extends ThreadableCommand {

    public static final WiperType SPEED = new WiperType(1);
    private final WindshieldWiper windshieldWiper;
    private final WiperType type;
    private final ReturnableNumber number;

    public SetWiper(WindshieldWiper windshieldWiper, WiperType type, double number, boolean newThread) {
        this(windshieldWiper, type, new ReturnableNumber.Number(number), newThread);
    }

    public SetWiper(WindshieldWiper windshieldWiper, WiperType type, ReturnableNumber number, boolean newThread) {
        super(newThread);
        this.windshieldWiper = windshieldWiper;
        this.type = type;
        this.number = number;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if (type.equals(SPEED)) {
                    windshieldWiper.setSpeed(number.get());
                }
            }
        };
    }

    public static final class WiperType {

        private final int type;

        private WiperType(int type) {
            this.type = type;
        }

        public int hashCode() {
            int hash = 3;
            hash = 19 * hash + this.type;
            return hash;
        }

        public boolean equals(Object obj) {
            return (obj instanceof WiperType) ? (type == ((WiperType) obj).type) : false;
        }
    }
}