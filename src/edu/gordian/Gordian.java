package edu.gordian;

import edu.gordian.method.Method;
import edu.gordian.method.RunningMethod;
import edu.gordian.special.For;
import edu.gordian.special.If;
import edu.gordian.special.Special;
import edu.gordian.special.While;
import edu.gordian.variable.BooleanInterface;
import edu.gordian.variable.BooleanVariable;
import edu.gordian.variable.NumberInterface;
import edu.gordian.variable.NumberVariable;
import edu.gordian.variable.StringInterface;
import edu.gordian.variable.StringVariable;
import edu.gordian.variable.field.BooleanField;
import edu.gordian.variable.field.NumberField;
import edu.gordian.variable.field.StringField;
import java.utils.ArrayList;

public class Gordian {

    private final String script;
    private final ArrayList methods;
    private final ArrayList fields;
    private final ArrayList blockStack = new ArrayList();
    private int count = 0;
    private If prevIf;

    public Gordian(String script, Method[] methods) {
        if (script == null) {
            throw new NullPointerException("Null script");
        }
        this.methods = new ArrayList();
        this.fields = new ArrayList();
        if (methods != null) {
            for (int x = 0; x < methods.length; x++) {
                this.methods.add(methods[x]);
            }
        }
        this.script = StringUtils.replace(StringUtils.replace(script, '\n', ";"), ']', "];");
    }

    public Gordian(String script) {
        this(script, null);
    }

    public Gordian(Gordian gordian, String script) {
        if (script == null) {
            throw new NullPointerException("Null script");
        }
        this.methods = gordian.methods;
        this.fields = gordian.fields;
        this.script = StringUtils.replace(StringUtils.replace(script, '\n', ";"), ']', "];");
    }

    public void addMethod(Method method) {
        methods.add(method);
    }

    public void run() {
        String[] s = StringUtils.split(script, ';');
        for (int x = 0; x < s.length; x++) {
            s[x] = s[x].trim();
            if (!(s[x].length() == 0 && s[x].startsWith("#"))) {
                doLine(s[x]);
            }
        }
    }

    private void doLine(String line) {
        if (StringUtils.contains(line, "[")) {
            count++;
        } else if (StringUtils.contains(line, "]")) {
            count--;
        }
        if (blockStack.size() > 0 && !(StringUtils.contains(line, "]") && count == 0)) {
            ((Special) blockStack.get(blockStack.size() - 1)).add(line);
            return;
        }
        if (StringUtils.contains(line, "[")) {
            String start = line.substring(0, line.indexOf("["));
            String arg = null;
            if (StringUtils.contains(start, "(") && StringUtils.contains(start, ")")) {
                arg = start.substring(start.indexOf("(") + 1, start.lastIndexOf(')'));
            }
            Special newBlock;
            if (start.startsWith("while")) {
                newBlock = new While(this, arg);
            } else if (start.startsWith("for")) {
                Variable variable = convertVariable(arg);
                if (variable instanceof NumberVariable) {
                    newBlock = new For(this, ((NumberVariable) variable).intValue());
                } else {
                    System.err.println("Invalid for loop count - " + variable.getLiteralString());
                    newBlock = new If(this, "false");
                }
            } else if (start.startsWith("else")) {
                newBlock = new If(this, String.valueOf(!prevIf.ran()));
            } else {
                newBlock = new If(this, arg);
                prevIf = (If) newBlock;
            }
            blockStack.add(newBlock);
            line = line.substring(line.indexOf("[") + 1);
        }
        if (StringUtils.contains(line, "]")) {
            Special currentBlock = (Special) blockStack.get(blockStack.size() - 1);
            blockStack.remove(currentBlock);
            currentBlock.add(line.substring(0, line.indexOf("]")));
            currentBlock.run();
        }
        if (!StringUtils.contains(line, "[") && !StringUtils.contains(line, "]")) {
            convertInstruction(line).run();
        }
    }

