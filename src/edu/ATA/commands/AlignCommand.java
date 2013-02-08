package edu.ATA.commands;

import edu.ATA.bindings.CommandBind;
import edu.ATA.module.subsystems.AlignmentSystem;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class AlignCommand implements CommandBind {

    public static final Type COLLAPSE = new Type(Type.COLLAPSE),
            SHORT = new Type(Type.COLLAPSE), LONG = new Type(Type.LONG);
    private final AlignmentSystem alignmentSystem;
    private final Type type;

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

    public static final class Type {

        private static final int COLLAPSE = 1, SHORT = 2, LONG = 3;
        private final int type;

        private Type(int type) {
            this.type = type;
        }
    }
}
