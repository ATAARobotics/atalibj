package edu.ata.commands;

import edu.ata.subsystems.Compressor;

public final class SetCompressor extends ThreadableCommand {

    public static final CompressType ON = new CompressType(1);
    public static final CompressType OFF = new CompressType(2);
    public static final CompressType SWITCH = new CompressType(3);
    private final Compressor compressor;
    private final CompressType type;

    public SetCompressor(Compressor compressor, CompressType type, boolean newThread) {
        super(newThread);
        this.compressor = compressor;
        this.type = type;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if(type.equals(ON)) {
                    compressor.start();
                } else if (type.equals(OFF)) {
                    compressor.stop();
                } else if (type.equals(SWITCH)) {
                    if(compressor.isStarted()) {
                        compressor.stop();
                    } else {
                        compressor.start();
                    }
                }
            }
        };
    }

    public static final class CompressType {

        private final int type;

        private CompressType(int type) {
            this.type = type;
        }

        public int hashCode() {
            int hash = 5;
            hash = 59 * hash + this.type;
            return hash;
        }

        public boolean equals(Object obj) {
            return (obj instanceof CompressType) ? (type == ((CompressType) obj).type) : false;
        }
    }
}