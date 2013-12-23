package api.gordian.storage;

import api.gordian.methods.Method;

/**
 * Storage for methods. The specific behavior of the methods is up to the
 * implementation given.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Methods {

    /**
     * Returns the method stored under the name.
     *
     * @param name user name for the method
     * @return method object for user
     * @throws InternalNotFoundException when method was not found
     */
    public Method get(String name) throws InternalNotFoundException;

    public Method[] getAll(String name);

    /**
     * Stores the method as the name. This method does not necessarily overwrite
     * any existing methods.
     *
     * @param name user name for the method
     * @param method method to store
     */
    public void put(String name, Method method);

    /**
     * Stores the method as the name. This method will overwrite any existing
     * methods.
     *
     * @param name user name for the method
     * @param method method to store
     */
    public void set(String name, Method method);

    /**
     * Removes the method with the given name. This method will only remove the
     * first found instance of the name.
     *
     * @param name user name for the method
     * @throws InternalNotFoundException when method was not found
     */
    public void remove(String name) throws InternalNotFoundException;

    /**
     * Removes the method with the given name. This method will remove every
     * instance with the name.
     *
     * @param name user name for the method
     */
    public void removeAll(String name);

    /**
     * Returns whether this storage contains a method named {@code name}.
     *
     * @param name user name for the method
     * @return if named method is found in storage
     */
    public boolean contains(String name);
}
