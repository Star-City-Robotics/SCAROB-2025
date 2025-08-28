package frc.robot.state.sequencer;

import frc.robot.state.Input;
import frc.robot.state.StateMachine;
import frc.robot.state.StateMachineCallback;
import frc.robot.state.sequencer.positions.Positions;
import frc.robot.subsystems.Elevator.*;

public class SequenceStateMachine extends StateMachine {

  // subsystems
  private ElevatorSubsystem elevatorSubsystem;

  // sequence tracking
  private Sequence currentSequence;
  private Action currentAction;
  private GamePiece currentGamePiece;
  private Level updatedLevel;
  private Positions positions;

  // reset/abort tracking
  private boolean isResetting = false;
  private boolean elevatorResetDone = false;
  private boolean armResetDone = false;

  //  public SequenceStateMachine(ElevatorSubsystem elevatorSubsystem, ArmSubsystem armSubsystem,
  // HandClamperSubsystem handClamperSubsystem, HandIntakeSubsystem handIntakeSubsystem) { //add
  // hand here
  // this.elevatorSubsystem = elevatorSubsystem;
  // this.armSubsystem = armSubsystem;
  // this.handClamperSubsystem = handClamperSubsystem;
  // this.handIntakeSubsystem = handIntakeSubsystem;
  // setCurrentState(SequenceState.HOME);
  // }

  /**
   * @param elevatorSubsystem
   */
  public SequenceStateMachine(ElevatorSubsystem elevatorSubsystem) { // add hand here
    this.elevatorSubsystem = elevatorSubsystem;
    setCurrentState(SequenceState.HOME);
  }

  /*
   * COMMAND INTERFACE
   */

  /**
   * @return
   */
  public boolean isReady() {
    return currentState == SequenceState.HOME;
  }

  /**
   * @param sequence
   */
  public void setSequence(Sequence sequence) {
    currentSequence = sequence;
    currentAction = SequenceManager.getActionSelection();
    currentGamePiece = SequenceManager.getGamePieceSelection();
    // the sequence determines the choreographed movement of elevator/arm/hand
    setStateTransitionTable(
        SequenceFactory.getTransitionTable(sequence)); // state machine transitions
    positions = SequenceFactory.getPositions(sequence); // position constants for subsystems

    // on reset, put it into special state to kick off reset
    // may be in an incomplete state from a previous sequence
    if (sequence == Sequence.RESET) setCurrentState(SequenceState.INIT_RESET);
  }

  /*
   * SUBSYSTEM INTERFACE
   */

  /**
   * @param input
   */
  protected void handleSubsystemCallback(Input input) {
    if (isResetting) {
      SequenceInput sequenceInput = (SequenceInput) input;
      if (sequenceInput == SequenceInput.ELEVATOR_THRESHOLD_MET) setInput(input); // not home yet
      if (sequenceInput == SequenceInput.ELEVATOR_DONE) elevatorResetDone = true;
      if (elevatorResetDone) {
        isResetting = false;
        setInput(SequenceInput.RESET_DONE);
      }
    } else {
      setInput(input);
    }
  }

  /**
   * @return
   */
  public StateMachineCallback getSubsystemCallback() {
    return subsystemCallback;
  }

  /*
   * STATE OPERATION METHODS
   */

  /**
   * @return
   */
  public boolean raiseElevator() {
    elevatorSubsystem.moveElevator(
        positions.raiseElevatorPosition, subsystemCallback, positions.raiseElevatorThreshold);
    return true;
  }

  // Add intakecode

  /**
   * @return
   */
  public boolean outTaking() {
    return true;
  }

  /**
   * @return
   */
  public boolean raiseElevatorNoThreshold() {
    elevatorSubsystem.moveElevator(positions.raiseElevatorPosition, subsystemCallback);
    return true;
  }

  /**
   * @return
   */
  public boolean elevatorSecondStage() {
    elevatorSubsystem.moveElevator(positions.secondStageElevatorPosition, subsystemCallback);
    return true;
  }
  /**
   * @return
   */
  public boolean moveElevatorHome() {
    isResetting = true;
    elevatorSubsystem.moveElevator(
        ElevatorConstants.elevatorHomePosition,
        subsystemCallback,
        positions.lowerElevatorThreshold);
    return true;
  }

