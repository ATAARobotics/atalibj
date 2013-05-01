package edu.ata.commands;

import edu.ata.subsystems.GearShifters;

public final class SetGear extends ThreadableCommand {

    public static final GearType FIRST = new GearType(1);
    public static final GearType SECOND = new GearType(2);
    public static final GearType SWITCH = new GearType(3);
    private final GearShifters gearShifters;
    private final GearType gearType;

    public SetGear(GearShifters gearShifters, GearType gearType, boolean newThread) {
        super(newThread);
        this.gearShifters = gearShifters;
        this.gearType = gearType;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if (gearType.equals(FIRST)) {
                    gearShifters.firstGear();
                } else if (gearType.equals(SECOND)) {
                    gearShifters.secondGear();
                } else if (gearType.equals(SWITCH)) {
                    gearShifters.switchGears();
                }
            }
        };
    }

    public static final class GearType {

        private final int type;

        private GearType(int type) {
            this.type = type;
        }

        public int hashCode() {
            int hash = 3;
            hash = 37 * hash + this.type;
            return hash;
        }

        public boolean equals(Object obj) {
            return (obj instanceof GearType) ? (type == ((GearType) obj).type) : false;
        }
    }
}