package edu.ata.commands;

import edu.ata.subsystems.SmartDashboardSender;
import edu.first.identifiers.ReturnableBoolean;

public final class SetSmartDashboard extends ThreadableCommand {

    private final SmartDashboardSender dashboardSender;
    private final ReturnableBoolean on;

    public SetSmartDashboard(SmartDashboardSender dashboardSender, boolean on, boolean newThread) {
        this(dashboardSender, new ReturnableBoolean.Boolean(on), newThread);
    }

    public SetSmartDashboard(SmartDashboardSender dashboardSender, ReturnableBoolean on, boolean newThread) {
        super(newThread);
        this.dashboardSender = dashboardSender;
        this.on = on;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if(on.get()) {
                    dashboardSender.start();
                } else {
                    dashboardSender.stop();
                }
            }
        };
    }
}