package ata2014.main;

import api.gordian.Object;
import api.gordian.Signature;
import edu.first.main.Constants;
import org.gordian.method.GordianMethod;
import org.gordian.scope.GordianScope;
import org.gordian.value.GordianBoolean;
import org.gordian.value.GordianNumber;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class Autonomous extends GordianScope implements Constants {

    private final Drivetrain gordianDrivetrain = new Drivetrain();

    public Autonomous() {
        variables().put("drivetrain", gordianDrivetrain);
    }

    private class Drivetrain extends GordianScope {

        public Drivetrain() {
            super(Autonomous.this);

            methods().put("arcade", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS, GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    drivetrain.arcadeDrive(((GordianNumber) args[0]).getValue(), ((GordianNumber) args[1]).getValue());
                    return null;
                }
            });
            methods().put("tank", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS, GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    drivetrain.tankDrive(((GordianNumber) args[0]).getValue(), ((GordianNumber) args[1]).getValue());
                    return null;
                }
            });
            methods().put("drive", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS, GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    drivetrain.drive(((GordianNumber) args[0]).getValue(), ((GordianNumber) args[1]).getValue());
                    return null;
                }
            });
            methods().put("setMaxSpeed", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    drivetrain.setMaxSpeed(((GordianNumber) args[0]).getValue());
                    return null;
                }
            });
            methods().put("setReversed", new GordianMethod(new Signature(new api.gordian.Class[]{GordianBoolean.CLASS})) {
                public Object run(Object[] args) {
                    drivetrain.setReversed(((GordianBoolean) args[0]).getValue());
                    return null;
                }
            });
            methods().put("setReversedTurn", new GordianMethod(new Signature(new api.gordian.Class[]{GordianBoolean.CLASS})) {
                public Object run(Object[] args) {
                    drivetrain.setReversedTurn(((GordianBoolean) args[0]).getValue());
                    return null;
                }
            });
        }
    }
}
