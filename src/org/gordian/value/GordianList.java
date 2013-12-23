package org.gordian.value;

import api.gordian.Class;
import api.gordian.Object;
import api.gordian.Signature;
import edu.first.util.list.Collections;
import edu.first.util.list.List;
import org.gordian.GordianClass;
import org.gordian.GordianPrimitive;
import org.gordian.method.GordianMethod;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianList extends GordianPrimitive {

    public static final Class CLASS = Parent.CLASS;
    private final List list;

    {
        methods().put("add", new GordianMethod(
                new Signature(
                        new Class[]{GordianClass.ALL_CLASSES}
                )) {
                    public Object run(Object[] args) {
                        list.add(args[0]);
                        return null;
                    }
                });
        methods().put("add", new GordianMethod(
                new Signature(
                        new Class[]{GordianNumber.CLASS, GordianClass.ALL_CLASSES}
                )) {
                    public Object run(Object[] args) {
                        list.add(((GordianNumber) args[0]).getInt(), args[1]);
                        return null;
                    }
                });
        methods().put("addAll", new GordianMethod(
                new Signature(
                        new Class[]{GordianList.CLASS}
                )) {
                    public Object run(Object[] args) {
                        list.addAll(((GordianList) args[0]).list);
                        return null;
                    }
                });
        methods().put("addAll", new GordianMethod(
                new Signature(
                        new Class[]{GordianNumber.CLASS, GordianList.CLASS}
                )) {
                    public Object run(Object[] args) {
                        list.addAll(((GordianNumber) args[0]).getInt(), ((GordianList) args[1]).list);
                        return null;
                    }
                });
        methods().put("clear", new GordianMethod(
                new Signature()) {
                    public Object run(Object[] args) {
                        list.clear();
                        return null;
                    }
                });
        methods().put("contains", new GordianMethod(
                new Signature(
                        new Class[]{GordianClass.ALL_CLASSES}
                )) {
                    public Object run(Object[] args) {
                        return new GordianBoolean(list.contains(args[0]));
                    }
                });
        methods().put("containsAll", new GordianMethod(
                new Signature(
                        new Class[]{GordianList.CLASS}
                )) {
                    public Object run(Object[] args) {
                        return new GordianBoolean(list.containsAll(((GordianList) args[0]).list));
                    }
                });
        methods().put("get", new GordianMethod(
                new Signature(
                        new Class[]{GordianNumber.CLASS}
                )) {
                    public Object run(Object[] args) {
                        return get(((GordianNumber) args[0]).getInt());
                    }
                });
        methods().put("indexOf", new GordianMethod(
                new Signature(
                        new Class[]{GordianClass.ALL_CLASSES}
                )) {
                    public Object run(Object[] args) {
                        return new GordianNumber(list.indexOf(args[0]));
                    }
                });
        methods().put("isEmpty", new GordianMethod(
                new Signature()) {
                    public Object run(Object[] args) {
                        return new GordianBoolean(list.isEmpty());
                    }
                });
        methods().put("lastIndexOf", new GordianMethod(
                new Signature(
                        new Class[]{GordianClass.ALL_CLASSES}
                )) {
                    public Object run(Object[] args) {
                        return new GordianNumber(list.lastIndexOf(args[0]));
                    }
                });
        methods().put("remove", new GordianMethod(
                new Signature(
                        new Class[]{GordianNumber.CLASS}
                )) {
                    public Object run(Object[] args) {
                        return (Object) list.remove(((GordianNumber) args[0]).getInt());
                    }
                });
        methods().put("removeAll", new GordianMethod(
                new Signature(
                        new Class[]{GordianList.CLASS}
                )) {
                    public Object run(Object[] args) {
                        list.removeAll(((GordianList) args[0]).list);
                        return null;
                    }
                });
        methods().put("retainAll", new GordianMethod(
                new Signature(
                        new Class[]{GordianList.CLASS}
                )) {
                    public Object run(Object[] args) {
                        list.retainAll(((GordianList) args[0]).list);
                        return null;
                    }
                });
        methods().put("set", new GordianMethod(
                new Signature(
                        new Class[]{GordianNumber.CLASS, GordianClass.ALL_CLASSES}
                )) {
                    public Object run(Object[] args) {
                        list.set(((GordianNumber) args[0]).getInt(), args[1]);
                        return null;
                    }
                });
        methods().put("size", new GordianMethod(
                new Signature()) {
                    public Object run(Object[] args) {
                        return new GordianNumber(list.size());
                    }
                });
        methods().put("sublist", new GordianMethod(
                new Signature(new Class[]{GordianNumber.CLASS, GordianNumber.CLASS})
        ) {
            public Object run(Object[] args) {
                int start = ((GordianNumber) args[0]).getInt();
                int end = ((GordianNumber) args[1]).getInt();
                Object[] array = new Object[end - start];
                for (int x = 0; x < array.length; x++) {
                    array[x] = (Object) list.get(x + start);
                }
                return new GordianList(array);
            }
        });
    }

    public GordianList(Object[] values) {
        list = Collections.asList(values);
    }

    public Object get(int index) {
        return (Object) list.get(index);
    }

    public boolean equals(Object object) {
        if (object instanceof GordianList) {
            return list.equals(((GordianList) object).list);
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
        return list.toString();
    }

    private static final class Parent extends PrimitiveClass {

        private static final Class CLASS = new Parent();
    }
}
