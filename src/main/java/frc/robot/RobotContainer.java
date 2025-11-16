// Copyright (c) 2021-2025 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.Constants.Elevator;
import frc.robot.Constants.Slapdown;
import frc.robot.commands.DriveCommands;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CoralManipulatorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.SlapdownSubsystem;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.GyroIO;
import frc.robot.subsystems.drive.GyroIOPigeon2;
import frc.robot.subsystems.drive.ModuleIO;
import frc.robot.subsystems.drive.ModuleIOSim;
import frc.robot.subsystems.drive.ModuleIOTalonFX;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystems
  private final Drive drive;

  private final CommandXboxController xboxDriverController = new CommandXboxController(0);
  private final CommandXboxController xboxOperatorController = new CommandXboxController(1);
  // Dashboard inputs
  private final LoggedDashboardChooser<Command> autoChooser;

  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(true);
  private final SlapdownSubsystem slapdownSubsystem = new SlapdownSubsystem();
  private final CoralManipulatorSubsystem coralManipulatorSubsystem =
      new CoralManipulatorSubsystem();

  /* Driver Buttons */
  private final Trigger dStart = xboxDriverController.start();
  private final Trigger dBack = xboxDriverController.back();
  private final Trigger dY = xboxDriverController.y();
  private final Trigger dB = xboxDriverController.b();
  private final Trigger dA = xboxDriverController.a();
  private final Trigger dX = xboxDriverController.x();
  private final Trigger dLeftBumper = xboxDriverController.leftBumper();
  private final Trigger dRightBumper = xboxDriverController.rightBumper();
  private final Trigger dLeftTrigger = xboxDriverController.leftTrigger();
  private final Trigger dRightTrigger = xboxDriverController.rightTrigger();
  private final Trigger dPOVDown = xboxDriverController.povDown();
  private final Trigger dPOVUp = xboxDriverController.povUp();
  private final Trigger dPOVLeft = xboxDriverController.povLeft();
  private final Trigger dPOVRight = xboxDriverController.povRight();

  /* Operator Buttons */
  private final Trigger opStart = xboxOperatorController.start();
  private final Trigger opBack = xboxOperatorController.back();
  private final Trigger opY = xboxOperatorController.y();
  private final Trigger opB = xboxOperatorController.b();
  private final Trigger opA = xboxOperatorController.a();
  private final Trigger opX = xboxOperatorController.x();
  private final Trigger opLeftBumper = xboxOperatorController.leftBumper();
  private final Trigger opRightBumper = xboxOperatorController.rightBumper();
  private final Trigger opLeftTrigger = xboxOperatorController.leftTrigger();
  private final Trigger opRightTrigger = xboxOperatorController.rightTrigger();
  private final Trigger opPOVDown = xboxOperatorController.povDown();
  private final Trigger opPOVUp = xboxOperatorController.povUp();
  private final Trigger opPOVLeft = xboxOperatorController.povLeft();
  private final Trigger opPOVRight = xboxOperatorController.povRight();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    switch (Constants.currentMode) {
      case REAL:
        // Real robot, instantiate hardware IO implementations
        drive =
            new Drive(
                new GyroIOPigeon2(),
                new ModuleIOTalonFX(TunerConstants.FrontLeft),
                new ModuleIOTalonFX(TunerConstants.FrontRight),
                new ModuleIOTalonFX(TunerConstants.BackLeft),
                new ModuleIOTalonFX(TunerConstants.BackRight));
        break;

      case SIM:
        // Sim robot, instantiate physics sim IO implementations
        drive =
            new Drive(
                new GyroIO() {},
                new ModuleIOSim(TunerConstants.FrontLeft),
                new ModuleIOSim(TunerConstants.FrontRight),
                new ModuleIOSim(TunerConstants.BackLeft),
                new ModuleIOSim(TunerConstants.BackRight));
        break;

      default:
        // Replayed robot, disable IO implementations
        drive =
            new Drive(
                new GyroIO() {},
                new ModuleIO() {},
                new ModuleIO() {},
                new ModuleIO() {},
                new ModuleIO() {});
        break;
    }

    registerNamedCommands();

    // Set up auto routines
    autoChooser = new LoggedDashboardChooser<>("Auto Choices", AutoBuilder.buildAutoChooser());

    // Set up SysId routines
    autoChooser.addOption(
        "Drive Wheel Radius Characterization", DriveCommands.wheelRadiusCharacterization(drive));
    autoChooser.addOption(
        "Drive Simple FF Characterization", DriveCommands.feedforwardCharacterization(drive));
    autoChooser.addOption(
        "Drive SysId (Quasistatic Forward)",
        drive.sysIdQuasistatic(SysIdRoutine.Direction.kForward));
    autoChooser.addOption(
        "Drive SysId (Quasistatic Reverse)",
        drive.sysIdQuasistatic(SysIdRoutine.Direction.kReverse));
    autoChooser.addOption(
        "Drive SysId (Dynamic Forward)", drive.sysIdDynamic(SysIdRoutine.Direction.kForward));
    autoChooser.addOption(
        "Drive SysId (Dynamic Reverse)", drive.sysIdDynamic(SysIdRoutine.Direction.kReverse));

    // Configure the button bindings
    configureButtonBindings();
  }

  private void registerNamedCommands() {
    NamedCommands.registerCommand(
        "Score-Coral",
        new SequentialCommandGroup(
            new InstantCommand(() -> slapdownSubsystem.angleIntake(Constants.Slapdown.SlapdownOut)),
            new InstantCommand(() -> elevatorSubsystem.moveElevator(Constants.Elevator.ElevatorL4)),
            new WaitUntilCommand(
                () -> Elevator.ElevatorL4 - elevatorSubsystem.getElevatorPosition() <= 0.15),
            new InstantCommand(() -> coralManipulatorSubsystem.intake()),
            new WaitCommand(2),
            new InstantCommand(() -> coralManipulatorSubsystem.stopMotors()),
            new InstantCommand(
                () -> elevatorSubsystem.moveElevator(Constants.Elevator.ElevatorHome))));

    NamedCommands.registerCommand(
        "Coral-Intake",
        new SequentialCommandGroup(
            new InstantCommand(() -> coralManipulatorSubsystem.intake()),
            new WaitUntilCommand(() -> coralManipulatorSubsystem.coralDetected() == true),
            new WaitCommand(0.075),
            new InstantCommand(() -> coralManipulatorSubsystem.stopMotors())));

    NamedCommands.registerCommand(
        "Reset-Gyro",
        // Commands.runOnce(
        //         () -> drive.setPose(new Pose2d(drive.getPose().getTranslation(), new
        // Rotation2d())),
        //         drive)
        //     .ignoringDisable(true));
        new InstantCommand(
                () -> drive.setPose(new Pose2d(drive.getPose().getTranslation(), new Rotation2d())),
                drive)
            .ignoringDisable(true));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    dY.onTrue(
        new SequentialCommandGroup(
            new InstantCommand(() -> slapdownSubsystem.angleIntake(Slapdown.SlapdownOut)),
            new InstantCommand(() -> elevatorSubsystem.moveElevator(Elevator.ElevatorL4))));
    dX.onTrue(
        new SequentialCommandGroup(
            new InstantCommand(() -> slapdownSubsystem.angleIntake(Slapdown.SlapdownOut)),
            new InstantCommand(() -> elevatorSubsystem.moveElevator(Elevator.ElevatorL3))));
    dA.onTrue(
        new SequentialCommandGroup(
            new InstantCommand(() -> slapdownSubsystem.angleIntake(Slapdown.SlapdownOut)),
            new InstantCommand(() -> elevatorSubsystem.moveElevator(Elevator.ElevatorL2))));
    dB.onTrue(
        new SequentialCommandGroup(
            new InstantCommand(() -> slapdownSubsystem.angleIntake(Slapdown.SlapdownOuttakeBarge)),
            new InstantCommand(() -> elevatorSubsystem.moveElevator(Elevator.ElevatorBarge))));

    dRightBumper.onTrue(
        new SequentialCommandGroup(
            new InstantCommand(() -> coralManipulatorSubsystem.intake()),
            new WaitUntilCommand(() -> coralManipulatorSubsystem.coralDetected()),
            new WaitCommand(0.3),
            new InstantCommand(() -> coralManipulatorSubsystem.stopMotors())));

    dLeftBumper.onTrue(
        new SequentialCommandGroup(
            new InstantCommand(() -> coralManipulatorSubsystem.intake()),
            new WaitCommand(0.5),
            new InstantCommand(() -> coralManipulatorSubsystem.stopMotors()),
            new InstantCommand(() -> elevatorSubsystem.moveElevator(Elevator.ElevatorHome))));

    dRightTrigger.onTrue(
        new SequentialCommandGroup(
            new InstantCommand(() -> slapdownSubsystem.intakeRollers()),
            new WaitUntilCommand(() -> slapdownSubsystem.algaeDetected()),
            new WaitCommand(0.1),
            new InstantCommand(() -> slapdownSubsystem.stopRollers())));

    dLeftTrigger.onTrue(
        new SequentialCommandGroup(
            new InstantCommand(() -> slapdownSubsystem.outakeRollers()),
            new WaitCommand(0.5),
            new InstantCommand(() -> slapdownSubsystem.stopRollers())));

    dPOVUp.onTrue(new InstantCommand(() -> slapdownSubsystem.angleIntake(Slapdown.SlapdownOut)));

    dPOVDown.onTrue(
        new InstantCommand(() -> slapdownSubsystem.angleIntake(Slapdown.SlapdownGroundIntake)));

    // Default command, normal field-relative drive
    // if (DriverStation.getAlliance().get() == Alliance.Blue) {
    //   drive.setDefaultCommand(
    //       DriveCommands.joystickDrive(
    //           drive,
    //           () -> xboxDriverController.getLeftY(),
    //           () -> xboxDriverController.getLeftX(),
    //           () -> xboxDriverController.getRightX()));
    // } else if (DriverStation.getAlliance().get() == Alliance.Red) {
    //   drive.setDefaultCommand(
    //       DriveCommands.joystickDrive(
    //           drive,
    //           () -> xboxDriverController.getLeftY(),
    //           () -> xboxDriverController.getLeftX(),
    //           () -> xboxDriverController.getRightX()));
    // }

    drive.setDefaultCommand(
        DriveCommands.joystickDrive(
            drive,
            () -> -xboxDriverController.getLeftY(),
            () -> -xboxDriverController.getLeftX(),
            () -> -xboxDriverController.getRightX()));

    // Lock to 0° when A button is held
    // xboxDriverController
    //     .povDown()
    //     .whileTrue(
    //         DriveCommands.joystickDriveAtAngle(
    //             drive,
    //             () -> -xboxDriverController.getLeftY(),
    //             () -> -xboxDriverController.getLeftX(),
    //             () -> new Rotation2d()));

    // Switch to X pattern when X button is pressed
    // xboxDriverController.x().onTrue(Commands.runOnce(drive::stopWithX, drive));

    dPOVRight.onTrue(
        Commands.runOnce(
                () -> drive.setPose(new Pose2d(drive.getPose().getTranslation(), new Rotation2d())),
                drive)
            .ignoringDisable(true));

    dPOVLeft.onTrue(new InstantCommand(() -> elevatorSubsystem.resetPosition()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.get();
  }

  public void teleopInit() {
    slapdownSubsystem.angleIntake(Slapdown.SlapdownOut);
  }
}
