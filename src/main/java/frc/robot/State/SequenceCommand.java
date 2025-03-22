package frc.robot.State;

import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.SlapdownSubsystem;

public class SequenceCommand {

  private final ElevatorSubsystem elevatorSubsystem;
  private final SlapdownSubsystem slapdownSubsystem;

  private final double slapdownOutPosition = SequenceConstants.Slapdown.OUT;

  public SequenceCommand(ElevatorSubsystem elevatorSubsystem, SlapdownSubsystem slapdownSubsystem) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.slapdownSubsystem = slapdownSubsystem;
    // addRequirements(elevatorSubsystem, slapdownSubsystem);
  }

  public void moveSlapdownOut() {
    slapdownSubsystem.angleIntake(slapdownOutPosition);
    // System.out.println("SLAPDOWNNNNNNNNNN: " + slapdownSubsystem.getIntakePosition());
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
