package ata2014.main.identifiers;

import edu.first.identifiers.Function;

/**
 *
 * @author Skyler
 */
public class OneWayMotor implements Function {
    
    public double F(double in) {
        return in > 0 ? in : 0;
    }
}
