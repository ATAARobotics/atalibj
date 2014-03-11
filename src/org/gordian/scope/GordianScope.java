package org.gordian.scope;

import api.gordian.Arguments;
import api.gordian.Class;
import api.gordian.Object;
import api.gordian.Scope;
import api.gordian.methods.Method;
import api.gordian.methods.ValueReturned;
import api.gordian.storage.InternalNotFoundException;
import api.gordian.storage.Methods;
import api.gordian.storage.Variables;
import edu.first.util.Strings;
import edu.first.util.list.ArrayList;
import edu.first.util.list.Collections;
import edu.first.util.list.Iterator;
import edu.first.util.list.List;
import org.gordian.storage.GordianMethods;
import org.gordian.storage.GordianVariables;
import org.gordian.value.GordianBoolean;
import org.gordian.value.GordianList;
import org.gordian.value.GordianNumber;
import org.gordian.value.GordianString;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianScope implements Scope {

    public static final String[] keywords = {"new", "del", "return"};
    public static final List operations = Collections.asList(new Operator[]{
        new Addition(), new Subtraction(), new Multiplication(), new Division(), new Modulus()
    });

    public static boolean isInstruction(String s) {
        // All instructions will have one semi-colon at the end. No other semi-colons will be there.
        return s.indexOf(';') > 0
                && Strings.allIndexesOf(s, ';').length == 1
                && s.endsWith(";")
                && !Strings.contains(s, '{') && !Strings.contains(s, '}');
    }

    public static boolean isBlock(String s) {
        // Requirement for a block is for there to be matching { and }.
        // Pre-block cannot contain separete instruction
        // It is also only a real block if all is contained within a pair of {}s.
        while (s.endsWith(";")) {
            s = s.substring(0, s.lastIndexOf(';'));
        }
        if (!(Strings.contains(s, '{') && s.endsWith("}"))
                || Strings.contains(s.substring(0, s.indexOf('{')), ';')) {
            return false;
        }
        int p = 0;
        for (int x = s.indexOf('{'); x < s.length(); x++) {
            if (s.charAt(x) == '{') {
                p++;
            } else if (s.charAt(x) == '}') {
                p--;
            }
            if (p == 0) {
                if (x == s.length() - 1
                        || (s.startsWith("if(") && s.substring(x + 1).startsWith("else"))
                        || (s.startsWith("try{") && s.substring(x + 1).startsWith("catch"))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isName(String s) {
        // Names can only contain letters and numbers, one character has to be a letter.
        if (s.length() == 0) {
            return false;
        }
        boolean containsLetter = false;
        for (int x = 0; x < s.length(); x++) {
            if (Character.isLowerCase(s.charAt(x)) || Character.isUpperCase(s.charAt(x))) {
                // letter
                containsLetter = true;
            } else if (!(s.charAt(x) > 47 && s.charAt(x) < 58)) {
                // not number
                return false;
            }
        }
        return containsLetter;
    }

    public static boolean isMethod(String s) {
        if (s.indexOf("(") > 0 && s.lastIndexOf(')') == s.length() - 1 && isName(s.substring(0, s.indexOf("(")))) {
            int c = 0;
            boolean method = false;
            for (int x = s.indexOf("("); x < s.length(); x++) {
                if (s.charAt(x) == '(') {
                    c++;
                } else if (s.charAt(x) == ')') {
                    c--;
                }
                if (c == 0) {
                    method = (x == s.length() - 1);
                    break;
                }
            }
            return method;
        } else {
            return false;
        }
    }

    public static String[] findConstructors(String s) {
        if (!isProper(s)) {
            s = clean(s);
        }

        List l = new ArrayList();
        String next = findNextConstructor(s);
        while (next != null) {
            l.add(next);
            if (s.length() > next.length()) {
                s = s.substring(next.length());
            }
            next = findNextConstructor(s);
        }

        String[] strings = new String[l.size()];
        System.arraycopy(l.toArray(), 0, strings, 0, l.size());
        return strings;
    }

    private static String findNextConstructor(String s) {
        if (!Strings.isEmpty(s) && !s.equals(";")) {
            String next = next(s);
            if (next.startsWith("defconstruct(")) {
                return next;
            } else if (s.length() > next.length()) {
                return findNextConstructor(s.substring(next.length()));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String next(String s) {
        if (!Strings.contains(s, ';')) {
            return s + ';';
        }
        if (Strings.contains(s.substring(0, s.indexOf(';')), '{')) {
            int i = 0;
            StringBuffer buffer = new StringBuffer();
            for (int x = 0; x < s.length(); x++) {
                char e = s.charAt(x);
                if (e == '{') {
                    i++;
                } else if (e == '}') {
                    i--;
                }
                buffer.append(e);
                if (Strings.contains(s.substring(x), "{")
                        && (s.substring(x).startsWith("}catch")
                        || s.substring(x).startsWith("}else"))) {
                    buffer.append(s.substring(x + 1, x + s.substring(x).indexOf('{')));
                    x = x + s.substring(x).indexOf('{') - 1;
                } else if (i == 0 && buffer.toString().indexOf("{") >= 0) {
                    // Add everything until matching bracket shows up.
                    return buffer.toString();
                }
            }
            // Bracket didn't find a match
            throw new RuntimeException("Brackets could not be matched in \n\t" + s);
        } else {
            return s.substring(0, s.indexOf(';') + 1);
        }
    }

    public static boolean isProper(String s) {
        // Tabs should be spaces
        if (Strings.contains(s, "\t") || Strings.contains(s, '\n')) {
            return false;
        }
        if (Strings.allIndexesOf(s, '{').length != Strings.allIndexesOf(s, '}').length) {
            throw new RuntimeException("Curly braces are not matched up.");
        }
        int[] i = Strings.allIndexesOf(s, ' ');
        // Assume there are no spaces
        boolean found = true;
        for (int x = 0; x < i.length; x++) {
            // There are spaces - prove me wrong!
            found = false;
            for (int k = 0; k < keywords.length; k++) {
                if (x - keywords[k].length() >= 0 && s.substring(x - keywords[k].length(), x).equals(keywords[k])) {
                    // Proven wrong - keyword proceeded the space
                    found = true;
                    break;
                }
            }
        }
        // If there were spaces, but no keyword proceeded it.
        if (!found) {
            return false;
        }

        int a[] = Strings.allIndexesOf(s, "\"");
        if (a.length % 2 != 0) {
            // Quotation marks must match - fast way to find out
            return false;
        }
        for (int x = 0; x < a.length; x++) {
            if ((x == 0 || s.charAt(a[x] - 1) != '!') && (x == s.length() - 1 || s.charAt(a[x] + 1) != '!')) {
                return false;
            }
        }
        a = Strings.allIndexesOf(s, "\'");
        if (a.length % 2 != 0) {
            // Quotation marks must match - fast way to find out
            return false;
        }
        for (int x = 0; x < a.length; x++) {
            if ((x == 0 || s.charAt(a[x] - 1) == '\\' || s.charAt(a[x] - 1) != '!')
                    && (x == s.length() - 1 || s.charAt(a[x] + 1) != '!')) {
                return false;
            }
        }

        return true;
    }

    private static String convertStrings(String s) {
        char[] c = s.toCharArray();
        StringBuffer b = new StringBuffer();

        int debug1 = -1;
        boolean i1 = false, i2 = false;
        for (int x = 0; x < c.length; x++) {
            String add = String.valueOf(c[x]);
            if (c[x] == '\'' && (x == c.length - 1 || x == 0 || (c[x - 1] != '\\' && c[x - 1] != '!' && c[x + 1] != '!'))) {
                i1 = !i1;
                if (i1) {
                    debug1 = x;
                    add = "\'!";
                } else {
                    add = "!\'";
                }
            } else if (c[x] == '\"' && (x == c.length - 1 || x == 0 || (c[x - 1] != '\\' && c[x - 1] != '!' && c[x + 1] != '!'))) {
                i2 = !i2;
                if (i2) {
                    debug1 = x;
                    add = "\'!";
                } else {
                    add = "!\'";
                }
            } else if (i1 || i2) {
                add = "@" + (int) c[x];
            }
            b.append(add);
        }

        if (i1 || i2) {
            throw new IllegalStateException("Quotes were not closed! - " + s.substring(debug1));
        }

        return b.toString();
    }

    public static String clean(String s) {
        s = Strings.replaceAll(s, '\t', ' ');
        s = Strings.replaceAll(s, '\n', ';');
        s = Strings.replaceAll(s, '{', "{;");
        s = Strings.replaceAll(s, '}', ";}");

        if (!Strings.contains(s, ";")) {
            s = s + ';';
        }

        while (Strings.contains(s, "#")) {
            String toRemove = s.substring(s.indexOf('#'), (s.substring(s.indexOf('#'))).indexOf(';') + s.indexOf('#'));
            s = Strings.replace(s, toRemove, "");
        }

        s = convertStrings(s);

        int y = s.indexOf(" ");
        while (y != -1) {
            boolean found = false;
            for (int k = 0; k < keywords.length; k++) {
                if (y - keywords[k].length() >= 0 && s.substring(y - keywords[k].length(), y).equals(keywords[k])) {
                    // Proven wrong - keyword preceeded the space
                    found = true;
                    break;
                }
            }
            if (!found) {
                s = s.substring(0, y) + s.substring(y + 1);
            }
            y += s.substring(y + 1).indexOf(" ") + 1;
            if (s.substring(y).indexOf(" ") == -1) {
                break;
            }
        }

        while (Strings.contains(s, ";;")) {
            s = Strings.replaceAll(s, ";;", ";");
        }

        return s;
    }

    public static String betweenMatch(String s, char f, char l) {
        int scope = 0;
        for (int x = 0; x < s.length(); x++) {
            if (s.charAt(x) == f) {
                scope++;
            } else if (s.charAt(x) == l) {
                scope--;
            }
            if (scope == 0 && Strings.contains(s, f) && s.indexOf(f) < x) {
                return s.substring(s.indexOf(f) + 1, x);
            }
        }
        return "";
    }

    public static int betweenMatchLast(String s, char f, char l) {
        int scope = 0;
        for (int x = 0; x < s.length(); x++) {
            if (s.charAt(x) == f) {
                scope++;
            } else if (s.charAt(x) == l) {
                scope--;
            }
            if (scope == 0 && Strings.contains(s, f) && s.indexOf(f) < x) {
                return x;
            }
        }
        return -1;
    }

    private static boolean isBetween(String s, char i, char f, char l) {
        int pos = s.lastIndexOf(i);
        int scope = 0;
        for (int x = 0; x < s.length(); x++) {
            if (s.charAt(x) == f) {
                scope++;
            } else if (s.charAt(x) == l) {
                scope--;
            }
            if (scope > 0 && Strings.contains(s, f) && s.indexOf(f) < x) {
                // in between
                if (x == pos) {
                    return true;
                }
            }
        }
        return false;
    }

    protected String[] getPureArgs(String s) {
        if (!Strings.contains(s, '(') && !Strings.contains(s, ')') && !Strings.contains(s, '[') && !Strings.contains(s, ']')) {
            return Strings.split(s, ',');
        } else {
            List l = new ArrayList();
            boolean inQuotes = false;
            int p1 = 0, p2 = 0;
            int last = 0;
            char[] d = s.toCharArray();
            for (int x = 0; x < d.length; x++) {
                if (d[x] == '(') {
                    p1++;
                } else if (d[x] == ')') {
                    p1--;
                } else if (d[x] == '[') {
                    p2++;
                } else if (d[x] == ']') {
                    p2--;
                }
                if (d[x] == ',' && !inQuotes && p1 == 0 && p2 == 0 && !Strings.isEmpty(s.substring(last, x))) {
                    l.add(s.substring(last, x));
                    last = x + 1;
                }
                if (x == d.length - 1 && !Strings.isEmpty(s.substring(last))) {
                    l.add(s.substring(last));
                }
            }
            String[] args = new String[l.size()];
            for (int x = 0; x < args.length; x++) {
                args[x] = (String) l.get(x);
            }
            return args;
        }
    }

    protected Object[] getArgs(String s) {
        return toObjects(getPureArgs(s));
    }

    private final GordianMethods methods;
    private final GordianVariables variables;
    private final Scope container;

    public GordianScope() {
        this.container = null;
        this.methods = new GordianMethods();
        this.variables = new GordianVariables();
    }

    public GordianScope(GordianScope container) {
        this.container = container;
        this.methods = new GordianMethods(container.methods);
        this.variables = new GordianVariables(container.variables);

        variables.put("container", container);
    }

    public void inheritFrom(GordianScope scope) {
        scope.methods.sendTo(methods);
        scope.variables.sendTo(variables);
    }

    public Scope container() {
        return container;
    }

    public Methods methods() {
        return methods;
    }

    public Variables variables() {
        return variables;
    }

    public void run(String s) {
        if (!isProper(s)) {
            s = clean(s);
        }
        if (isInstruction(s)) {
            toObject(s);
        } else if (isBlock(s)) {
            runBlock(s);
        } else if (!Strings.isEmpty(s) && !s.equals(";")) {
            String next = next(s);
            run(next);
            if (s.length() > next.length()) {
                run(s.substring(next.length()));
            }
        }
    }

    private Object[] toObjects(String[] s) {
        Object[] v = new Object[s.length];
        for (int x = 0; x < v.length; x++) {
            v[x] = toObject(s[x]);
        }
        return v;
    }

    public Object toObject(String s) {
        // clean up
        if (s.endsWith(";")) {
            s = s.substring(0, s.length() - 1);
        }

        // number literals
        try {
            double d = Double.parseDouble(s);
            return new GordianNumber(d);
        } catch (NumberFormatException e) {
        }
        // string literals
        if (s.startsWith("\'!") && s.endsWith("!\'")
                && s.substring(s.indexOf('\'') + 1, 1 + s.substring(1).indexOf('\'')).equals(s.substring(1, s.length() - 1))) {
            return GordianString.evaluate(s.substring(2, s.length() - 2));
        }
        // return
        if (s.startsWith("return ")) {
            throw new ValueReturned(toObject(s.substring(7)));
        }
        // delete
        if (s.startsWith("del ") && isName(s.substring(4))) {
            try {
                Object o = variables().get(s.substring(4));
                variables().remove(s.substring(4));
                return o;
            } catch (InternalNotFoundException ex) {
                throw new NullPointerException(s.substring(4) + " could not be removed");
            }
        }
        // object access deletion
        if (s.startsWith("del ")) {
            Object call;
            String a = s.substring(4);
            String before = a.substring(0, a.lastIndexOf('.'));
            String after = a.substring(a.lastIndexOf('.') + 1);

            call = toObject(before);
            try {
                Object o = call.variables().get(after);
                call.variables().remove(after);
                return o;
            } catch (InternalNotFoundException ex) {
                throw new NullPointerException(after + " could not be removed");
            }
        }

        // adjustments
        if (s.endsWith("++")) {
            s = s.substring(0, s.length() - 2) + "="
                    + (((GordianNumber) toObject(s.substring(0, s.length() - 2))).getValue() + 1);
        }
        if (s.endsWith("--")) {
            s = s.substring(0, s.length() - 2) + "="
                    + (((GordianNumber) toObject(s.substring(0, s.length() - 2))).getValue() - 1);
        }

        // shorthand declarations
        Iterator i = operations.iterator();
        while (i.hasNext()) {
            Operator o = (Operator) i.next();
            String x = o.getChar() + "=";
            if (Strings.contains(s, x) && isName(s.substring(0, s.indexOf(x)))) {
                s = s.substring(0, s.indexOf(x)) + "="
                        + s.substring(0, s.indexOf(x)) + o.getChar() + s.substring(s.indexOf(x) + 2);
            }
        }

        // declaration
        if (Strings.containsThatIsnt(s, "=", "==") && isName(s.substring(0, Strings.indexThatIsnt(s, "=", "==")))) {
            return variables().set(s.substring(0, Strings.indexThatIsnt(s, "=", "==")),
                    toObject(s.substring(Strings.indexThatIsnt(s, "=", "==") + 1)));
        }

        // object access declaration
        if (Strings.containsThatIsnt(s, "=", "==") && Strings.contains(s.substring(0, s.indexOf("=")), ".")) {
            Object call;
            String a = s.substring(0, Strings.indexThatIsnt(s, "=", "=="));
            String before = a.substring(0, a.lastIndexOf('.'));
            String after = a.substring(a.lastIndexOf('.') + 1);

            call = toObject(before);

            return call.variables().set(after, toObject(s.substring(Strings.indexThatIsnt(s, "=", "==") + 1)));
        }

        // parentheses
        if (Strings.contains(s, '(') && Strings.contains(s, ')')
                && (s.indexOf('(') == 0 || !isName("" + s.charAt(s.indexOf('(') - 1)))) {
            return toObject(
                    s.substring(0, s.indexOf('('))
                    + toObject(betweenMatch(s, '(', ')'))
                    + s.substring(betweenMatchLast(s, '(', ')') + 1));
        }

        // reversed
        if (s.startsWith("!")) {
            return new GordianBoolean(!((GordianBoolean) toObject(s.substring(1))).getValue());
        }

        // method
        if (isMethod(s)) {
            Method[] m = methods().getAll(s.substring(0, s.indexOf("(")));
            Arguments a = new Arguments(getArgs(betweenMatch(s, '(', ')')));
            for (int x = 0; x < m.length; x++) {
                if (m[x].signature().matches(a.getSignature())) {
                    return m[x].run(a);
                }
            }
            throw new RuntimeException("Appropriate method could not be found - " + s.substring(0, s.indexOf("(")));
        }

        // lists
        if (s.startsWith("[") && s.endsWith("]") && betweenMatch(s, '[', ']').equals(s.substring(1, s.length() - 1))) {
            return new GordianList(getArgs(s.substring(1, s.length() - 1)));
        }

        // calculations
        while (Strings.contains(s, "--")) {
            s = Strings.replaceAll(s, "--", "+");
        }
        while (Strings.contains(s, "++")) {
            s = Strings.replaceAll(s, "++", "+");
        }
        while (Strings.contains(s, "+-")) {
            s = Strings.replaceAll(s, "+-", "-");
        }
        while (Strings.contains(s, "-+")) {
            s = Strings.replaceAll(s, "-+", "-");
        }
        Iterator i1 = operations.iterator();
        while (i1.hasNext()) {
            Operator o = (Operator) i1.next();
            String op = o.getChar() + "";
            if (Strings.contains(s, op)
                    && !isBetween(s, o.getChar(), '(', ')')
                    && !isBetween(s, o.getChar(), '[', ']')) {
                try {
                    return new GordianNumber(o.result(((GordianNumber) toObject(s.substring(0, s.lastIndexOf(o.getChar())))).getValue(),
                            ((GordianNumber) toObject(s.substring(s.lastIndexOf(o.getChar()) + 1))).getValue()));
                } catch (Exception e) {
                    // weren't numbers...
                }
            }
        }

        // constructor
        if (s.startsWith("new ") && Strings.contains(s, '(') && s.endsWith(")")) {
            int track = 0;
            for (int x = s.indexOf('('); x < s.length(); x++) {
                if (s.charAt(x) == '(') {
                    track++;
                } else if (s.charAt(x) == ')') {
                    track--;
                }
                if (track == 0) {
                    if (x == s.length() - 1) {
                        // is a lone constructor
                        try {
                            Class c = (Class) variables().get(s.substring(4, s.indexOf("(")));
                            Arguments a = new Arguments(getArgs(betweenMatch(s, '(', ')')));
                            return c.contruct(a);
                        } catch (InternalNotFoundException ex) {
                            throw new RuntimeException("Could not find class \"" + s.substring(4, s.indexOf("(")) + "\"");
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        // and
        if (Strings.contains(s, "&&")) {
            return new GordianBoolean(((GordianBoolean) toObject(s.substring(0, s.indexOf("&&")))).getValue()
                    && ((GordianBoolean) toObject(s.substring(s.indexOf("&&") + 2))).getValue());
        }

        // or
        if (Strings.contains(s, "||")) {
            return new GordianBoolean(((GordianBoolean) toObject(s.substring(0, s.indexOf("||")))).getValue()
                    || ((GordianBoolean) toObject(s.substring(s.indexOf("||") + 2))).getValue());
        }

        // equals
        if (Strings.contains(s, "==")) {
            return new GordianBoolean(toObject(s.substring(0, s.indexOf("==")))
                    .equals(toObject(s.substring(s.indexOf("==") + 2))));
        }

        // not equals
        if (Strings.contains(s, "!=")) {
            return new GordianBoolean(!toObject(s.substring(0, s.indexOf("!=")))
                    .equals(toObject(s.substring(s.indexOf("!=") + 2))));
        }

        // bigger or equal
        if (Strings.contains(s, ">=")) {
            return new GordianBoolean(((GordianNumber) toObject(s.substring(0, s.indexOf(">=")))).getValue()
                    >= ((GordianNumber) toObject(s.substring(s.indexOf(">=") + 2))).getValue());
        }

        // smaller or equal
        if (Strings.contains(s, "<=")) {
            return new GordianBoolean(((GordianNumber) toObject(s.substring(0, s.indexOf("<=")))).getValue()
                    <= ((GordianNumber) toObject(s.substring(s.indexOf("<=") + 2))).getValue());
        }

        // bigger
        if (Strings.contains(s, ">")) {
            return new GordianBoolean(((GordianNumber) toObject(s.substring(0, s.indexOf(">")))).getValue()
                    > ((GordianNumber) toObject(s.substring(s.indexOf(">") + 1))).getValue());
        }

        // smaller
        if (Strings.contains(s, "<")) {
            return new GordianBoolean(((GordianNumber) toObject(s.substring(0, s.indexOf("<")))).getValue()
                    < ((GordianNumber) toObject(s.substring(s.indexOf("<") + 1))).getValue());
        }

        // object access
        if (s.indexOf(".") > 0 && s.indexOf(".") < s.length() - 1) {
            Object call = null;
            int index = s.lastIndexOf('.');
            while (call == null && index != -1) {
                try {
                    call = toObject(s.substring(0, index));
                } catch (NullPointerException e) {
                    index = s.substring(0, index).lastIndexOf('.');
                }
            }

            String r = s.substring(index + 1);
            if (isMethod(r)) {
                Method[] m = call.methods().getAll(r.substring(0, r.indexOf("(")));
                Arguments a = new Arguments(getArgs(betweenMatch(r, '(', ')')));
                for (int x = 0; x < m.length; x++) {
                    if (m[x].signature().matches(a.getSignature())) {
                        return m[x].run(a);
                    }
                }
                throw new RuntimeException("Appropriate method could not be found - " + r);
            }

            if (call != null && isName(r)) {
                try {
                    return call.variables().get(r);
                } catch (InternalNotFoundException ex) {
                    throw new RuntimeException(r + " could not be found in context "
                            + s.substring(0, s.lastIndexOf('.')) + " [" + call + "]");
                }
            }
        }

        // list access
        if (s.lastIndexOf('[') > 0 && s.endsWith("]")) {
            GordianList list = (GordianList) toObject(s.substring(0, s.lastIndexOf('[')));
            GordianNumber index = (GordianNumber) toObject(s.substring(s.lastIndexOf('[') + 1, s.length() - 1));
            return list.get(index.getInt());
        }

        // negative
        if (s.startsWith("-")) {
            return new GordianNumber(-((GordianNumber) toObject(s.substring(1))).getValue());
        }
        if (s.startsWith("+")) {
            return new GordianNumber(((GordianNumber) toObject(s.substring(1))).getValue());
        }

        // variables
        if (variables().contains(s)) {
            try {
                return variables().get(s);
            } catch (InternalNotFoundException ex) {
                // should never happen
            }
        }

        throw new NullPointerException(s + " was not a value or instruction.");
    }

    public void runBlock(String b) {
        try {
            if (b.startsWith("if(")) {
                GordianIf.run(this, b);
            } else if (b.startsWith("for(")) {
                GordianFor.run(this, b);
            } else if (b.startsWith("count(")) {
                GordianCount.run(this, b);
            } else if (b.startsWith("thread{")) {
                GordianThread.run(this, b);
            } else if (b.startsWith("try{")) {
                GordianTry.run(this, b);
            } else if (b.startsWith("while(")) {
                GordianWhile.run(this, b);
            } else if (b.startsWith("def") && isName(b.substring(3, b.indexOf('(')))) {
                methods().put(b.substring(3, b.indexOf('(')), GordianDefinedMethod.get(this, b));
            } else if (b.startsWith("class")) {
                // no parent
                if (isName(b.substring(5, b.indexOf('{')))) {
                    variables().put(b.substring(5, b.indexOf('{')), GordianDefinedClass.get(this, null,
                            b.substring(b.indexOf('{') + 1, b.lastIndexOf('}'))));
                } else if (isName(b.substring(5, b.indexOf('('))) && Strings.contains(b.substring(0, b.indexOf('{')), ")")) {
                    variables().put(b.substring(5, b.indexOf('(')), GordianDefinedClass.get(this,
                            (Class) variables().get(b.substring(b.indexOf('(') + 1, b.indexOf(')'))),
                            b.substring(b.indexOf('{') + 1, b.lastIndexOf('}'))));
                } else {
                    throw new RuntimeException("Illegal class was created.");
                }
            } else {
                throw new NullPointerException("Block was not recognized - " + b);
            }
        } catch (Break e) {
            // break was called
        } catch (InternalNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public boolean equals(Object object) {
        return object == this;
    }

    public Class parentClass() {
        return null;
    }

    public Object parent() {
        return container;
    }

    private static interface Operator {

        public char getChar();

        public double result(double o, double o1);
    }

    private static class Addition implements Operator {

        public char getChar() {
            return '+';
        }

        public double result(double o, double o1) {
            return o + o1;
        }
    }

    private static class Subtraction implements Operator {

        public char getChar() {
            return '-';
        }

        public double result(double o, double o1) {
            return o - o1;
        }
    }

    private static class Multiplication implements Operator {

        public char getChar() {
            return '*';
        }

        public double result(double o, double o1) {
            return o * o1;
        }
    }

    private static class Division implements Operator {

        public char getChar() {
            return '/';
        }

        public double result(double o, double o1) {
            return o / o1;
        }
    }

    private static class Modulus implements Operator {

        public char getChar() {
            return '%';
        }

        public double result(double o, double o1) {
            return o % o1;
        }
    }
}
