package edu.first.commands;

import edu.first.command.Command;
import edu.first.identifiers.ReturnableBoolean;
import edu.first.identifiers.SetteableBoolean;

/**
 * Command that sets a {@link SetteableBoolean}.
 *
 * @author Joel Gallant
 */
public class SetBooleanCommand implements Command {

    private final SetteableBoolean setteableBoolean;
    private final ReturnableBoolean setting;

    /**
     * Sets the {@link SetteableBoolean} to the setting when run.
     *
     * @param setteableBoolean object that can be set
     * @param setting value to set object to
     */
    public SetBooleanCommand(SetteableBoolean setteableBoolean, boolean setting) {
        this.setteableBoolean = setteableBoolean;
        this.setting = new ReturnableBoolean.Boolean(setting);
    }

    /**
     * Sets the {@link SetteableBoolean} to the setting when run.
     *
     * @param setteableBoolean object that can be set
     * @param setting value to set object to
     */
    public SetBooleanCommand(SetteableBoolean setteableBoolean, ReturnableBoolean setting) {
        this.setteableBoolean = setteableBoolean;
        this.setting = setting;
    }

    public void run() {
        setteableBoolean.set(setting.get());
    }
}