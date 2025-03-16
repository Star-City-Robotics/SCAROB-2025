package frc.robot.state.sequencer.transitions;

import frc.robot.state.sequencer.SequenceInput;
import frc.robot.state.sequencer.SequenceState;

public class ScoreAlgaeBargeTransitions {
  private static final Object transitionTable[][] = {
    // CURRENT                              INPUT                                     OPERATION
    //                NEXT
    {
      SequenceState.HOME,
      SequenceInput.BEGIN,
      "moveElevatorForBarge",
      SequenceState.RAISING_ELEVATOR_BARGE
    },
    {
      SequenceState.RAISING_ELEVATOR_BARGE,
      SequenceInput.ELEVATOR_BARGE,
      null,
      SequenceState.WAITING
    },
    {
      SequenceState.WAITING,
      SequenceInput.BUTTON_RELEASED,
      "outtakeAlgae",
      SequenceState.OUTTAKING_ALGAE
    },
    {
      SequenceState.OUTTAKING_ALGAE,
      SequenceInput.ALGAE_ABSENT,
      "stopRollers",
      SequenceState.ROLLERS_STOPPED
    },
    {
      SequenceState.ROLLERS_STOPPED,
      SequenceInput.ELEVATOR_HOME,
      "moveElevatorHome",
      SequenceState.MOVING_ELEVATOR_HOME
    },
    {
      SequenceState.MOVING_ELEVATOR_HOME,
      SequenceInput.ELEVATOR_HOME,
      "moveSlapdownUp",
      SequenceState.MOVING_SLAPDOWN_UP
    },
    {SequenceState.MOVING_SLAPDOWN_UP, SequenceInput.SLAPDOWN_UP, "resetState", SequenceState.HOME}

    // Abort sequences

  };

  public static Object[][] getTransitionTable() {
    return transitionTable;
  }
}
