package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.State.Sequence;
import frc.robot.State.SequenceCommand;
import frc.robot.State.SequenceFunctions;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.*;

public class IntakeCoralCommand extends Command {

  private ElevatorSubsystem elevatorSubsystem;
  private SlapdownSubsystem slapdownSubsystem;
  private CoralManipulatorSubsystem coralManipulatorSubsystem;

  private SequenceCommand sequenceCommand =
      new SequenceCommand(elevatorSubsystem, slapdownSubsystem, coralManipulatorSubsystem);

  private boolean commandFinished = false;

  public IntakeCoralCommand(
      CoralManipulatorSubsystem coralManipulatorSubsystem,
      SequenceCommand sequenceCommand) {
    this.coralManipulatorSubsystem = coralManipulatorSubsystem;
    this.sequenceCommand = sequenceCommand;
    addRequirements(coralManipulatorSubsystem);
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
    Sequence.incrementNumber();
    commandFinished = false;
  }
}
