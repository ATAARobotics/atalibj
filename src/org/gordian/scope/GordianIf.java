package org.gordian.scope;

import edu.first.util.Strings;
import edu.first.util.list.ArrayList;
import edu.first.util.list.Iterator;
import edu.first.util.list.List;
import org.gordian.value.GordianBoolean;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianIf extends GordianScope {

    private final List conditions = new ArrayList();
    private final List instructions = new ArrayList();

    public static void run(GordianScope scope, String s) {
        GordianIf i = new GordianIf(scope);
        build(i, s);

        i.run();
    }

    private static void build(GordianIf i, String s) {
        if (s.startsWith("if")) {
            i.addIf(s.substring(3, s.substring(0, s.indexOf(";")).lastIndexOf(')')),
                    s.substring(s.indexOf(";") + 1, nextToken(s)));
            if (nextToken(s) != s.length() - 1) {
                build(i, s.substring(nextToken(s)));
            }
        } else if (s.startsWith("}elseif")) {
            i.addIf(s.substring(8, s.substring(0, s.indexOf(";")).lastIndexOf(')')),
                    s.substring(s.indexOf(";") + 1, nextToken(s)));
            if (nextToken(s) != s.length() - 1) {
                build(i, s.substring(nextToken(s)));
            }
        } else if (s.startsWith("}else{")) {
            i.addElse(s.substring(7, nextToken(s)));
        }
    }

    private static int nextToken(String s) {
        char[] c = s.toCharArray();
        int track = 0;
        for (int x = s.indexOf("{"); x < c.length; x++) {
            if (c[x] == '{') {
                track++;
            } else if (c[x] == '}') {
                track--;
            }
            
            if (track == 0) {
                return x;
            }
        }
        return -1;
    }

    public GordianIf(GordianScope scope) {
        super(scope);
    }

    public void addIf(String condition, String instr) {
        conditions.add(condition);
        instructions.add(instr);
    }

    public void addElse(String instr) {
        conditions.add("true");
        instructions.add(instr);
    }

    public void run() {
        Iterator i = conditions.iterator();
        Iterator x = instructions.iterator();
        while (i.hasNext()) {
            String cond = (String) i.next();
            String inst = (String) x.next();
            try {
                if (((GordianBoolean) toObject(cond)).getValue()) {
                    run(inst);
                    return;
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                throw new RuntimeException("If statement failed. Condition \"" + cond + "\" could not be calculated");
            }
        }
    }
}
