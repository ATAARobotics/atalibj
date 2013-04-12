package edu.ata.commands;

import edu.ata.subsystems.AlignmentSystem;

public final class SetAlignment extends ThreadableCommand {

    public static final AlignmentType IN = new AlignmentType(1);
    public static final AlignmentType OUT = new AlignmentType(2);
    public static final AlignmentType LEFT = new AlignmentType(3);
    public static final AlignmentType RIGHT = new AlignmentType(4);
    public static final AlignmentType SWITCH = new AlignmentType(5);
    private final AlignmentSystem alignmentSystem;
    private final AlignmentType alignmentType;

    public SetAlignment(AlignmentSystem alignmentSystem, AlignmentType alignmentType, boolean newThread) {
        super(newThread);
        this.alignmentSystem = alignmentSystem;
        this.alignmentType = alignmentType;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if(alignmentType.equals(IN)) {
                    alignmentSystem.setIn();
                } else if (alignmentType.equals(OUT)) {
                    alignmentSystem.setOut();
                } else if (alignmentType.equals(LEFT)) {
                    alignmentSystem.setLeft();
                } else if (alignmentType.equals(RIGHT)) {
                    alignmentSystem.setRight();
                } else if (alignmentType.equals(SWITCH)) {
                    alignmentSystem.switchPosition();
                }
            }
        };
    }

    public final static class AlignmentType {

        private final int value;

        private AlignmentType(int value) {
            this.value = value;
        }

        public int hashCode() {
            int hash = 3;
            hash = 41 * hash + this.value;
            return hash;
        }

        public boolean equals(Object obj) {
            return (obj instanceof AlignmentType)
                    ? (value == ((AlignmentType) obj).value)
                    : false;
        }
    }
}