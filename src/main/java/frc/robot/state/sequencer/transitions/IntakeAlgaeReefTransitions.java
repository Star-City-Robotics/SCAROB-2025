package frc.robot.state.sequencer.transitions;

import frc.robot.state.sequencer.SequenceInput;
import frc.robot.state.sequencer.SequenceState;

public class IntakeAlgaeReefTransitions {
  private static final Object transitionTable[][] = {
    // CURRENT                              INPUT                                     OPERATION
    //                NEXT
    {SequenceState.HOME, SequenceInput.BEGIN, "moveSlapdownOut", SequenceState.MOVING_SLAPDOWN_OUT},
    {
      SequenceState.MOVING_SLAPDOWN_OUT,
      SequenceInput.SLAPDOWN_OUT,
      "raiseElevator",
      SequenceState.RAISING_ELEVATOR
    },
    {
      SequenceState.RAISING_ELEVATOR,
      SequenceInput.ELEVATOR_DONE,
      "moveSlapdownDown",
      SequenceState.MOVING_SLAPDOWN_DOWN
    },
    {SequenceState.MOVING_SLAPDOWN_DOWN, SequenceInput.SLAPDOWN_DOWN, null, SequenceState.WAITING},
    {
      SequenceState.WAITING,
      SequenceInput.BUTTON_RELEASED,
      "intakeRollers",
      SequenceState.INTAKING_ROLLERS
    },
    {
      SequenceState.INTAKING_ROLLERS,
      SequenceInput.ALGAE_PRESENT,
      "stopRollers",
      SequenceState.ROLLERS_STOPPED
    },
    {
      SequenceState.ROLLERS_STOPPED,
      SequenceInput.MOVE_SLAPDOWN_OUT,
      "moveSlapdownOut",
      SequenceState.MOVING_SLAPDOWN_OUT
    },
    {
      SequenceState.MOVING_SLAPDOWN_OUT_2,
      SequenceInput.SLAPDOWN_OUT,
      "moveElevatorHome",
      SequenceState.MOVING_ELEVATOR_HOME
    },
    {
      SequenceState.MOVING_ELEVATOR_HOME,
      SequenceInput.ELEVATOR_HOME,
      "resetState",
      SequenceState.HOME
    },

    // Abort sequences
    {
      SequenceState.INTAKING_ROLLERS,
      SequenceInput.BUTTON_RELEASED,
      "startReset",
      SequenceState.FINISHING
    }
  };

  public static Object[][] getTransitionTable() {
    return transitionTable;
  }
}
