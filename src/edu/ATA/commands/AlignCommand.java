package edu.ATA.commands;

import edu.ATA.twolf.subsystems.AlignmentSystem;

/**
 * This is the command class for the alignment pistons
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class AlignCommand extends ThreadableCommand {

    /**
     * The type for all three alignment pistons being collapsed.
     */
    public static final AlignType COLLAPSE = new AlignType(AlignType.COLLAPSE);
    /**
     * The type to turn the short piston on and the longs piston off.
     */
    public static final AlignType SHORT = new AlignType(AlignType.SHORT);
    /**
     * The type to turn the short piston off and to turn the long piston off.
     */
    public static final AlignType LONG = new AlignType(AlignType.LONG);
    private final AlignmentSystem alignmentSystem;
    private final AlignType type;

    public AlignCommand(AlignmentSystem alignmentSystem, AlignType type, boolean newThread) {
        super(newThread);
        this.alignmentSystem = alignmentSystem;
        this.type = type;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if (type.type == COLLAPSE.type) {
                    alignmentSystem.collapse();
                } else if (type.type == SHORT.type) {
                    alignmentSystem.setShort();
                } else if (type.type == LONG.type) {
                    alignmentSystem.setLong();
                }
            }
        };
    }

    /**
     * This class is meant to include the different the different configurations
     * of the alignment system.
     */
    public static final class AlignType {

        private static final int COLLAPSE = 1, SHORT = 2, LONG = 3;
        private final int type;

        private AlignType(int type) {
            this.type = type;
        }
    }
}
