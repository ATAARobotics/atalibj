package edu.first.commands;

import edu.first.command.Command;
import edu.first.identifiers.SetteableNumber;

public final class SetNumberCommand implements Command {

    private final SetteableNumber setteableNumber;
    private final double setting;

    public SetNumberCommand(SetteableNumber setteableNumber, double setting) {
        this.setteableNumber = setteableNumber;
        this.setting = setting;
    }

    public void run() {
        setteableNumber.set(setting);
    }
}
