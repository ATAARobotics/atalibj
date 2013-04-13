package edu.first.commands;

import edu.first.command.Command;
import edu.first.identifiers.ReturnableNumber;
import edu.first.identifiers.SetteableNumber;

/**
 * Command that sets a {@link SetteableNumber}.
 *
 * @author Joel Gallant
 */
public final class SetNumberCommand implements Command {

    private final SetteableNumber setteableNumber;
    private final ReturnableNumber setting;

    /**
     * Sets the {@link SetteableNumber} to the setting when run.
     *
     * @param setteableNumber object to set number on
     * @param setting value to set object to
     */
    public SetNumberCommand(SetteableNumber setteableNumber, double setting) {
        this.setteableNumber = setteableNumber;
        this.setting = new ReturnableNumber.Number(setting);
    }

    /**
     * Sets the {@link SetteableNumber} to the setting when run.
     *
     * @param setteableNumber object to set number on
     * @param setting value to set object to
     */
    public SetNumberCommand(SetteableNumber setteableNumber, ReturnableNumber setting) {
        this.setteableNumber = setteableNumber;
        this.setting = setting;
    }

    public void run() {
        setteableNumber.set(setting.get());
    }
}
