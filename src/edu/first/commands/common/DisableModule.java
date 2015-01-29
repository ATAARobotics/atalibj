package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.module.Module;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class DisableModule implements Command {

    private final Module[] modules;

    public DisableModule(Module[] modules) {
        this.modules = modules;
    }

    public DisableModule(Module module) {
        this.modules = new Module[]{module};
    }

    @Override
    public void run() {
        for (Module module : modules) {
            module.disable();
        }
    }
}
