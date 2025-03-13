package frc.robot.state.sequencer.positions;

import frc.robot.state.sequencer.positions.PositionConstants.ALGAE_INTAKE.ALGAE_REEF_L2;
import frc.robot.state.sequencer.positions.PositionConstants.ALGAE_INTAKE.ALGAE_REEF_L3;
import frc.robot.state.sequencer.positions.PositionConstants.ALGAE_SCORE.ALGAE_SCORE_BARGE;
import frc.robot.state.sequencer.positions.PositionConstants.CORAL_SCORE.CORAL_L1;
import frc.robot.state.sequencer.positions.PositionConstants.CORAL_SCORE.CORAL_L2;
import frc.robot.state.sequencer.positions.PositionConstants.CORAL_SCORE.CORAL_L3;
import frc.robot.state.sequencer.positions.PositionConstants.CORAL_SCORE.CORAL_L4;

// (Evens) takes the values from PositionsConstants.java and when called, sets Positions to equal
// those values, later used in the state machine
public class PositionsFactory {
  /*
   * !!!!!!!!!!!!!!!!!!!!!!
   * CORAL POSITION HELPERS
   * !!!!!!!!!!!!!!!!!!!!!!
   */

  public static Positions getCoralFeederPickupPositions() {
    Positions positions = new Positions();
    // TODO:
    return positions;
  }

  public static Positions getCoralFloorPickupPositions() {
    Positions positions = new Positions();
    // TODO:
    return positions;
  }

  public static Positions getCoralUprightFloorPickupPositions() {
    Positions positions = new Positions();
    // TODO: list the positions needed and apply the value stored in PositionsConstants
    return positions;
  }

  public static Positions getCoralScoreL1Positions() {
    Positions positions = new Positions();
    positions.moveElevatorPosition = CORAL_L1.moveElevatorPosition;
    // TODO: list the positions needed and apply the value stored in PositionsConstants
    return positions;
  }

  public static Positions getCoralScoreL2Positions() {
    Positions positions = new Positions();
    positions.moveElevatorPosition = CORAL_L2.moveElevatorPosition;
    // TODO: list the positions needed and apply the value stored in PositionsConstants
    return positions;
  }

  public static Positions getCoralScoreL3Positions() {
    Positions positions = new Positions();
    positions.moveElevatorPosition = CORAL_L3.moveElevatorPosition;
    // TODO: list the positions needed and apply the value stored in PositionsConstants
    return positions;
  }

  public static Positions getCoralScoreL4Positions() {
    Positions positions = new Positions();
    positions.moveElevatorPosition = CORAL_L4.moveElevatorPosition;
    // TODO: list the positions needed and apply the value stored in PositionsConstants
    return positions;
  }

  /*
   * !!!!!!!!!!!!!!!!!!!!!!
   * ALGAE POSITION HELPERS
   * !!!!!!!!!!!!!!!!!!!!!!
   */

  public static Positions getAlgaeReefL2PickupPositions() {
    Positions positions = new Positions();
    positions.moveElevatorPosition = ALGAE_REEF_L2.moveElevatorPosition;
    // TODO: list the positions needed and apply the value stored in PositionsConstants
    return positions;
  }

  public static Positions getAlgaeReefL3PickupPositions() {
    Positions positions = new Positions();
    positions.moveElevatorPosition = ALGAE_REEF_L3.moveElevatorPosition;
    // TODO: list the positions needed and apply the value stored in PositionsConstants
    return positions;
  }

  public static Positions getAlgaeFloorPickupPositions() {
    Positions positions = new Positions();
    // TODO: list the positions needed and apply the value stored in PositionsConstants
    return positions;
  }

  public static Positions getAlgaeScoreBargePositions() {
    Positions positions = new Positions();
    positions.moveElevatorPosition = ALGAE_SCORE_BARGE.moveElevatorPosition;
    // TODO: list the positions needed and apply the value stored in PositionsConstants
    return positions;
  }

  public static Positions getAlgaeHandoffPositions() {
    Positions positions = new Positions();
    // TODO: list the positions needed and apply the value stored in PositionsConstants
    return positions;
  }
}
