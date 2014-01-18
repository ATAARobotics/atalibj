package ata2014.main;

import edu.first.module.actuators.VictorModule;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;

/**
 *
 * @author ata
 */
public interface Loader extends Ports {

    VictorModule loaderMotor = new VictorModule(LOADER);
    
    Subsystem loader = new SubsystemBuilder()
            .add(loaderMotor)
            .toSubsystem();
}
