package edu.gordian.special;

import edu.gordian.Gordian;
import edu.wpi.first.wpilibj.networktables2.util.List;

public final class For implements Special {

    private final Gordian gordian;
    private final int loops;
    private final List list = new List();

    public For(Gordian gordian, int loops) {
        this.gordian = gordian;
        this.loops = loops;
    }

    public void add(String instruction) {
        list.add(instruction);
    }

    public void run() {
        for (int x = 0; x < loops; x++) {
            for (int i = 0; i < list.size(); i++) {
                gordian.convertInstruction((String) list.get(i)).run();
            }
        }
    }
}
