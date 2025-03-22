package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.State.Sequence;
import frc.robot.State.SequenceCommand;
import frc.robot.State.SequenceFunctions;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.*;

public class ScoreAlgaeBargeCommand extends Command {

  private ElevatorSubsystem elevatorSubsystem;
  private SlapdownSubsystem slapdownSubsystem;
  private CoralManipulatorSubsystem coralManipulatorSubsystem;

  private SequenceCommand sequenceCommand =
      new SequenceCommand(elevatorSubsystem, slapdownSubsystem, coralManipulatorSubsystem);

  private boolean commandFinished = false;

  public ScoreAlgaeBargeCommand(
      ElevatorSubsystem elevatorSubsystem,
      SlapdownSubsystem slapdownSubsystem,
      SequenceCommand sequenceCommand) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.slapdownSubsystem = slapdownSubsystem;
    this.sequenceCommand = sequenceCommand;
    addRequirements(elevatorSubsystem, slapdownSubsystem);
  }

  @Override
  public void initialize() {
    Sequence.setNumber(1);
    commandFinished = false;
  }

  @Override
  public void execute() {

    switch (Sequence.number) {
      case 1:
        sequenceCommand.moveSlapdownOut();
        break;
      case 2:
        sequenceCommand.raiseElevator(SequenceFunctions.getElevatorConstant());
        break;
      case 3:
        sequenceCommand.moveSlapdownMiddle();
        break;
      case 4:
        sequenceCommand.outtakeAlgae();
        break;
      case 5:
        sequenceCommand.waitFor(0.5);
        break;
      case 6:
        sequenceCommand.stopIntakeMotors();
        break;
      case 7:
        sequenceCommand.moveSlapdownOut();
        break;
      case 8:
        sequenceCommand.elevatorHome();
        break;
      case 9:
        sequenceCommand.moveSlapdownUp();
        break;
      case 10:
        commandFinished = true;
        break;
    }
  }

  @Override
  public boolean isFinished() {
    if (commandFinished == true) return true;
    return false;
  }

  @Override
  public void end(boolean interupted) {
    Sequence.setNumber(0);
    commandFinished = false;
  }
}
