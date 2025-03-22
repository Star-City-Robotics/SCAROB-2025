package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.State.Sequence;
import frc.robot.State.SequenceCommand;
import frc.robot.State.SequenceFunctions;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.*;

public class TestCommand extends Command {

  private ElevatorSubsystem elevatorSubsystem;
  private SlapdownSubsystem slapdownSubsystem;

  private SequenceCommand sequenceCommand = new SequenceCommand(elevatorSubsystem, slapdownSubsystem);

  private boolean commandFinished = false;
  private Sequence.State state = SequenceFunctions.getState();
  private Sequence.Input input = SequenceFunctions.getInput();

  public TestCommand(
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
    SequenceFunctions.setState(Sequence.State.HOME);
    SequenceFunctions.setInput(Sequence.Input.BEGIN);
  }

  @Override
  public void execute() {
    if (SequenceFunctions.checkState(Sequence.State.HOME) == true && SequenceFunctions.checkInput(Sequence.Input.BEGIN) == true) {
      sequenceCommand.moveSlapdownOut(Sequence.Input.RAISE_ELEVATOR);
    }
    else if (state == Sequence.State.SLAPDOWN_OUT && input == Sequence.Input.RAISE_ELEVATOR) {
      sequenceCommand.raiseElevator(31.0, Sequence.Input.FINISHED);
    }
    else if (state == Sequence.State.ElEVATOR_RAISED && input == Sequence.Input.FINISHED) {
      commandFinished = true;
    }
  }

  @Override
  public boolean isFinished() {
    if (commandFinished == true) {
      return true;
    }
    return false;
  }

  @Override
  public void end(boolean interupted) {
    SequenceFunctions.setState(Sequence.State.HOME);
    SequenceFunctions.setInput(null);
    commandFinished = false;
  }
}
