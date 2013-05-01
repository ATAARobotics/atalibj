package edu.ata.commands;

import edu.ata.subsystems.BitchBar;

public final class SetBitchBar extends ThreadableCommand {

    public static final BitchBarType IN = new BitchBarType(1);
    public static final BitchBarType OUT = new BitchBarType(2);
    public static final BitchBarType SWITCH = new BitchBarType(3);
    private final BitchBar bitchBar;
    private final BitchBarType bitchBarType;

    public SetBitchBar(BitchBar bitchBar, BitchBarType bitchBarType, boolean newThread) {
        super(newThread);
        this.bitchBar = bitchBar;
        this.bitchBarType = bitchBarType;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if(bitchBarType.equals(IN)) {
                    bitchBar.setIn();
                } else if (bitchBarType.equals(OUT)) {
                    bitchBar.setOut();
                } else if (bitchBarType.equals(SWITCH)) {
                    bitchBar.switchPosition();
                }
            }
        };
    }

    public final static class BitchBarType {

        private final int type;

        private BitchBarType(int type) {
            this.type = type;
        }

        public int hashCode() {
            int hash = 5;
            hash = 59 * hash + this.type;
            return hash;
        }

        public boolean equals(Object obj) {
            return (obj instanceof BitchBarType) ? (type == ((BitchBarType) obj).type) : false;
        }
    }
}