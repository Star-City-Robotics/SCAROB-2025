package frc.robot.state.sequencer;

import frc.robot.state.sequencer.positions.Positions;
import frc.robot.state.sequencer.positions.PositionsFactory;
import frc.robot.state.sequencer.transitions.IntakeCoralTransitions;
import frc.robot.state.sequencer.transitions.ResetTransitions;
import frc.robot.state.sequencer.transitions.ScoreCoralTransitions; // you will need to import the

// transition tables you make

// (Evens) This takes the selected game peice and level and determines which transition table and
// positions to use
public class SequenceFactory {
  public static Sequence getSequence(
      Level levelSelection, GamePiece pieceSelection, Action actionSelection) {
    /*
     * CORAL SEQUENCES
     */
    // Coral intake
    if (pieceSelection == GamePiece.CORAL
        && levelSelection == Level.L1
        && actionSelection == Action.INTAKE) return Sequence.INTAKE_CORAL;
    if (pieceSelection == GamePiece.CORAL
        && levelSelection == Level.L2
        && actionSelection == Action.INTAKE) return Sequence.INTAKE_CORAL;
    if (pieceSelection == GamePiece.CORAL
        && levelSelection == Level.L3
        && actionSelection == Action.INTAKE) return Sequence.INTAKE_CORAL;
    if (pieceSelection == GamePiece.CORAL
        && levelSelection == Level.L4
        && actionSelection == Action.INTAKE) return Sequence.INTAKE_CORAL;

    // Coral score
    if (pieceSelection == GamePiece.CORAL
        && levelSelection == Level.L1
        && actionSelection == Action.SCORE) return Sequence.SCORE_CORAL_L1;
    if (pieceSelection == GamePiece.CORAL
        && levelSelection == Level.L2
        && actionSelection == Action.SCORE) return Sequence.SCORE_CORAL_L2;
    if (pieceSelection == GamePiece.CORAL
        && levelSelection == Level.L3
        && actionSelection == Action.SCORE) return Sequence.SCORE_CORAL_L3;
    if (pieceSelection == GamePiece.CORAL
        && levelSelection == Level.L4
        && actionSelection == Action.SCORE) return Sequence.SCORE_CORAL_L4;

    /*
     * ALGAE SEQUENCES
     */
    // Algae intake
    if (pieceSelection == GamePiece.ALGAE
        && levelSelection == Level.L1
        && actionSelection == Action.INTAKE) return Sequence.INTAKE_ALGAE_FLOOR;
    if (pieceSelection == GamePiece.ALGAE
        && levelSelection == Level.L2
        && actionSelection == Action.INTAKE) return Sequence.INTAKE_ALGAE_L2;
    if (pieceSelection == GamePiece.ALGAE
        && levelSelection == Level.L3
        && actionSelection == Action.INTAKE) return Sequence.INTAKE_ALGAE_L3;
    if (pieceSelection == GamePiece.ALGAE
        && levelSelection == Level.L4
        && actionSelection == Action.INTAKE) return Sequence.INTAKE_ALGAE_L3;
    // Algae score
    if (pieceSelection == GamePiece.ALGAE
        && levelSelection == Level.L1
        && actionSelection == Action.SCORE) return Sequence.HANDOFF_ALGAE;
    if (pieceSelection == GamePiece.ALGAE
        && levelSelection == Level.L2
        && actionSelection == Action.SCORE) return Sequence.SHOOT_ALGAE;
    if (pieceSelection == GamePiece.ALGAE
        && levelSelection == Level.L3
        && actionSelection == Action.SCORE) return Sequence.SHOOT_ALGAE;
    if (pieceSelection == GamePiece.ALGAE
        && levelSelection == Level.L4
        && actionSelection == Action.SCORE) return Sequence.SHOOT_ALGAE;

    return null;
  }
  // (Evens) this is what chooses which transition table to use based on what is returned above
  // ^^^^^^^
  public static Object[][] getTransitionTable(Sequence sequence) {
    switch (sequence) {
      case RESET:
        return ResetTransitions.getTransitionTable();
        /*
         * CORAL TRANSITIONS
         */
      case INTAKE_CORAL:
        return IntakeCoralTransitions.getTransitionTable();
      case SCORE_CORAL_L1:
        // you might not this this depending on how different your L1 score is
        return null; // TODO: need to return transitions for L1 score
      case SCORE_CORAL_L2:
      case SCORE_CORAL_L3:
      case SCORE_CORAL_L4:
        // return TestTransitions.getTransitionTable();
        return ScoreCoralTransitions.getTransitionTable();

        /*
         * ALGAE TRANSITIONS
         */
      case INTAKE_ALGAE_L2:
      case INTAKE_ALGAE_L3:
        return null; // TODO: need to return transitions for taking algae off the reef
      case INTAKE_ALGAE_FLOOR:
        return null; // TODO: need to return transitions for picking up algae off the floor
      case SHOOT_ALGAE:
        return null; // TODO: need to return transitions for scoring algae in barge
      case HANDOFF_ALGAE:
        return null; // TODO: need to return transitions for scoring algae in processor

      default:
        return null;
    }
  }
  // (Evens) this is what sets the positions for the state machine, using the values from the
  // PositionConstants
  public static Positions getPositions(Sequence sequence) {
    switch (sequence) {
      case RESET:
        return new Positions(); // empty positions, not needed for a reset
        /*
         * CORAL POSITIONS
         */
      case INTAKE_CORAL:
        return PositionsFactory.getCoralFeederPickupPositions();
      case SCORE_CORAL_L1:
        return PositionsFactory.getCoralScoreL1Positions();
      case SCORE_CORAL_L2:
        return PositionsFactory.getCoralScoreL2Positions();
      case SCORE_CORAL_L3:
        return PositionsFactory.getCoralScoreL3Positions();
      case SCORE_CORAL_L4:
        return PositionsFactory.getCoralScoreL4Positions();

        /*
         * ALGAE POSITIONS
         */
      case INTAKE_ALGAE_L2:
        return PositionsFactory.getAlgaeReefL2PickupPositions();
      case INTAKE_ALGAE_L3:
        return PositionsFactory.getAlgaeReefL3PickupPositions();
      case INTAKE_ALGAE_FLOOR:
        return PositionsFactory.getAlgaeFloorPickupPositions();
      case SHOOT_ALGAE:
        return PositionsFactory.getAlgaeScoreBargePositions();
      case HANDOFF_ALGAE:
        return PositionsFactory.getAlgaeHandoffPositions();

      default:
        return null;
    }
  }
}
