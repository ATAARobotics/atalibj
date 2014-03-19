package org.gordian.storage;

import api.gordian.Object;
import api.gordian.storage.InternalNotFoundException;
import api.gordian.storage.Variables;
import edu.first.util.list.List;
import org.gordian.value.GordianBoolean;
import org.gordian.value.GordianNull;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianVariables implements Variables {

    private final GordianStorage storage;

    private final void init() {
        storage.reserve("null", new GordianNull());
        storage.reserve("true", GordianBoolean.TRUE);
        storage.reserve("True", GordianBoolean.TRUE);
        storage.reserve("false", GordianBoolean.FALSE);
        storage.reserve("False", GordianBoolean.FALSE);
    }

    public GordianVariables(boolean empty) {
        storage = new GordianStorage();
        if (!empty) {
            init();
        }
    }

    public GordianVariables() {
        storage = new GordianStorage();
        init();
    }

    public GordianVariables(GordianVariables variables) {
        storage = new GordianStorage(variables.storage);
    }

    public void sendTo(GordianVariables variables) {
        storage.sendTo(variables.storage);
    }

    public Object get(String name) throws InternalNotFoundException {
        return (Object) storage.get(name);
    }

    public Object put(String name, Object object) {
        return (Object) storage.put(name, object);
    }

    public Object set(String name, Object object) {
        return (Object) storage.set(name, object);
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

    public List keys() {
        return storage.keys();
    }
}
