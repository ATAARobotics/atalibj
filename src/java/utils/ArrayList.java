package java.utils;

public final class ArrayList {

    private Object[] elements;

    public ArrayList() {
        this(0);
    }

    public ArrayList(int size) {
        this.elements = new Object[size];
    }

    public int size() {
        int x = 0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                x++;
            }
        }
        return x;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        ensureCapacity(0);
    }

    public int capacity() {
        return elements.length;
    }

    public void ensureCapacity(int capacity) {
        if (elements.length < capacity) {
            Object[] buf = elements;
            elements = new Object[capacity];
            System.arraycopy(buf, 0, elements, 0, buf.length);
        }
    }

    public void add(Object element) {
        shiftElements();
        ensureCapacity(elements.length + 1);
        elements[elements.length - 1] = element;
    }

    public boolean remove(Object element) {
        for (int x = 0; x < elements.length; x++) {
            if (elements[x].equals(element)) {
                elements[x] = null;
                shiftElements();
                return true;
            }
        }
        return false;
    }

    public boolean contains(Object element) {
        for (int x = 0; x < elements.length; x++) {
            if (elements[x].equals(element)) {
                return true;
            }
        }
        return false;
    }
    
    public void set(int index, Object element) {
        shiftElements();
        elements[index] = element;
    }

    public Object get(int index) {
        return elements[index];
    }

    private void shorten(int length) {
        Object[] buf = elements;
        elements = new Object[length];
        System.arraycopy(buf, 0, elements, 0, elements.length);
    }

    private void shiftElements() {
        for (int x = 1; x < elements.length; x++) {
            while (elements[x - 1] == null) {
                Object[] buf = elements;
                System.arraycopy(buf, x, elements, x - 1, buf.length - x);
                shorten(elements.length - 1);
                x--;
            }
        }
    }
}