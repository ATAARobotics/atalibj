package api.gordian;

import api.gordian.storage.Methods;
import api.gordian.storage.Variables;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Scope extends Object {
    
    public Scope container();

    public void run(String s);

    public Methods methods();

    public Variables variables();

    public Object toObject(String s);
}
