package edu.gordian.scopes;

import edu.first.util.list.ArrayList;
import edu.first.util.list.List;
import edu.gordian.Strings;
import edu.gordian.values.gordian.GordianBoolean;

final class If extends Scope {

    If(Scope scope) {
        super(scope);
    }

    public void run(String script) throws Exception {
        script = "#" + script.substring(2);
        List conditions = new ArrayList();
        List run = new ArrayList();

        String[] e = Strings.split(script, ';');
        StringBuffer buffer = new StringBuffer(e[0] + ';');
        int scopes = 0;
        for (int x = 1; x < e.length; x++) {
            if (e[x].startsWith("if")) {
                scopes++;
            } else if (e[x].startsWith("end")) {
                scopes--;
            }

            if (scopes == 0 && e[x].startsWith("elseif")) {
                buffer.append('#').append(e[x].substring(6));
            } else if (scopes == 0 && e[x].startsWith("else")) {
                buffer.append("#(true)").append(e[x].substring(4));
            } else {
                buffer.append(e[x]);
            }

            buffer.append(';');
        }
        script = buffer.toString();

        String[] conds = Strings.split(script, "#(");
        for (int x = 1; x < conds.length; x++) {
            conditions.add(conds[x].substring(0, conds[x].substring(0, conds[x].indexOf(';')).lastIndexOf(')')));
            run.add(conds[x].substring(conds[x].substring(0, conds[x].indexOf(';')).lastIndexOf(')') + 2));
        }

        for (int x = 0; x < conditions.size(); x++) {
            if (((GordianBoolean) toValue((String) conditions.get(x))).booleanValue()) {
                super.run((String) run.get(x));
                return;
            }
        }
    }
}
