package edu.gordian.special;

import edu.gordian.Gordian;

public final class If implements Special {

    private final Gordian gordian;
    private final String literal;
    private String script = "";
    private boolean ran = false;

    public If(Gordian gordian, String literal) {
        this.gordian = gordian;
        this.literal = literal;
    }

    public void add(String instruction) {
        script += instruction + ";";
    }

    public boolean ran() {
        return ran;
    }

    public void run() {
        if (gordian.convertVariable(literal).getValue().equals(Boolean.TRUE)) {
            ran = true;
            new Gordian(gordian, script).run();
        } else {
            ran = false;
        }
    }
}
