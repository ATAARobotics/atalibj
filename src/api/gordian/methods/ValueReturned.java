package api.gordian.methods;

import api.gordian.Object;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class ValueReturned extends RuntimeException {

    public final Object value;

    public ValueReturned(Object value) {
        this.value = value;
    }
}
