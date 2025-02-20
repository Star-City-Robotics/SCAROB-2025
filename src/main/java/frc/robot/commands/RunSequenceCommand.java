package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.state.sequencer.SequenceInput;
import frc.robot.state.sequencer.SequenceStateMachine;
import frc.robot.state.sequencer.Sequence;
import frc.robot.state.sequencer.SequenceManager;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;

public class RunSequenceCommand extends Command {
    SequenceStateMachine m_scoreStateMachine;
    ElevatorSubsystem m_elevatorSubsystem;
    boolean m_sequenceStarted = false;
    boolean m_sequenceDone = false;

    private CommandCallback stateMachineCallback = () -> {
        System.out.println("RunSequenceCommand: Sequence notified that it is complete");
        m_sequenceDone = true;
    };

    // public RunSequenceCommand(ElevatorSubsystem elevatorSubsystem, ArmSubsystem armSubsystem, HandClamperSubsystem clamperSubsystem, HandIntakeSubsystem intakeSubsystem) {
    //     m_scoreStateMachine = SequenceManager.getStateMachine(elevatorSubsystem, armSubsystem, clamperSubsystem, intakeSubsystem);
    //     m_elevatorSubsystem = elevatorSubsystem;
    //     m_armSubsystem = armSubsystem;
    //     m_clamperSubsystem = clamperSubsystem;
    //     m_intakeSubsystem = intakeSubsystem;
    //     addRequirements(m_elevatorSubsystem, m_armSubsystem, m_clamperSubsystem, m_intakeSubsystem);
    // }
    public RunSequenceCommand(ElevatorSubsystem elevatorSubsystem) {
        m_scoreStateMachine = SequenceManager.getStateMachine(elevatorSubsystem);
        m_elevatorSubsystem = elevatorSubsystem;
        addRequirements(m_elevatorSubsystem);
    }

    public void runStateMachine() {
        System.out.println("RunSequenceCommand: running score command state machine");
        m_sequenceStarted = true;
        m_scoreStateMachine.setCallback(stateMachineCallback);
        Sequence sequence = SequenceManager.getSequence();
        if(sequence == null) {
            System.out.println("RunSequenceCommand: Sequence not found for the combination of " + 
                SequenceManager.getGamePieceSelection() + " " + 
                SequenceManager.getLevelSelection() + " " + 
                SequenceManager.getActionSelection() + " " + 
                "- Exiting command");
            m_sequenceDone = true;
            return;
        }

        System.out.println("RunSequenceCommand: Sequence chosen " + sequence + " " + 
            SequenceManager.getGamePieceSelection() + " " + 
            SequenceManager.getLevelSelection() + " " + 
            SequenceManager.getActionSelection());
        m_scoreStateMachine.setSequence(sequence);
        m_scoreStateMachine.setInput(SequenceInput.BEGIN);
    }

    @Override
    public void initialize() {
        m_sequenceStarted = false;
        m_sequenceDone = false;
        if(m_scoreStateMachine.isReady()) {
            runStateMachine();
        }
    }

    @Override
    public void execute() {
        if(!m_sequenceStarted && m_scoreStateMachine.isReady()) {
            runStateMachine();
        }
    }

    @Override
    public void end(boolean interrupted) {
        if(!m_sequenceDone) {
            System.out.println("RunSequenceCommand: command interrupted");
            m_scoreStateMachine.setInput(SequenceInput.BUTTON_RELEASED);
            m_scoreStateMachine.setCallback(null); // command ending, nothing to callback to
            m_sequenceDone = true;
        }
    }

    @Override
    public boolean isFinished() {
        return m_sequenceDone;
    }
}
