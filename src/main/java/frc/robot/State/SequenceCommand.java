package frc.robot.State;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.CoralManipulatorSubsystem;
import frc.robot.subsystems.intake.SlapdownSubsystem;

public class SequenceCommand {

  private final ElevatorSubsystem elevatorSubsystem;
  private final SlapdownSubsystem slapdownSubsystem;
  private final CoralManipulatorSubsystem coralManipulatorSubsystem;

  private final double slapdownOutPosition = SequenceConstants.Slapdown.OUT;
  private final double slapdownUpPosition = SequenceConstants.Slapdown.UP;
  private final double slapdownDownPosition = SequenceConstants.Slapdown.OUT;
  private final double slapdownMiddlePosition = SequenceConstants.Slapdown.MIDDLE;

  private final Timer timer = new Timer();

  public SequenceCommand(
      ElevatorSubsystem elevatorSubsystem,
      SlapdownSubsystem slapdownSubsystem,
      CoralManipulatorSubsystem coralManipulatorSubsystem) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.slapdownSubsystem = slapdownSubsystem;
    this.coralManipulatorSubsystem = coralManipulatorSubsystem;
    // addRequirements(elevatorSubsystem, slapdownSubsystem);
  }

  public void moveSlapdownOut() {
    slapdownSubsystem.angleIntake(slapdownOutPosition);
    if (Math.abs(slapdownOutPosition - slapdownSubsystem.getIntakePosition()) <= 2) {
      Sequence.incrementNumber();
    }
  }

  public void moveSlapdownUp() {
    slapdownSubsystem.angleIntake(slapdownUpPosition);
    if (Math.abs(slapdownUpPosition - slapdownSubsystem.getIntakePosition()) <= 2) {
      Sequence.incrementNumber();
    }
  }

  public void moveSlapdownDown() {
    slapdownSubsystem.angleIntake(slapdownDownPosition);
    if (Math.abs(slapdownDownPosition - slapdownSubsystem.getIntakePosition()) <= 2) {
      Sequence.incrementNumber();
    }
  }

  public void moveSlapdownMiddle() {
    slapdownSubsystem.angleIntake(slapdownMiddlePosition);
    if (Math.abs(slapdownMiddlePosition - slapdownSubsystem.getIntakePosition()) <= 2) {
      Sequence.incrementNumber();
    }
  }

  public void raiseElevator(Double position) {
    elevatorSubsystem.moveElevator(position);
    if (elevatorSubsystem.getElevatorPosition() == position) {
      Sequence.incrementNumber();
    }
  }

  public void elevatorHome() {
    elevatorSubsystem.moveElevator(SequenceConstants.Elevator.HOME);
    if (elevatorSubsystem.getElevatorPosition() == SequenceConstants.Elevator.HOME) {
      Sequence.incrementNumber();
    }
  }

  public void intakeAlgae() {
    slapdownSubsystem.intakeRollers();
    if (slapdownSubsystem.detectAlgae()) {
      Sequence.incrementNumber();
    }
  }

  public void stopIntakeMotors() {
    slapdownSubsystem.stopRollers();
    Sequence.incrementNumber();
  }

  public void outtakeAlgae() {
    slapdownSubsystem.outtakeRollers();
    Sequence.incrementNumber();
  }

  public void waitFor(double time) {
    timer.reset();
    timer.start();
    if (timer.hasElapsed(time)) {
      timer.stop();
      Sequence.incrementNumber();
    }
  }

  public void intakeCoral() {
    coralManipulatorSubsystem.intake();
    if (coralManipulatorSubsystem.coralDetected()) {
      Sequence.incrementNumber();
    }
  }

  public void stopCoralManipulators() {
    coralManipulatorSubsystem.stopMotors();
    Sequence.incrementNumber();
  }

  public void outtakeCoral() {
    coralManipulatorSubsystem.intake();
    Sequence.incrementNumber();
  }
}
