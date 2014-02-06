package ata2014.main;

import api.gordian.Arguments;
import api.gordian.Class;
import api.gordian.Object;
import api.gordian.Signature;
import org.gordian.GordianClass;
import org.gordian.method.GordianMethod;
import org.gordian.scope.GordianScope;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class Autonomous extends GordianScope {

    private final Command command = new Command();

    public Autonomous() {
        variables().put("Command", command);
    }

    private class Command extends GordianClass {

        public Command() {
            super(null);
        }

        public Object contruct(Arguments arguments) {
            GordianScope scope = new GordianScope();
            scope.methods().put("run", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    System.out.println("Command's run method was not found.");
                    return null;
                }
            });
            return scope;
        }

        public Signature[] contructors() {
            return new Signature[]{new Signature()};
        }

    }

    private class InternalCommand extends GordianClass {

        private final edu.first.command.Command internalCommand;

        public InternalCommand(edu.first.command.Command internalCommand) {
            super(command);
            this.internalCommand = internalCommand;
        }

        public Object contruct(Arguments arguments) {
            GordianScope scope = new GordianScope();
            scope.methods().put("run", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    internalCommand.run();
                    return null;
                }
            });
            return scope;
        }

        public Signature[] contructors() {
            return new Signature[]{new Signature()};
        }
    }
}
