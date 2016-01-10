package edu.first.module.actuators;

import edu.first.command.Command;
import edu.first.module.Module;

/**
 * Controls a single-action solenoid. Has only two positions - on and off.
 *
 * @since June 01 13
 * @author Joel Gallant
 */
public class SolenoidModule extends Module.StandardModule implements Solenoid {

    private final edu.wpi.first.wpilibj.Solenoid solenoid;

    /**
     * Constructs the solenoid with the {@link edu.wpi.first.wpilibj.Solenoid}
     * object that this solenoid controls.
     *
     * @param solenoid the composing instance which perform the functions
     */
    protected SolenoidModule(edu.wpi.first.wpilibj.Solenoid solenoid) {
        if (solenoid == null) {
            throw new NullPointerException("Null solenoid given");
        }
        this.solenoid = solenoid;
    }

    /**
     * Constructs the solenoid using its channel on the solenoid breakout.
     *
     * @param channel channel on solenoid sidecar
     */
    public SolenoidModule(int channel) {
        this(new edu.wpi.first.wpilibj.Solenoid(channel));
    }

    /**
     * Constructs the solenoid using its channel on the solenoid breakout.
     *
     * @param channel channel on solenoid sidecar
     * @param slot slot in cRIO (1 = default)
     */
    public SolenoidModule(int channel, int slot) {
        this(new edu.wpi.first.wpilibj.Solenoid(slot, channel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Turns off the solenoid.
     */
    @Override
    protected void disableModule() {
        solenoid.set(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public void setPosition(boolean pos) {
        ensureEnabled();
        solenoid.set(pos);
    }

    /**
     * Returns a command that calls {@link #setPosition(boolean)}.
     *
     * @param pos the new position of the solenoid
     * @return command that sets position
     */
    public Command setPositionCommand(final boolean pos) {
        return new Command() {
            @Override
            public void run() {
                setPosition(pos);
            }
        };
    }

    /**
     * Reverses the position of the solenoid.
     *
     * @throws IllegalStateException when module is not enabled
     */
    public void reverse() {
        setPosition(!getPosition());
    }

    /**
     * Returns a command that calls {@link #reverse()}.
     *
     * @return command that reverses
     */
    public Command reverseCommand() {
        return new Command() {
            @Override
            public void run() {
                reverse();
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getPosition() {
        return solenoid.get();
    }
}