    public Instruction convertInstruction(String original) {
        if (original.length() == 0 || original.startsWith("#")) {
            return new BlankInstruction();
        }
        if (StringUtils.contains(original, "=") && (original.indexOf("=") != original.indexOf("=="))
                && (original.indexOf("=") - 1 != original.indexOf("!="))
                && (original.indexOf("=") - 1 != original.indexOf(">="))
                && (original.indexOf("=") - 1 != original.indexOf("<="))) {
            return new Declaration(original);
        }
        if (StringUtils.contains(original, "++")) {
            return new Declaration(original);
        } else if (StringUtils.contains(original, "--")) {
            return new Declaration(original);
        }
        for (int x = 0; x < methods.size(); x++) {
            if (methods.get(x) instanceof RunningMethod && original.startsWith(((Method) methods.get(x)).getMethodName())
                    && original.indexOf("(") == ((Method) methods.get(x)).getMethodName().length() && StringUtils.contains(original, ")")) {
                final RunningMethod method = (RunningMethod) methods.get(x);
                String[] args = StringUtils.split(original.substring(original.indexOf("(") + 1, original.lastIndexOf(')')), ',');
                final Variable[] arguments = new Variable[args.length];
                for (int i = 0; i < arguments.length; i++) {
                    arguments[i] = convertVariable(args[i]);
                }
                return RunningMethod.createMethod(method, arguments);
            }
        }
        throw new NullPointerException(original + " is not an instruction");
    }

    public Variable convertVariable(String original) {
        try {
            if (original == null) {
                throw new NullPointerException("Null variable");
            }
            original = original.trim();
            if (original.indexOf("\"") != original.lastIndexOf('\"')) {
                return new StringVariable(original.substring(original.indexOf("\"") + 1, original.lastIndexOf('\"')));
            }
            // Boolean
            if (StringUtils.contains(original, "&&")) {
                return new BooleanVariable(((BooleanInterface) convertVariable(original.substring(0, original.indexOf("&&")))).booleanValue()
                        && ((BooleanInterface) convertVariable(original.substring(original.indexOf("&&") + 2))).booleanValue());
            }
            if (StringUtils.contains(original, "||")) {
                return new BooleanVariable(((BooleanInterface) convertVariable(original.substring(0, original.indexOf("||")))).booleanValue()
                        || ((BooleanInterface) convertVariable(original.substring(original.indexOf("||") + 2))).booleanValue());
            }
            if (original.startsWith("!")) {
                return new BooleanVariable(!((BooleanInterface) convertVariable(original.substring(original.indexOf("!") + 1))).booleanValue());
            }
            if (original.equalsIgnoreCase("true")) {
                return new BooleanVariable(true);
            } else if (original.equalsIgnoreCase("false")) {
                return new BooleanVariable(false);
            } else if (StringUtils.contains(original, "==")) {
                Variable v1 = convertVariable(original.substring(0, original.indexOf("==")));
                Variable v2 = convertVariable(original.substring(original.indexOf("==") + 2));
                return new BooleanVariable(v1.getValue().equals(v2.getValue()));
            } else if (StringUtils.contains(original, "!=")) {
                Variable v1 = convertVariable(original.substring(0, original.indexOf("!=")));
                Variable v2 = convertVariable(original.substring(original.indexOf("!=") + 2));
                return new BooleanVariable(!v1.getValue().equals(v2.getValue()));
            } else if (StringUtils.contains(original, ">=")) {
                NumberInterface v1 = (NumberInterface) convertVariable(original.substring(0, original.indexOf(">=")));
                NumberInterface v2 = (NumberInterface) convertVariable(original.substring(original.indexOf(">=") + 2));
                return new BooleanVariable(v1.doubleValue() >= v2.doubleValue());
            } else if (StringUtils.contains(original, "<=")) {
                NumberInterface v1 = (NumberInterface) convertVariable(original.substring(0, original.indexOf("<=")));
                NumberInterface v2 = (NumberInterface) convertVariable(original.substring(original.indexOf("<=") + 2));
                return new BooleanVariable(v1.doubleValue() <= v2.doubleValue());
            } else if (StringUtils.contains(original, ">")) {
                NumberInterface v1 = (NumberInterface) convertVariable(original.substring(0, original.indexOf(">")));
                NumberInterface v2 = (NumberInterface) convertVariable(original.substring(original.indexOf(">") + 1));
                return new BooleanVariable(v1.doubleValue() > v2.doubleValue());
            } else if (StringUtils.contains(original, "<")) {
                NumberInterface v1 = (NumberInterface) convertVariable(original.substring(0, original.indexOf("<")));
                NumberInterface v2 = (NumberInterface) convertVariable(original.substring(original.indexOf("<") + 1));
                return new BooleanVariable(v1.doubleValue() < v2.doubleValue());
            }
            // Number
            try {
                return new NumberVariable(Double.parseDouble(original));
            } catch (NumberFormatException ex) {
                if (StringUtils.contains(original, "+")) {
                    return new NumberVariable(((NumberInterface) convertVariable(original.substring(0, original.indexOf("+")))).doubleValue()
                            + ((NumberInterface) convertVariable(original.substring(original.indexOf("+") + 1))).doubleValue());
                } else if (StringUtils.contains(original, "-")) {
                    return new NumberVariable(((NumberInterface) convertVariable(original.substring(0, original.indexOf("-")))).doubleValue()
                            - ((NumberInterface) convertVariable(original.substring(original.indexOf("-") + 1))).doubleValue());
                } else if (StringUtils.contains(original, "*")) {
                    return new NumberVariable(((NumberInterface) convertVariable(original.substring(0, original.indexOf("*")))).doubleValue()
                            * ((NumberInterface) convertVariable(original.substring(original.indexOf("*") + 1))).doubleValue());
                } else if (StringUtils.contains(original, "/")) {
                    return new NumberVariable(((NumberInterface) convertVariable(original.substring(0, original.indexOf("/")))).doubleValue()
                            / ((NumberInterface) convertVariable(original.substring(original.indexOf("/") + 1))).doubleValue());
                }
            }
            for (int x = 0; x < fields.size(); x++) {
                if (original.equals(((Field) fields.get(x)).fieldName())) {
                    return (Field) fields.get(x);
                }
            }
            for (int x = 0; x < methods.size(); x++) {
                if (original.startsWith(((Method) methods.get(x)).getMethodName())) {
                    String[] args = StringUtils.split(original.substring(original.indexOf("(") + 1, original.lastIndexOf(')')), ',');
                    final Variable[] arguments = new Variable[args.length];
                    for (int i = 0; i < arguments.length; i++) {
                        arguments[i] = convertVariable(args[i]);
                    }
                    return ((Variable) methods.get(x));
                }
            }
            // String
            return new StringVariable(original);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NullPointerException("Problem converting " + original);
        }
    }

