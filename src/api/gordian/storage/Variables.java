package api.gordian.storage;

import api.gordian.Object;

/**
 * Storage for objects. The specific behavior of the methods is up to the
 * implementation given.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Variables {

    /**
     * Returns the object stored under the name.
     *
     * @param name user name for the object
     * @return object for user
     * @throws InternalNotFoundException when object was not found
     */
    public Object get(String name) throws InternalNotFoundException;

    /**
     * Stores the object as the name. This method does not necessarily overwrite
     * any existing objects.
     *
     * @param name user name for the object
     * @param object object to store
     * @return {@code object}
     */
    public Object put(String name, Object object);

    /**
     * Stores the object as the name. This method will overwrite any existing
     * objects.
     *
     * @param name user name for the object
     * @param object object to store
     * @return {@code object}
     */
    public Object set(String name, Object object);

    /**
     * Removes the object with the given name. This method will only remove the
     * first found instance of the name.
     *
     * @param name user name for the object
     * @throws InternalNotFoundException when object was not found
     */
    public void remove(String name) throws InternalNotFoundException;

    /**
     * Removes the object with the given name. This method will remove every
     * instance with the name.
     *
     * @param name user name for the object
     */
    public void removeAll(String name);

    /**
     * Returns whether this storage contains a object named {@code name}.
     *
     * @param name user name for the object
     * @return if named object is found in storage
     */
    public boolean contains(String name);
}
