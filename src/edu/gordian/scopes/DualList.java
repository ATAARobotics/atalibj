package edu.gordian.scopes;

import edu.first.util.list.ArrayList;
import edu.first.util.list.Iterator;
import edu.first.util.list.List;

class DualList {

    private final List publicList = new ArrayList();
    private final List privateList = new ArrayList();

    DualList() {
    }

    public DualList(DualList parent) {
        // Private is added first to find closer scope first
        publicList.addAll(parent.privateList);
        publicList.addAll(parent.publicList);
    }

    public boolean isValue(String key) {
        return isPublicValue(key) || isPrivateValue(key);
    }

    public Object getValue(String key) {
        if (isPrivateValue(key)) {
            return getPrivateValue(key);
        } else if (isPublicValue(key)) {
            return getPublicValue(key);
        } else {
            throw new NullPointerException(key + " did not exist");
        }
    }

    public boolean isPublicValue(String key) {
        Iterator i = publicList.iterator();
        while (i.hasNext()) {
            Link l = (Link) i.next();
            if (l.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Object getPublicValue(String key) {
        Iterator i = publicList.iterator();
        while (i.hasNext()) {
            Link l = (Link) i.next();
            if (l.getKey().equals(key)) {
                return l.getValue();
            }
        }
        throw new NullPointerException(key + " did not exist");
    }

    public boolean isPrivateValue(String key) {
        Iterator i = privateList.iterator();
        while (i.hasNext()) {
            Link l = (Link) i.next();
            if (l.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Object getPrivateValue(String key) {
        Iterator i = privateList.iterator();
        while (i.hasNext()) {
            Link l = (Link) i.next();
            if (l.getKey().equals(key)) {
                return l.getValue();
            }
        }
        throw new NullPointerException(key + " did not exist");
    }

    public void setValue(String key, Object v) {
        if (isPrivateValue(key)) {
            setPrivateValue(key, v);
        } else if (isPublicValue(key)) {
            setPublicValue(key, v);
        } else {
            setPrivateValue(key, v);
        }
    }

    public void setPublicValue(String key, Object v) {
        for (int x = 0; x < publicList.size(); x++) {
            if (key.equals(((Link) publicList.get(x)).getKey())) {
                ((Link) publicList.get(x)).setValue(v);
                return;
            }
        }
        publicList.add(0, new Link(key, v));
    }

    public void setPrivateValue(String key, Object v) {
        for (int x = 0; x < privateList.size(); x++) {
            if (key.equals(((Link) privateList.get(x)).getKey())) {
                ((Link) privateList.get(x)).setValue(v);
                return;
            }
        }
        privateList.add(0, new Link(key, v));
    }

    public void remove(String key) {
        for (int x = 0; x < privateList.size(); x++) {
            if (key.equals(((Link) privateList.get(x)).getKey())) {
                privateList.remove(x);
                return;
            }
        }
        for (int x = 0; x < publicList.size(); x++) {
            if (key.equals(((Link) publicList.get(x)).getKey())) {
                publicList.remove(x);
                return;
            }
        }
    }

    public String toString() {
        return "private=" + privateList + " public=" + publicList;
    }

    private static final class Link {

        private final String s;
        private Object value;

        public Link(String s, Object value) {
            this.s = s;
            this.value = value;
        }

        public String getKey() {
            return s;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String toString() {
            return s;
        }
    }
}