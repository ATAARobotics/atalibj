package edu.ata.script.storage;

import edu.ata.script.Data;

/**
 * Basic class used to store {@link Data}.
 *
 * @author Joel Gallant
 */
public class DataStorage extends Storage {

    /**
     * Adds an object to the storage. If the object is not an instance of
     * {@link Data}, an error will be thrown.
     *
     * <p> Stores the data statically. This means that the value will not change
     * from when it is put into storage and when it is accessed.
     *
     * @param key key to save under
     * @param data data to save
     * @throws IllegalArgumentException thrown when data is not data
     */
    public void add(String key, Object data) throws IllegalArgumentException {
        if (!(data instanceof Data)) {
            throw new IllegalArgumentException("Added data to the storage that "
                    + "WAS NOT DATA.");
        } else {
            getStorage().put(key, Data.get(((Data) data).getValue().toString()));
        }
    }

    /**
     * Type-safe way of adding {@link Data} objects to the storage. Is
     * equivalent to calling {@code add(key, data)}.
     *
     * @param key key to save under
     * @param data data to save
     */
    public void addData(String key, Data data) {
        // Stores the data statically
        add(key, data);
    }
}