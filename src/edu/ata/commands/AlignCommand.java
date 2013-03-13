package edu.ata.commands;

import edu.ata.subsystems.AlignmentSystem;

/**
 * Command to align pistons.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class AlignCommand extends ThreadableCommand {

    public static final AlignType COLLAPSE = new AlignType(AlignType.COLLAPSE);
    public static final AlignType EXTEND = new AlignType(AlignType.EXTEND);
    public static final AlignType RIGHT = new AlignType(AlignType.RIGHT);
    public static final AlignType LEFT = new AlignType(AlignType.LEFT);
    private final AlignmentSystem alignmentSystem;
    private final AlignType type;

    /**
     * Constructs the command with the alignment system to use and how to align.
     *
     * @param alignmentSystem system to align robot
     * @param type kind of alignment
     * @param newThread if command should run in new thread
     */
    public AlignCommand(AlignmentSystem alignmentSystem, AlignType type, boolean newThread) {
        super(newThread);
        this.alignmentSystem = alignmentSystem;
        this.type = type;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if (type.type == COLLAPSE.type) {
                    alignmentSystem.setIn();
                } else if (type.type == EXTEND.type) {
                    alignmentSystem.setOut();
                } else if (type.type == RIGHT.type) {
                    alignmentSystem.setRight();
                } else if (type.type == LEFT.type) {
                    alignmentSystem.setLeft();
                }
            }
        };
    }

    /**
     * This class is meant to include the different the different configurations
     * of the alignment system.
     */
    public static final class AlignType {

        private static final int COLLAPSE = 1, EXTEND = 2, RIGHT = 3, LEFT = 4;
        private final int type;

        private AlignType(int type) {
            this.type = type;
        }
    }
}
