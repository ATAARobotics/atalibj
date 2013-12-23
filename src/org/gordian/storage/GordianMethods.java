package org.gordian.storage;

import api.gordian.Class;
import api.gordian.Object;
import api.gordian.Signature;
import api.gordian.methods.Method;
import api.gordian.storage.InternalNotFoundException;
import api.gordian.storage.Methods;
import java.util.Random;
import org.gordian.GordianClass;
import org.gordian.method.GordianMethod;
import org.gordian.scope.Break;
import org.gordian.scope.GordianScope;
import org.gordian.value.GordianBoolean;
import org.gordian.value.GordianNumber;
import org.gordian.value.GordianString;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianMethods implements Methods {

    private final GordianStorage storage;

    private final void init() {
        final Random RANDOM = new Random();
        storage.reserve("exit", new GordianMethod(
                new Signature(
                        new Class[]{GordianNumber.CLASS}
                )) {
                    public Object run(Object[] args) {
                        System.exit(((GordianNumber) args[0]).getInt());
                        return null;
                    }
                });
        storage.reserve("time", new GordianMethod(
                new Signature()) {
                    public Object run(Object[] args) {
                        return new GordianNumber(System.currentTimeMillis());
                    }
                });
        storage.reserve("break", new GordianMethod(
                new Signature()) {
                    public Object run(Object[] args) {
                        throw new Break();
                    }
                });
        storage.reserve("eval", new GordianMethod(
                new Signature(
                        new Class[]{GordianString.CLASS}
                )) {
                    public Object run(Object[] args) {
                        new GordianScope().run(((GordianString) args[0]).getValue());
                        return null;
                    }
                });
        storage.reserve("int", new GordianMethod(
                new Signature(
                        new Class[]{GordianNumber.CLASS}
                )) {
                    public Object run(Object[] args) {
                        return new GordianNumber(((GordianNumber) args[0]).getInt());
                    }
                });
        storage.reserve("num", new GordianMethod(
                new Signature(
                        new Class[]{GordianClass.ALL_CLASSES}
                )) {
                    public Object run(Object[] args) {
                        return new GordianNumber(Double.parseDouble(args[0].toString()));
                    }
                });
        storage.reserve("bool", new GordianMethod(
                new Signature(
                        new Class[]{GordianClass.ALL_CLASSES}
                )) {
                    public Object run(Object[] args) {
                        return new GordianBoolean(args[0].toString().equalsIgnoreCase("true"));
                    }
                });
        storage.reserve("str", new GordianMethod(
                new Signature(
                        new Class[]{GordianClass.ALL_CLASSES}
                )) {
                    public Object run(Object[] args) {
                        return new GordianString(args[0].toString());
                    }
                });
        storage.reserve("concat", new GordianMethod(
                new Signature(
                        new Class[]{GordianClass.ALL_CLASSES, GordianClass.ALL_CLASSES}
                )) {
                    public Object run(Object[] args) {
                        return new GordianString(args[0].toString() + args[1].toString());
                    }
                });
        storage.reserve("print", new GordianMethod(
                new Signature(
                        new Class[]{GordianClass.ALL_CLASSES}
                )) {
                    public Object run(Object[] args) {
                        System.out.println(args[0].toString());
                        return null;
                    }
                });
        storage.reserve("sleep", new GordianMethod(
                new Signature(
                        new Class[]{GordianNumber.CLASS}
                )) {
                    public Object run(Object[] args) {
                        try {
                            Thread.sleep(((GordianNumber) args[0]).getLong());
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        return null;
                    }
                });
        storage.reserve("rand", new GordianMethod(
                new Signature()) {
                    public Object run(Object[] args) {
                        return new GordianNumber(RANDOM.nextDouble());
                    }
                });
        storage.reserve("randInt", new GordianMethod(
                new Signature()) {
                    public Object run(Object[] args) {
                        return new GordianNumber(RANDOM.nextInt());
                    }
                });
    }

    public GordianMethods(boolean empty) {
        storage = new GordianStorage();
        if (!empty) {
            init();
        }
    }

    public GordianMethods() {
        storage = new GordianStorage();
        init();
    }

    public GordianMethods(GordianMethods methods) {
        storage = new GordianStorage(methods.storage);
    }

    public void sendTo(GordianMethods methods) {
        storage.sendTo(methods.storage);
    }
    
    public Method get(String name) throws InternalNotFoundException {
        return (Method) storage.get(name);
    }

    public Method[] getAll(String name) {
        java.lang.Object[] o = storage.getAll(name);
        Method[] a = new Method[o.length];
        System.arraycopy(o, 0, a, 0, o.length);
        return a;
    }

    public void put(String name, Method method) {
        storage.put(name, method);
    }

    public void set(String name, Method method) {
        storage.set(name, method);
    }

    public void remove(String name) throws InternalNotFoundException {
        storage.remove(name);
    }

    public void removeAll(String name) {
        storage.removeAll(name);
    }

    public boolean contains(String name) {
        return storage.contains(name);
    }
}
