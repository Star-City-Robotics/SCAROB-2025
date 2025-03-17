package frc.robot.State;

import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.SlapdownSubsystem;

public class SequenceCommand {

  private final ElevatorSubsystem elevatorSubsystem;
  private final SlapdownSubsystem slapdownSubsystem;

  public SequenceCommand(ElevatorSubsystem elevatorSubsystem, SlapdownSubsystem slapdownSubsystem) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.slapdownSubsystem = slapdownSubsystem;
    //addRequirements(elevatorSubsystem, slapdownSubsystem);
  }

  public void raiseElevator(Double position, Sequence.Input nextInput) {
    elevatorSubsystem.moveElevator(position);
    if (elevatorSubsystem.getElevatorPosition() == position) {
      SequenceFunctions.setState(Sequence.State.ElEVATOR_RAISED);
      SequenceFunctions.setInput(nextInput);
    }
  }

  public void moveSlapdownOut(Sequence.Input nextInput) {
    slapdownSubsystem.angleIntake(SequenceConstants.Slapdown.SLAPDOWN_UP_POSITION);
    if (slapdownSubsystem.getIntakePosition() == SequenceConstants.Slapdown.SLAPDOWN_UP_POSITION) {
      SequenceFunctions.setState(Sequence.State.SLAPDOWN_OUT);
      SequenceFunctions.setInput(nextInput);
    }
  }
}