  // public boolean moveArmForward() {
  //     armSubsystem.moveArm(positions.armForwardPosition, subsystemCallback);
  //     return true;
  // }

  // public boolean checkIfShouldScore() {
  //     // TODO put autonomous handling in here when ready
  //     handIntakeSubsystem.watchForScoreDetection(subsystemCallback);
  //     return true;
  // }

  // public boolean moveArmToScore() {
  //     armSubsystem.moveArm(positions.armScoringPosition, subsystemCallback);
  //     return true;
  // }

  // public boolean moveArmHome() {
  //     armSubsystem.moveArm(ArmConstants.armHomePosition, subsystemCallback);
  //     return true;
  // }

  // public boolean closeHand() {
  //     handClamperSubsystem.close();
  //     return true;
  // }

  // public boolean returnHandToDefault() {
  //     handClamperSubsystem.close();
  //     handIntakeSubsystem.stop();
  //     return true;
  // }

  // public boolean prepareToIntake() {
  //     handClamperSubsystem.open(positions.clamperIntakePosition);
  //     handIntakeSubsystem.intake(HandConstants.intakeVelocity, subsystemCallback);
  //     return true;
  // }

  // public boolean stopIntaking() {
  //     handClamperSubsystem.close();
  //     handIntakeSubsystem.stop(subsystemCallback);
  //     return true;
  // }

  // public boolean holdAndLower() {
  //     handClamperSubsystem.moveHand(positions.clamperHoldPosition);
  //     elevatorSubsystem.moveElevator(ElevatorConstants.elevatorHomePosition, subsystemCallback);
  //     return true;
  // }

  // public boolean releasePiece() {
  //     handIntakeSubsystem.stop();
  //     handIntakeSubsystem.release(HandConstants.intakeVelocity,
  // HandConstants.defaultReleaseRuntime);
  //     return true;
  // }

  // public boolean shootToScore() {
  //     handIntakeSubsystem.release(HandConstants.scoreAlgaeVelocity,
  // HandConstants.defaultReleaseRuntime, subsystemCallback);
  //     return true;
  // }

  // Drive the elevator to a new position when the operator overrides it midstream

  /**
   * @return
   */
  public boolean updateElevator() {
    // Find the new elevator position by using the new level with the current action and game piece
    // Sequence updatedLevelSequence = SequenceFactory.getSequence(updatedLevel, currentGamePiece,
    // currentAction);
    Sequence updatedLevelSequence = SequenceFactory.getSequence(updatedLevel);
    Positions updatedLevelPositions = SequenceFactory.getPositions(updatedLevelSequence);
    // Drive to the updated position
    elevatorSubsystem.moveElevator(updatedLevelPositions.raiseElevatorPosition, subsystemCallback);
    return true;
  }

  // // Used to return the arm home (and stop intake) before driving the elevator to a new position
  // public boolean returnArmForUpdate() {
  //     if(currentAction == Action.INTAKE) {
  //         handClamperSubsystem.close();
  //         handIntakeSubsystem.stop();
  //     }
  //     armSubsystem.moveArm(ArmConstants.armHomePosition, subsystemCallback);
  //     return true;
  // }

  /**
   * @return
   */
  public boolean startReset() {
    isResetting = true;
    // stop current movements
    elevatorSubsystem.stopElevator();
    // move back home
    elevatorSubsystem.moveElevator(ElevatorConstants.elevatorHomePosition, subsystemCallback);
    return true;
  }

  // public boolean startIntakeReset() {
  //     handClamperSubsystem.close();
  //     handIntakeSubsystem.stop();
  //     startReset();
  //     return true;
  // }

  /**
   * @return
   */
  public boolean resetState() {
    currentSequence = null;
    currentAction = null;
    currentGamePiece = null;
    updatedLevel = null;
    positions = null;
    isResetting = false;
    elevatorResetDone = false;
    armResetDone = false;
    processComplete();
    return true;
  }
}
