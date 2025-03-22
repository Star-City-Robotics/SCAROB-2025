package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.State.Sequence;
import frc.robot.State.SequenceCommand;
import frc.robot.State.SequenceFunctions;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.CoralManipulatorSubsystem;
import frc.robot.subsystems.intake.SlapdownSubsystem;

public class RunSequenceCommand extends Command {

  // ElevatorSubsystem elevatorSubsystem;
  // SlapdownSubsystem slapdownSubsystem;
  // CoralManipulatorSubsystem coralManipulatorSubsystem;

  // SequenceCommand sequenceCommand =
  //   new SequenceCommand(elevatorSubsystem, slapdownSubsystem, coralManipulatorSubsystem);

  ScoreCoralCommand scoreCoralCommand;
  ScoreAlgaeProcessorCommand scoreAlgaeProcessorCommand;
  ScoreAlgaeBargeCommand scoreAlgaeBargeCommand;
  IntakeCoralCommand intakeCoralCommand;
  IntakeAglaeReefCommand intakeAglaeReefCommand;
  IntakeAlgaeGroundCommand intakeAlgaeGroundCommand;

  // private ScoreCoralCommand scoreCoralCommand =
  //     new ScoreCoralCommand(
  //         elevatorSubsystem, slapdownSubsystem, coralManipulatorSubsystem, sequenceCommand);
  // private ScoreAlgaeProcessorCommand scoreAlgaeProcessorCommand =
  //     new ScoreAlgaeProcessorCommand(slapdownSubsystem, sequenceCommand);
  // private ScoreAlgaeBargeCommand scoreAlgaeBargeCommand =
  //     new ScoreAlgaeBargeCommand(elevatorSubsystem, slapdownSubsystem, sequenceCommand);
  // private IntakeCoralCommand intakeCoralCommand =
  //     new IntakeCoralCommand(coralManipulatorSubsystem, sequenceCommand);
  // private IntakeAglaeReefCommand intakeAglaeReefCommand =
  //     new IntakeAglaeReefCommand(elevatorSubsystem, slapdownSubsystem, sequenceCommand);
  // private IntakeAlgaeGroundCommand intakeAlgaeGroundCommand =
  //     new IntakeAlgaeGroundCommand(slapdownSubsystem, sequenceCommand);

  // public RunSequenceCommand(
      //   ElevatorSubsystem elevatorSubsystem,
      //   SlapdownSubsystem slapdownSubsystem,
      //   CoralManipulatorSubsystem coralManipulatorSubsystem,
      //   SequenceCommand sequenceCommand0) {
      // this.elevatorSubsystem = elevatorSubsystem;
      // this.slapdownSubsystem = slapdownSubsystem;
      // this.coralManipulatorSubsystem = coralManipulatorSubsystem;
      // this.sequenceCommand = sequenceCommand;
      // addRequirements(elevatorSubsystem, slapdownSubsystem, coralManipulatorSubsystem);
      // ) {}

  public RunSequenceCommand(
    ScoreCoralCommand scoreCoralCommand,
    ScoreAlgaeBargeCommand scoreAlgaeBargeCommand,
    ScoreAlgaeProcessorCommand scoreAlgaeProcessorCommand,
    IntakeCoralCommand intakeCoralCommand,
    IntakeAglaeReefCommand intakeAglaeReefCommand,
    IntakeAlgaeGroundCommand intakeAlgaeGroundCommand) {
      this.scoreCoralCommand = scoreCoralCommand;
      this.scoreAlgaeProcessorCommand = scoreAlgaeProcessorCommand;
      this.intakeCoralCommand = intakeCoralCommand;
      this.intakeAglaeReefCommand = intakeAglaeReefCommand;
      this.intakeAlgaeGroundCommand = intakeAlgaeGroundCommand;
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
      //intakeAlgaeGroundCommand.schedule();
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
      scoreCoralCommand.schedule();
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
