package frc.robot.state.sequencer.transitions;

import frc.robot.state.sequencer.SequenceInput;
import frc.robot.state.sequencer.SequenceState;

public class TestTransitions {
  private static final Object transitionTable[][] = {

    // CURRENT                              INPUT                                     OPERATION
    //               NEXT
    {SequenceState.HOME, SequenceInput.BEGIN, "raiseElevator", SequenceState.RAISING_ELEVATOR},
    {
      SequenceState.RAISING_ELEVATOR, SequenceInput.ELEVATOR_DONE, null, SequenceState.ELEVATOR_DONE
    },
    {SequenceState.ELEVATOR_DONE, SequenceInput.SCORE, "outTaking", SequenceState.SCORING},
    {
      SequenceState.SCORING, SequenceInput.DONE_SCORING, "moveElevatorHome", SequenceState.FINISHING
    },
    {SequenceState.FINISHING, SequenceInput.RESET_DONE, "resetState", SequenceState.HOME},

    // Abort sequences
    {
      SequenceState.RAISING_ELEVATOR,
      SequenceInput.BUTTON_RELEASED,
      "startReset",
      SequenceState.FINISHING
    },
    {SequenceState.SCORING, SequenceInput.BUTTON_RELEASED, "startReset", SequenceState.FINISHING}
  };

  public static Object[][] getTransitionTable() {
    return transitionTable;
  }
}
