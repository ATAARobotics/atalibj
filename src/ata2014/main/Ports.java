package ata2014.main;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Ports extends Files {

    SettingsFile ports = new SettingsFile(portFile);

    // BACKUPS
    // --- Drivetrain
    int _leftDrive1 = 1,
            _leftDrive2 = 2,
            _leftDrive3 = 3;
    int _rightDrive1 = 5,
            _rightDrive2 = 6,
            _rightDrive3 = 7;
    int _shifterIn = 3,
            _shifterOut = 4;
    int _leftDriveEncoderA = 3,
            _leftDriveEncoderB = 4;
    int _rightDriveEncoderA = 5,
            _rightDriveEncoderB = 6;

    // --- Loader
    int _leftLoaderMotor = 9,
            _rightLoaderMotor = 4;
    int _loaderPistonIn = 1,
            _loaderPistonOut = 2;
    int _loaderPosition = 7;

    // --- Winch
    int _winchPosition = 1;
    int _winchLimit = 1;
    int _winchMotor = 10;
    int _winchReleaseIn = 8,
            _winchReleaseOut = 7;

    // --- Backloader
    int _backLoaderIn = 5,
            _backLoaderOut = 6;

    // --- Compressor
    int _compressor = 1;
    int _compressorPSI = 2;

    // PORTS
    // --- Drivetrain
    int LEFT_DRIVE_1 = ports.getInt("LeftDrive1", _leftDrive1),
            LEFT_DRIVE_2 = ports.getInt("LeftDrive2", _leftDrive2),
            LEFT_DRIVE_3 = ports.getInt("LeftDrive3", _leftDrive3);
    int RIGHT_DRIVE_1 = ports.getInt("RightDrive1", _rightDrive1),
            RIGHT_DRIVE_2 = ports.getInt("RightDrive2", _rightDrive2),
            RIGHT_DRIVE_3 = ports.getInt("RightDrive3", _rightDrive3);
    int SHIFTER_IN = ports.getInt("ShifterIn", _shifterIn),
            SHIFTER_OUT = ports.getInt("ShifterOut", _shifterOut);
    int LEFT_DRIVE_ENCODER_A = ports.getInt("LeftDriveEncoderA", _leftDriveEncoderA),
            LEFT_DRIVE_ENCODER_B = ports.getInt("LeftDriveEncoderB", _leftDriveEncoderB);
    int RIGHT_DRIVE_ENCODER_A = ports.getInt("RightDriveEncoderA", _rightDriveEncoderA),
            RIGHT_DRIVE_ENCODER_B = ports.getInt("RightDriveEncoderB", _rightDriveEncoderB);

    // --- Loader
    int LEFT_LOADER_MOTOR = ports.getInt("LeftLoaderMotor", _leftLoaderMotor),
            RIGHT_LOADER_MOTOR = ports.getInt("RightLoaderMotor", _rightLoaderMotor);
    int LOADER_PISTON_IN = ports.getInt("LoaderPistonIn", _loaderPistonIn),
            LOADER_PISTON_OUT = ports.getInt("LoaderPistonOut", _loaderPistonOut);
    int LOADER_POSITION = ports.getInt("LoaderPosition", _loaderPosition);

    // --- Winch
    int WINCH_POSITION = ports.getInt("WinchPosition", _winchPosition);
    int WINCH_LIMIT = ports.getInt("WinchLimit", _winchLimit);
    int WINCH_MOTOR = ports.getInt("WinchMotor", _winchMotor);
    int WINCH_RELEASE_IN = ports.getInt("WinchReleaseIn", _winchReleaseIn),
            WINCH_RELEASE_OUT = ports.getInt("WinchReleaseOut", _winchReleaseOut);

    // --- Compressor
    int COMPRESSOR = ports.getInt("Compressor", _compressor);
    int COMPRESSOR_PSI = ports.getInt("CompressorPSI", _compressorPSI);
}
