package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.State.Sequence;
import frc.robot.State.SequenceCommand;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.*;

public class ScoreAlgaeProcessorCommand extends Command {

  private ElevatorSubsystem elevatorSubsystem;
  private SlapdownSubsystem slapdownSubsystem;
  private CoralManipulatorSubsystem coralManipulatorSubsystem;

  private SequenceCommand sequenceCommand =
      new SequenceCommand(elevatorSubsystem, slapdownSubsystem, coralManipulatorSubsystem);

  private boolean commandFinished = false;

  public ScoreAlgaeProcessorCommand(
      SlapdownSubsystem slapdownSubsystem,
      SequenceCommand sequenceCommand) {
    this.slapdownSubsystem = slapdownSubsystem;
    this.sequenceCommand = sequenceCommand;
    addRequirements(slapdownSubsystem);
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
        sequenceCommand.moveSlapdownMiddle();
        break;
      case 2:
        sequenceCommand.outtakeAlgae();
        break;
      case 3: 
        sequenceCommand.waitFor(0.5);
        break;
      case 4:
        sequenceCommand.stopIntakeMotors();
        break;
      case 5:
        sequenceCommand.moveSlapdownUp();
        break;
      case 6:
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