    private final class Declaration implements Instruction {

        private final String original;

        public Declaration(String original) {
            this.original = original;
        }

        public void run() {
            if (StringUtils.contains(original, "++")) {
                String name = original.substring(0, original.indexOf("++")).trim();
                NumberVariable value = (NumberVariable) convertVariable(original.substring(0, original.indexOf("++")));
                for (int x = 0; x < fields.size(); x++) {
                    if (((Field) fields.get(x)).fieldName().equals(name)) {
                        fields.set(x, new NumberField(name, value.doubleValue() + 1));
                        return;
                    }
                }
            } else if (StringUtils.contains(original, "--")) {
                String name = original.substring(0, original.indexOf("--")).trim();
                NumberVariable value = (NumberVariable) convertVariable(original.substring(0, original.indexOf("--")));
                for (int x = 0; x < fields.size(); x++) {
                    if (((Field) fields.get(x)).fieldName().equals(name)) {
                        fields.set(x, new NumberField(name, value.doubleValue() - 1));
                        return;
                    }
                }
            } else {
                String name = original.substring(0, original.indexOf("=")).trim();
                Variable value = convertVariable(original.substring(original.indexOf("=") + 1));
                for (int x = 0; x < fields.size(); x++) {
                    if (((Field) fields.get(x)).fieldName().equals(name)) {
                        fields.set(x, convert(name, value));
                        return;
                    }
                }
                fields.add(convert(name, value));
            }
        }

        private Field convert(String name, Variable value) {
            if (value instanceof BooleanInterface) {
                return new BooleanField(name, (BooleanInterface) value);
            } else if (value instanceof NumberInterface) {
                return new NumberField(name, (NumberInterface) value);
            } else if (value instanceof StringInterface) {
                return new StringField(name, (StringInterface) value);
            } else {
                return new StringField(name, value.getValue().toString());
            }
        }
    }

    private static class BlankInstruction implements Instruction {

        public void run() {
        }
    }
}
