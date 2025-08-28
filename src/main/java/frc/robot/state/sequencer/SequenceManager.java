package frc.robot.state.sequencer;

import frc.robot.subsystems.Elevator.*;

/** */
public class SequenceManager {
  private static SequenceStateMachine stateMachine;
  private static Level levelSelection = Level.L2; // L2 is default
  private static Action actionSelection;
  private static GamePiece pieceSelection = GamePiece.CORAL; // coral is default

  /**
   * @return
   */
  public static Level getLevelSelection() {
    return levelSelection;
  }

  /**
   * @param level
   */
  public static void setLevelSelection(Level level) {
    levelSelection = level;

    // notify the state machine and it will handle it if applicable
    // if not applicable to the sequence/state it will be ignored
    if (stateMachine != null && stateMachine.hasLoadedTransitions()) {
      stateMachine.setInput(SequenceInput.LEVEL_CHANGED);
    }
  }

  /**
   * @return
   */
  public static GamePiece getGamePieceSelection() {
    return pieceSelection;
  }

  /**
   * @param piece
   */
  public static void setGamePieceSelection(GamePiece piece) {
    pieceSelection = piece;
  }

  /**
   * @return
   */
  public static boolean shouldDetectGamePiece() {
    return pieceSelection == GamePiece.CORAL;
  }

  /**
   * @return
   */
  public static Action getActionSelection() {
    return actionSelection;
  }

  /**
   * @param action
   */
  public static void setActionSelection(Action action) {
    actionSelection = action;
  }
  // public static SequenceStateMachine getStateMachine(ElevatorSubsystem elevatorSubsystem,
  // ArmSubsystem armSubsystem, HandClamperSubsystem clamperSubsystem, HandIntakeSubsystem
  // intakeSubsystem) {
  // if(stateMachine == null) {
  //     stateMachine = new SequenceStateMachine(elevatorSubsystem, armSubsystem, clamperSubsystem,
  // intakeSubsystem);
  // }
  // return stateMachine;
  // }

  /**
   * @param elevatorSubsystem
   * @return
   */
  public static SequenceStateMachine getStateMachine(ElevatorSubsystem elevatorSubsystem) {
    if (stateMachine == null) {
      stateMachine = new SequenceStateMachine(elevatorSubsystem);
    }
    return stateMachine;
  }

  /**
   * @return
   */
  public static Sequence getSequence() {
    // return SequenceFactory.getSequence(levelSelection, pieceSelection, actionSelection);
    return SequenceFactory.getSequence(levelSelection);
  }
}
