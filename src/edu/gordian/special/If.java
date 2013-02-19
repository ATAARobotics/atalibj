package edu.gordian.special;

import edu.gordian.Gordian;
import edu.wpi.first.wpilibj.networktables2.util.List;

public final class If implements Special {

    private final Gordian gordian;
    private final String literal;
    private final List list = new List();
    private boolean ran = false;

    public If(Gordian gordian, String literal) {
        this.gordian = gordian;
        this.literal = literal;
    }

    public void add(String instruction) {
        list.add(instruction);
    }

    public boolean ran() {
        return ran;
    }

    public void run() {
        if (gordian.convertVariable(literal).getValue().equals(Boolean.TRUE)) {
            ran = true;
            for (int x = 0; x < list.size(); x++) {
                gordian.convertInstruction((String) list.get(x)).run();
            }
        } else {
            ran = false;
        }
    }
}
