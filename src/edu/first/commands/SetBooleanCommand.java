package edu.first.commands;

import edu.first.command.Command;
import edu.first.identifiers.SetteableBoolean;

public class SetBooleanCommand implements Command {

    private final SetteableBoolean setteableBoolean;
    private final boolean setting;

    public SetBooleanCommand(SetteableBoolean setteableBoolean, boolean setting) {
        this.setteableBoolean = setteableBoolean;
        this.setting = setting;
    }

    public void run() {
        setteableBoolean.set(setting);
    }
}