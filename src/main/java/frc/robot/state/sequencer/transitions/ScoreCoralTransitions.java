package frc.robot.state.sequencer.transitions;

import frc.robot.state.sequencer.SequenceInput;
import frc.robot.state.sequencer.SequenceState;

public class ScoreCoralTransitions {
  private static final Object transitionTable[][] = {
    // CURRENT                              INPUT                                     OPERATION
    //               NEXT
    {SequenceState.HOME, SequenceInput.BEGIN, null, SequenceState.FINISHING},
    {SequenceState.FINISHING, SequenceInput.RESET_DONE, "resetState", SequenceState.HOME},

    // Abort sequences
    {
      SequenceState.RAISING_ELEVATOR,
      SequenceInput.BUTTON_RELEASED,
      "startReset",
      SequenceState.FINISHING
    }
  };

  public static Object[][] getTransitionTable() {
    return transitionTable;
  }
}
