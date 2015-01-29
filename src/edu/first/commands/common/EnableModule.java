package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.module.Module;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class EnableModule implements Command {

    private final Module[] modules;

    public EnableModule(Module[] modules) {
        this.modules = modules;
    }

    public EnableModule(Module module) {
        this.modules = new Module[]{module};
    }

    @Override
    public void run() {
        for (Module module : modules) {
            module.enable();
        }
    }
}
