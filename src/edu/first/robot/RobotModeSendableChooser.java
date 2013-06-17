package edu.first.robot;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * A {@link SendableChooser} class meant to provide a safe environment to select
 * a {@code RobotMode} from {@code SmartDashboard}. Is not capable of adding
 * other modes once it is constructed.
 *
 * @since May 08 13
 * @author Joel Gallant
 */
public final class RobotModeSendableChooser extends SendableChooser implements RobotModeSelector, NamedSendable {

    /**
     * Constructs the selector with an array of options. The array must contain
     * at least one mode. All the modes are displayed when this object is sent
     * to the {@code SmartDashboard}. They are identified by their names.
     *
     * @throws IllegalArgumentException when array is empty
     * @param modes array of the options for robot modes
     */
    public RobotModeSendableChooser(RobotMode[] modes) {
        if (modes.length < 1) {
            throw new IllegalArgumentException();
        }
        super.addDefault(modes[0].getName(), modes[0]);
        for (int x = 1; x < modes.length; x++) {
            super.addObject(modes[x].getName(), modes[x]);
        }
    }

    /**
     * This method can not be used. Robot modes should never be added except in
     * the constructor.
     *
     * @deprecated can not add options
     * @param name the name of the option
     * @param object the option
     */
    public void addDefault(String name, Object object) {
    }

    /**
     * This method can not be used. Robot modes should never be added except in
     * the constructor.
     *
     * @deprecated can not add options
     * @param name the name of the option
     * @param object the option
     */
    public void addObject(String name, Object object) {
    }

    /**
     * Returns the selected option, guaranteed to be an instance of
     * {@link RobotMode}.
     *
     * @deprecated use {@link RobotModeSelector#getRobotMode()} instead
     * @return selected mode
     */
    public Object getSelected() {
        return (RobotMode) super.getSelected();
    }

    /**
     * Returns the currently selected robot mode.
     *
     * @return selected mode
     */
    public RobotMode getRobotMode() {
        return (RobotMode) getSelected();
    }

    public String getName() {
        return "RobotMode";
    }
}
