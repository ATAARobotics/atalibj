package edu.ata.script.storage;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Basic hash table storage of objects.
 *
 * @author Joel Gallant
 */
public abstract class Storage {

    private final Hashtable storage = new Hashtable();

    /**
     * Returns the {@link Hashtable} object.
     *
     * @return storage
     */
    protected Hashtable getStorage() {
        return storage;
    }

    /**
     * Abstract way of adding an object.
     *
     * @param key key to save under
     * @param value value to save
     */
    protected abstract void add(String key, Object value);

    /**
     * Returns the object saved under the key.
     *
     * @param key key saved under
     * @return object saved under key
     */
    public Object get(String key) {
        return storage.get(key);
    }

    /**
     * Returns all the keys in the storage.
     *
     * @return key in storage
     */
    public Enumeration keys() {
        return storage.keys();
    }

    /**
     * Returns whether the storage has a value under the key.
     *
     * @param key key to check
     * @return whether storage contains key
     */
    public boolean contains(String key) {
        return storage.containsKey(key);
    }

    /**
     * Returns size of the table.
     *
     * @return how many elements
     */
    public int size() {
        return storage.size();
    }

    /**
     * Removes all elements in the storage.
     */
    public void clear() {
        storage.clear();
    }
}