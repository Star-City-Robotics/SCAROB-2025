package frc.robot.State;

import frc.robot.Constants.CoralManipulator;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.CoralManipulatorSubsystem;
import frc.robot.subsystems.intake.SlapdownSubsystem;

public class SequenceCommand {

  private final ElevatorSubsystem elevatorSubsystem;
  private final SlapdownSubsystem slapdownSubsystem;
  private final CoralManipulatorSubsystem coralManipulatorSubsystem;

  private final double slapdownOutPosition = SequenceConstants.Slapdown.OUT;

  public SequenceCommand(ElevatorSubsystem elevatorSubsystem, SlapdownSubsystem slapdownSubsystem, CoralManipulatorSubsystem coralManipulatorSubsystem) {
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

  public void raiseElevator(Double position) {
    elevatorSubsystem.moveElevator(position);
    if (elevatorSubsystem.getElevatorPosition() == position) {
      Sequence.incrementNumber();
    }
  }
}
