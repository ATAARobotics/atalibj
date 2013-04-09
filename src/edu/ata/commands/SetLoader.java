package edu.ata.commands;

import edu.ata.subsystems.Loader;

public final class SetLoader extends ThreadableCommand {

    public static final LoaderType IN = new LoaderType(1);
    public static final LoaderType OUT = new LoaderType(2);
    public static final LoaderType FIRE = new LoaderType(3);
    private final Loader loader;
    private final LoaderType loaderType;

    public SetLoader(Loader loader, LoaderType loaderType, boolean newThread) {
        super(newThread);
        this.loader = loader;
        this.loaderType = loaderType;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if (loaderType.equals(IN)) {
                    loader.setIn();
                } else if (loaderType.equals(OUT)) {
                    loader.setOut();
                } else if (loaderType.equals(FIRE)) {
                    loader.fire();
                }
            }
        };
    }

    public static final class LoaderType {

        private final int type;

        private LoaderType(int type) {
            this.type = type;
        }

        public int hashCode() {
            int hash = 7;
            hash = 31 * hash + this.type;
            return hash;
        }

        public boolean equals(Object obj) {
            return (obj instanceof LoaderType) ? (type == ((LoaderType) obj).type) : false;
        }
    }
}