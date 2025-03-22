package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.State.Sequence;
import frc.robot.State.SequenceCommand;
import frc.robot.State.SequenceFunctions;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.*;

public class ScoreCoralCommand extends Command {

  private ElevatorSubsystem elevatorSubsystem;
  private SlapdownSubsystem slapdownSubsystem;
  private CoralManipulatorSubsystem coralManipulatorSubsystem;

  private SequenceCommand sequenceCommand =
      new SequenceCommand(elevatorSubsystem, slapdownSubsystem, coralManipulatorSubsystem);

  private boolean commandFinished = false;

  public ScoreCoralCommand(
      ElevatorSubsystem elevatorSubsystem,
      SlapdownSubsystem slapdownSubsystem,
      CoralManipulatorSubsystem coralManipulatorSubsystem,
      SequenceCommand sequenceCommand) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.slapdownSubsystem = slapdownSubsystem;
    this.coralManipulatorSubsystem = coralManipulatorSubsystem;
    this.sequenceCommand = sequenceCommand;
    addRequirements(elevatorSubsystem, slapdownSubsystem, coralManipulatorSubsystem);
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
        sequenceCommand.outtakeCoral();
        break;
      case 4:
        sequenceCommand.waitFor(0.5);
        break;
      case 5:
        sequenceCommand.stopCoralManipulators();
        break;
      case 6:
        sequenceCommand.elevatorHome();
        break;
      case 7:
        sequenceCommand.moveSlapdownUp();
        break;
      case 8:
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
