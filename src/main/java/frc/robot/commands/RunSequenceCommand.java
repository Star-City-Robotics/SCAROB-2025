package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.State.Sequence;
import frc.robot.State.SequenceCommand;
import frc.robot.State.SequenceFunctions;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.CoralManipulatorSubsystem;
import frc.robot.subsystems.intake.SlapdownSubsystem;

public class RunSequenceCommand extends Command {

  ElevatorSubsystem elevatorSubsystem ;
  SlapdownSubsystem slapdownSubsystem;
  CoralManipulatorSubsystem coralManipulatorSubsystem;
  SequenceCommand sequenceCommand =
      new SequenceCommand(elevatorSubsystem, slapdownSubsystem, coralManipulatorSubsystem);

  private final ScoreCoralCommand ScoreCoralCommand =
      new ScoreCoralCommand(
          elevatorSubsystem, slapdownSubsystem, coralManipulatorSubsystem, sequenceCommand);
  private final ScoreAlgaeProcessorCommand scoreAlgaeProcessorCommand =
      new ScoreAlgaeProcessorCommand(slapdownSubsystem, sequenceCommand);
  private final ScoreAlgaeBargeCommand scoreAlgaeBargeCommand =
      new ScoreAlgaeBargeCommand(elevatorSubsystem, slapdownSubsystem, sequenceCommand);
  private final IntakeCoralCommand intakeCoralCommand =
      new IntakeCoralCommand(coralManipulatorSubsystem, sequenceCommand);
  private final IntakeAglaeReefCommand intakeAglaeReefCommand =
      new IntakeAglaeReefCommand(elevatorSubsystem, slapdownSubsystem, sequenceCommand);
  private final IntakeAlgaeGroundCommand intakeAlgaeGroundCommand =
      new IntakeAlgaeGroundCommand(slapdownSubsystem, sequenceCommand);

  public RunSequenceCommand(
      ElevatorSubsystem elevatorSubsystem,
      SlapdownSubsystem slapdownSubsystem,
      CoralManipulatorSubsystem coralManipulatorSubsystem,
      SequenceCommand sequenceCommand0) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.slapdownSubsystem = slapdownSubsystem;
    this.coralManipulatorSubsystem = coralManipulatorSubsystem;
    this.sequenceCommand = sequenceCommand;
    addRequirements(elevatorSubsystem, slapdownSubsystem, coralManipulatorSubsystem);
  }

  @Override
  public void initialize() {
    // Intake Algae L2 or L3
    if ((SequenceFunctions.checkLevel(Sequence.Level.L2)
            || SequenceFunctions.checkLevel(Sequence.Level.L3))
        && SequenceFunctions.checkGamePiece(Sequence.GamePiece.ALGAE)
        && SequenceFunctions.checkAction(Sequence.Action.INTAKE)) {
      intakeAglaeReefCommand.schedule();
      System.out.print("SEQUENCEEEEE INTAKE ALGAE REEF");
    }

    // Intake Algae ground
    if (SequenceFunctions.checkLevel(Sequence.Level.L1)
        && SequenceFunctions.checkGamePiece(Sequence.GamePiece.ALGAE)
        && SequenceFunctions.checkAction(Sequence.Action.INTAKE)) {
      intakeAlgaeGroundCommand.schedule();
    }

    // Intake Coral
    if (SequenceFunctions.checkGamePiece(Sequence.GamePiece.CORAL)
        && SequenceFunctions.checkAction(Sequence.Action.INTAKE)) {
      intakeCoralCommand.schedule();
    }

    // Score Algae Processor
    if (SequenceFunctions.checkLevel(Sequence.Level.L1)
        && SequenceFunctions.checkGamePiece(Sequence.GamePiece.ALGAE)
        && SequenceFunctions.checkAction(Sequence.Action.SCORE)) {
      scoreAlgaeProcessorCommand.schedule();
    }

    // Score Algae Barge
    if (SequenceFunctions.checkLevel(Sequence.Level.L4)
        && SequenceFunctions.checkGamePiece(Sequence.GamePiece.ALGAE)
        && SequenceFunctions.checkAction(Sequence.Action.SCORE)) {
      scoreAlgaeBargeCommand.schedule();
    }

    // Score Coral all levels
    if (SequenceFunctions.checkGamePiece(Sequence.GamePiece.CORAL)
        && SequenceFunctions.checkAction(Sequence.Action.SCORE)) {
      ScoreCoralCommand.schedule();
    }
  }

  @Override
  public void execute() {}

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interupted) {}
}
