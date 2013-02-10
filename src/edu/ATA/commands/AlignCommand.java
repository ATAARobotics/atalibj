package edu.ATA.commands;

import edu.ATA.bindings.CommandBind;
import edu.ATA.module.subsystems.AlignmentSystem;

/**
 * This is the command class for the alignment pistons
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class AlignCommand implements CommandBind {

    /**
     * The type for all three alignment pistons being collapsed.
     */
    public static final Type COLLAPSE = new Type(Type.COLLAPSE);
    /**
     * The type to turn the short piston on and the longs piston off.
     */
    public static final Type SHORT = new Type(Type.COLLAPSE);
    /**
     * The type to turn the short piston off and to turn the long piston off.
     */
    public static final Type LONG = new Type(Type.LONG);
    private final AlignmentSystem alignmentSystem;
    private final Type type;

    /**
     *
     * @param alignmentSystem
     * @param type
     */
    public AlignCommand(AlignmentSystem alignmentSystem, Type type) {
        this.alignmentSystem = alignmentSystem;
        this.type = type;
    }

    public void run() {
        if (type == COLLAPSE) {
            alignmentSystem.collapse();
        } else if (type == SHORT) {
            alignmentSystem.setShort();
        } else if (type == LONG) {
            alignmentSystem.setLong();
        }
    }

    /**
     * This class is meant to include the different the different configurations
     * of the alignment system.
     */
    public static final class Type {

        private static final int COLLAPSE = 1, SHORT = 2, LONG = 3;
        private final int type;

        private Type(int type) {
            this.type = type;
        }
    }
}
