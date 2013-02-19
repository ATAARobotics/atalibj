package edu.gordian.special;

import edu.gordian.Gordian;

public final class While implements Special {

    private final Gordian gordian;
    private final String literal;
    private String script = "";

    public While(Gordian gordian, String literal) {
        this.gordian = gordian;
        this.literal = literal;
    }

    public void add(String instruction) {
        script += instruction + ";";
    }

    public void run() {
        while (gordian.convertVariable(literal).getValue().equals(Boolean.TRUE)) {
            new Gordian(gordian, script).run();
        }
    }
}
