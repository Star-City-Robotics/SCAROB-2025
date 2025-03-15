package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.state.sequencer.Sequence;
import frc.robot.state.sequencer.SequenceInput;
import frc.robot.state.sequencer.SequenceManager;
import frc.robot.state.sequencer.SequenceStateMachine;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.CoralManipulatorSubsystem;
import frc.robot.subsystems.intake.SlapdownSubsystem;

public class ResetSequenceCommand extends Command {
  SequenceStateMachine m_scoreStateMachine;
  ElevatorSubsystem m_elevatorSubsystem;
  SlapdownSubsystem m_slapdownSubsystem;
  CoralManipulatorSubsystem m_coralManipulatorSubsystem;
  boolean m_sequenceDone = false;

  private CommandCallback stateMachineCallback =
      () -> {
        System.out.println("ResetSequenceCommand: Sequence notified that it is complete");
        m_sequenceDone = true;
      };

  // public ResetSequenceCommand(ElevatorSubsystem elevatorSubsystem, ArmSubsystem armSubsystem,
  // HandClamperSubsystem clamperSubsystem, HandIntakeSubsystem intakeSubsystem) {
  //     m_scoreStateMachine = SequenceManager.getStateMachine(elevatorSubsystem, armSubsystem,
  // clamperSubsystem, intakeSubsystem);
  //     m_elevatorSubsystem = elevatorSubsystem;
  //     m_armSubsystem = armSubsystem;
  //     m_clamperSubsystem = clamperSubsystem;
  //     m_intakeSubsystem = intakeSubsystem;
  //     addRequirements(m_elevatorSubsystem, m_armSubsystem, m_clamperSubsystem,
  // m_intakeSubsystem);
  // }
  public ResetSequenceCommand(ElevatorSubsystem elevatorSubsystem, SlapdownSubsystem slapdownSubsystem, CoralManipulatorSubsystem coralManipulatorSubsystem) {
    m_scoreStateMachine = SequenceManager.getStateMachine(elevatorSubsystem, slapdownSubsystem, coralManipulatorSubsystem);
    m_elevatorSubsystem = elevatorSubsystem;
    m_slapdownSubsystem = slapdownSubsystem;
    m_coralManipulatorSubsystem = coralManipulatorSubsystem;
    addRequirements(m_elevatorSubsystem, m_slapdownSubsystem, m_coralManipulatorSubsystem);
  }

  @Override
  public void initialize() {
    m_sequenceDone = false;
    System.out.println("ResetSequenceCommand: running reset sequence...");
    m_scoreStateMachine.setCallback(stateMachineCallback);
    m_scoreStateMachine.setSequence(Sequence.RESET);
    m_scoreStateMachine.setInput(SequenceInput.BEGIN);
  }

  @Override
  public void end(boolean interrupted) {
    if (!m_sequenceDone) {
      System.out.println("ResetSequenceCommand: command interrupted");
      m_scoreStateMachine.setCallback(null); // command ending, nothing to callback to
      m_sequenceDone = true;
    }
  }

  @Override
  public boolean isFinished() {
    return m_sequenceDone;
  }
}
