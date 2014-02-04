package ata2014.commands;

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

    public void run() {
        for (int x = 0; x < modules.length; x++) {
            modules[x].disable();
        }
    }
}
