package edu.first.module.actuators;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.CANTalon;

/**
 * The general purpose class that manipulates a Talon SRX speed controller that
 * should only ever "follow" another Talon, through CAN.
 *
 * @since Jan 30 15
 * @author Joel Gallant
 */
public class CANTalonSRXFollower extends Module.StandardModule {

    private final CANTalonSRXModule talon;

    /**
     * Constructs the follower.
     *
     * @param devID id of follower
     * @param follow id of talon to follow
     */
    public CANTalonSRXFollower(int devID, int follow) {
        this.talon = new CANTalonSRXModule(devID, CANTalon.ControlMode.Follower);
        talon.set(follow);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void enableModule() {
        talon.enable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void disableModule() {
        talon.disable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        talon.init();
    }
}
