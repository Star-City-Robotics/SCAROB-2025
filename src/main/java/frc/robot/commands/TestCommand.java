package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.State.Sequence;
import frc.State.SequenceFunctions;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.*;

public class TestCommand extends Command {

  private final ElevatorSubsystem elevatorSubsystem;
  private final SlapdownSubsystem slapdownSubsystem;

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
    
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interupted) {
    
  }
}