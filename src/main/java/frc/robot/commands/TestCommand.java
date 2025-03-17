package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.State.Sequence;
import frc.robot.State.SequenceCommand;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.*;

public class TestCommand extends Command {

  private ElevatorSubsystem elevatorSubsystem;
  private SlapdownSubsystem slapdownSubsystem;

  private SequenceCommand sequenceCommand = new SequenceCommand(elevatorSubsystem, slapdownSubsystem);

  private boolean commandFinished = false;

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
    Sequence.setNumber(1);
    commandFinished = false;
  }

  @Override
  public void execute() {

    switch (Sequence.number) {
      case 1:
        sequenceCommand.moveSlapdownOut();
      case 2:
        sequenceCommand.raiseElevator(31.0);
      case 3:
        commandFinished = true;
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
