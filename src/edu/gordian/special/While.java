package edu.gordian.special;

import edu.gordian.Gordian;
import edu.wpi.first.wpilibj.networktables2.util.List;

public final class While implements Special {

    private final Gordian gordian;
    private final String literal;
    private final List list = new List();

    public While(Gordian gordian, String literal) {
        this.gordian = gordian;
        this.literal = literal;
    }

    public void add(String instruction) {
        list.add(instruction);
    }

    public void run() {
        while (gordian.convertVariable(literal).getValue().equals(Boolean.TRUE)) {
            for (int x = 0; x < list.size(); x++) {
                gordian.convertInstruction((String) list.get(x)).run();
            }
        }
    }
}
