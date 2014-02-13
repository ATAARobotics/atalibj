package ata2014.main;

import edu.first.module.actuators.SpikeRelay;
import edu.first.module.actuators.SpikeRelayModule;
import edu.first.module.sensors.DigitalInput;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Compressor extends Ports {

    SpikeRelayModule compressorRelay = new SpikeRelayModule(1);
    DigitalInput compressorPSI = new DigitalInput(1);
    edu.first.module.controllers.Compressor compressorController
            = new edu.first.module.controllers.Compressor(compressorRelay, compressorPSI, SpikeRelay.Direction.FORWARDS, true);

    Subsystem compressor = new SubsystemBuilder()
            .add(compressorRelay)
            .add(compressorPSI)
            .add(compressorController)
            .toSubsystem();
}
