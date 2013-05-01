package edu.gordian.special;

import edu.gordian.Gordian;

public final class For implements Special {

    private final Gordian gordian;
    private final int loops;
    private String script = "";

    public For(Gordian gordian, int loops) {
        this.gordian = gordian;
        this.loops = loops;
    }

    public void add(String instruction) {
        script += instruction + ";";
    }

    public void run() {
        for (int x = 0; x < loops; x++) {
            new Gordian(gordian, script).run();
        }
    }
}
