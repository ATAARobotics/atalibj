package edu.ATA.commands;

import edu.ATA.twolf.subsystems.AlignmentSystem;

/**
 * This is the command class for the alignment pistons
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class AlignCommand extends ThreadableCommand {

    /**
     * The type for all three alignment pistons being collapsed.
     */
    public static final AlignType COLLAPSE = new AlignType(AlignType.COLLAPSE);
    public static final AlignType EXTEND = new AlignType(AlignType.EXTEND);
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
                } else if (type.type == EXTEND.type) {
                    alignmentSystem.extend();
                } 
            }
        };
    }

    /**
     * This class is meant to include the different the different configurations
     * of the alignment system.
     */
    public static final class AlignType {

        private static final int COLLAPSE = 1, EXTEND = 2;
        private final int type;

        private AlignType(int type) {
            this.type = type;
        }
    }
}
