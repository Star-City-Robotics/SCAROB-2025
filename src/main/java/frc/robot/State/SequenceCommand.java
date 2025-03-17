package frc.robot.State;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.SlapdownSubsystem;

public class SequenceCommand {

  private final ElevatorSubsystem elevatorSubsystem;
  private final SlapdownSubsystem slapdownSubsystem;

  private final double slapdownOutPosition = SequenceConstants.Slapdown.SLAPDOWN_OUT_POSITION;

  public SequenceCommand(ElevatorSubsystem elevatorSubsystem, SlapdownSubsystem slapdownSubsystem) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.slapdownSubsystem = slapdownSubsystem;
    //addRequirements(elevatorSubsystem, slapdownSubsystem);
  }

  public void moveSlapdownOut() {
    slapdownSubsystem.angleIntake(slapdownOutPosition);
    if(slapdownSubsystem.getIntakePosition() == slapdownOutPosition) {
        Sequence.number = Sequence.number + 1;
    }
  }

  public void raiseElevator(Double position) {
    elevatorSubsystem.moveElevator(position);
    if (elevatorSubsystem.getElevatorPosition() == position) {
        Sequence.number = Sequence.number + 1;
    }
  }
}
