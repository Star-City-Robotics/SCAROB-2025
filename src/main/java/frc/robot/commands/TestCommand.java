package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.State.Sequence;
import frc.State.SequenceCommand;
import frc.State.SequenceFunctions;
import frc.State.Sequence.State;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.*;

public class TestCommand extends Command {

  private final ElevatorSubsystem elevatorSubsystem;
  private final SlapdownSubsystem slapdownSubsystem;

  private boolean commandFinished = false;

  public TestCommand(ElevatorSubsystem elevatorSubsystem, SlapdownSubsystem slapdownSubsystem) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.slapdownSubsystem = slapdownSubsystem;
    addRequirements(elevatorSubsystem, slapdownSubsystem);
  }

  @Override
  public void initialize() {
    SequenceFunctions.setState(Sequence.State.HOME);
    SequenceFunctions.setInput(Sequence.Input.BEGIN);
  }

  @Override
  public void execute() {

    if (
      SequenceFunctions.getState() == Sequence.State.HOME && 
      SequenceFunctions.getInput() == Sequence.Input.BEGIN) {
        SequenceCommand.moveSlapdownOut(Sequence.Input.BEGIN, Sequence.Input.RAISE_ELEVATOR);
    }
    if (
      SequenceFunctions.getState() == Sequence.State.SLAPDOWN_OUT &&
      SequenceFunctions.getInput() == Sequence.Input.RAISE_ELEVATOR) {
        SequenceCommand.raiseElevator(31.0, Sequence.Input.RAISE_ELEVATOR, Sequence.Input.FINISHED);
      }
    if (
      SequenceFunctions.getState() == Sequence.State.ElEVATOR_RAISED &&
      SequenceFunctions.getInput() == Sequence.Input.FINISHED) {
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