package ata2014.subsystems;

import ata2014.main.Ports;
import edu.first.module.Module;
import edu.first.module.actuators.SpikeRelay;
import edu.first.module.actuators.SpikeRelayModule;
import edu.first.module.sensors.DigitalInput;
import edu.first.module.subsystems.Subsystem;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Compressor extends Ports {

    SpikeRelayModule compressorRelay = new SpikeRelayModule(COMPRESSOR);
    DigitalInput compressorPSI = new DigitalInput(COMPRESSOR_PSI);
    edu.first.module.controllers.Compressor compressorController
            = new edu.first.module.controllers.Compressor(compressorRelay, compressorPSI, SpikeRelay.Direction.FORWARDS, true);

    Subsystem compressor = new Subsystem(new Module[]{
        compressorRelay, compressorPSI, compressorController
    });
}
