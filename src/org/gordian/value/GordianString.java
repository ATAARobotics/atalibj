package org.gordian.value;

import api.gordian.Class;
import api.gordian.Object;
import api.gordian.Signature;
import edu.first.util.Strings;
import org.gordian.GordianPrimitive;
import org.gordian.method.GordianMethod;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianString extends GordianPrimitive {

    public static final Class CLASS = Parent.CLASS;
    private final String val;

    public static GordianString evaluate(String literal) {
        String[] s = Strings.split(literal, "@");
        StringBuffer b = new StringBuffer();
        for (int x = 1; x < s.length; x++) {
            b.append((char) Integer.parseInt(s[x]));
        }
        return new GordianString(Strings.replaceAll(Strings.replaceAll(Strings.replaceAll(Strings.replaceAll(b.toString(),
                "\\\"", "\""), "\\\'", "\'"), "\\t", "\t"), "\\n", "\n"));
    }

    {
        methods().put("charAt", new GordianMethod(
                new Signature(
                        new Class[]{GordianNumber.CLASS}
                )) {
                    public Object run(Object[] args) {
                        return new GordianString(String.valueOf(val.charAt(((GordianNumber) args[0]).getInt())));
                    }
                });
        methods().put("indexOf", new GordianMethod(
                new Signature(
                        new Class[]{GordianNumber.CLASS}
                )) {
                    public Object run(Object[] args) {
                        return new GordianNumber(val.indexOf(((GordianString) args[0]).getValue()));
                    }
                });
        methods().put("length", new GordianMethod(
                new Signature()) {
                    public Object run(Object[] args) {
                        return new GordianNumber(val.length());
                    }
                });
    }

    public GordianString(String val) {
        this.val = val;
    }

    public String getValue() {
        return val;
    }

    public boolean equals(Object object) {
        if (object instanceof GordianString) {
            return ((GordianString) object).val.equals(val);
        } else {
            return false;
        }
    }

    public Class parentClass() {
        return CLASS;
    }

    public Object parent() {
        return null;
    }

    public String toString() {
        return val;
    }

    private static final class Parent extends PrimitiveClass {

        private static final Class CLASS = new Parent();
    }
}
