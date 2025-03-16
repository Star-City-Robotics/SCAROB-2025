package frc.robot.state.sequencer;

import frc.robot.state.Input;
import frc.robot.state.StateMachine;
import frc.robot.state.StateMachineCallback;
import frc.robot.state.sequencer.positions.Positions;
import frc.robot.subsystems.Elevator.*;
import frc.robot.subsystems.intake.*;
// TODO: import the rest of the subsystems

public class SequenceStateMachine extends StateMachine {
  // subsystems
  private ElevatorSubsystem elevatorSubsystem;
  private SlapdownSubsystem slapdownSubsystem;
  private CoralManipulatorSubsystem coralManipulatorSubsystem;

  // sequence tracking
  private Sequence currentSequence;
  private Action currentAction;
  private GamePiece currentGamePiece;
  private Positions positions;

  // reset/abort tracking
  private boolean isResetting = false;
  private boolean elevatorResetDone = false;
  private boolean armResetDone = false;

  // TODO: add the rest of the subsystems to this
  public SequenceStateMachine(
      ElevatorSubsystem elevatorSubsystem,
      SlapdownSubsystem SlapdownSubsystem,
      CoralManipulatorSubsystem coralManipulatorSubsystem) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.slapdownSubsystem = SlapdownSubsystem;
    this.coralManipulatorSubsystem = coralManipulatorSubsystem;
    setCurrentState(SequenceState.HOME);
  }

  /*
   * COMMAND INTERFACE
   */

  public boolean isReady() {
    return currentState == SequenceState.HOME;
  }

  public Sequence getCurrentSequence() {
    return currentSequence;
  }

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

  // Called by the SequenceManager if the operator changes the level mid-stream
  // Note: Can only be used when the new sequence utilizes the same transition table
  public void overwriteSequenceForLevelChange(Sequence newSequence) {
    currentSequence = newSequence;
    positions = SequenceFactory.getPositions(newSequence); // overwrite w/positions for new level
  }

  /*
   * SUBSYSTEM INTERFACE
   */

  protected void handleSubsystemCallback(Input input) {
    if (isResetting) {
      // System.out.println("SequenceStateMachine: reset input " + input);
      // SequenceInput sequenceInput = (SequenceInput) input;
      // if (sequenceInput == SequenceInput.ELEVATOR_THRESHOLD_MET) setInput(input); // not home yet
      // if (sequenceInput == SequenceInput.ELEVATOR_DONE) elevatorResetDone = true;
      // if (sequenceInput == SequenceInput.ARM_DONE) armResetDone = true;
      // if (elevatorResetDone && armResetDone) {
      //   isResetting = false;
      //   if (SequenceManager.isCoralScoreSequence(currentSequence)) resetHandIfCoralNotDetected();
      //   setInput(SequenceInput.RESET_DONE);
      // }
    } else {
      // if (input == SequenceInput.RELEASED_PIECE) closeHandWithoutCallback();
      // if (input == SequenceInput.DETECTED_PIECE && currentGamePiece == GamePiece.CORAL)
      //   holdCoralPiece();
      // if (currentSequence == Sequence.SCORE_CORAL_L2 && input == SequenceInput.SENSOR_SCORE)
      // return;
      // if (currentSequence == Sequence.SCORE_CORAL_L2
      //     && input == SequenceInput.ARM_DONE
      //     && currentState == SequenceState.SCORING) {
      //   releaseCoralPiece();
      //   return;
      // }
      setInput(input);
    }
  }

  public StateMachineCallback getSubsystemCallback() {
    return subsystemCallback;
  }

  /*
   * SLAPDOWN OPERATIONAL METHODS
   */

  public boolean moveSlapdownDown() {
    slapdownSubsystem.angleIntake(positions.moveSlapdownDown);
    return true;
  }

  public boolean intakeRollers() {
    slapdownSubsystem.intakeRollers();
    return true;
  }

  public boolean stopRollers() {
    slapdownSubsystem.stopRollers();
    return true;
  }

  public boolean moveSlapdownUp() {
    slapdownSubsystem.angleIntake(positions.moveSlapdownUp);
    return true;
  }

  public boolean moveSlapdownOut() {
    slapdownSubsystem.angleIntake(positions.moveSlapdownOut);
    return true;
  }

  public boolean outtakeAlgae() {
    slapdownSubsystem.outtakeRollers();
    return true;
  }

  /*
   * ELEVATOR OPERATIONAL METHOS
   */

  public boolean raiseElevator() {
    elevatorSubsystem.moveElevator(positions.moveElevator);
    return true;
  }

  public boolean moveElevatorHome() {
    elevatorSubsystem.moveElevator(positions.moveElevatorHome);
    return true;
  }

  public boolean moveElevatorForBarge() {
    elevatorSubsystem.moveElevator(positions.moveElevator);
    return true;
  }

  /*
   * CORAL MANIPULATOR OPERATIONAL METHODS
   */

  public boolean intakeCoral() {
    coralManipulatorSubsystem.intake();
    return true;
  }

  public boolean detectRotations() {
    return coralManipulatorSubsystem.reachedPosition();
  }

  public boolean stopIntake() {
    coralManipulatorSubsystem.stopMotors();
    return true;
  }

  public boolean outtakeCoral() {
    coralManipulatorSubsystem.intake();
    return true;
  }

  /*
   * UPDATE LEVEL OPERATIONAL METHODS
   */

  // Drive the elevator to a new position when the operator overrides it midstream
  public boolean updateElevator() {
    // raise with no threshold b/c may have to move up or down, threshold potentially not valid
    // elevatorSubsystem.moveElevatorNormalSpeed(positions.raiseElevatorPosition,
    // subsystemCallback);
    return true;
  }

  // Used to return the arm home (and stop intake) before driving the elevator to a new position
  public boolean returnArmForUpdate() {
    // armSubsystem.moveArmNormalSpeed(ArmConstants.armHomePosition, subsystemCallback);
    return true;
  }

  /*
   * RESET OPERATIONAL METHODS
   */

  public boolean startReset() {
    isResetting = true;
    // stop current movements
    // armSubsystem.stopArm();
    elevatorSubsystem.stopElevator();
    // move back home
    // armSubsystem.moveArmNormalSpeed(ArmConstants.armHomePosition, subsystemCallback);
    // elevatorSubsystem.moveElevatorNormalSpeed(ElevatorConstants.elevatorHomePosition,
    // subsystemCallback);
    return true;
  }

  public boolean startIntakeReset() {
    // if(!handIntakeSubsystem.pieceDetectionSwitchFlipped()) {
    // handClamperSubsystem.close();
    // }
    // handIntakeSubsystem.stop();
    startReset();
    return true;
  }

  public boolean resetState() {
    currentSequence = null;
    currentAction = null;
    currentGamePiece = null;
    positions = null;
    isResetting = false;
    elevatorResetDone = false;
    armResetDone = false;
    processComplete();
    return true;
  }
}
