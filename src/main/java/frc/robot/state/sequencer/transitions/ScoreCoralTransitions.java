package frc.robot.state.sequencer.transitions;

import frc.robot.state.sequencer.SequenceInput;
import frc.robot.state.sequencer.SequenceState;

public class ScoreCoralTransitions {
  private static final Object transitionTable[][] = {

    // CURRENT                              INPUT                                     OPERATION                         NEXT
    {SequenceState.HOME,                    SequenceInput.BEGIN,                      "moveSlapdownOut",                SequenceState.MOVING_SLAPDOWN_OUT},
    {SequenceState.MOVING_SLAPDOWN_OUT,     SequenceInput.SLAPDOWN_OUT,               "raiseElevator",                  SequenceState.RAISING_ELEVATOR},
    {SequenceState.RAISING_ELEVATOR,        SequenceInput.ELEVATOR_DONE,              null,                             SequenceState.WAITING},
    {SequenceState.WAITING,                 SequenceInput.BUTTON_RELEASED,            "outtakeCoral",                   SequenceState.OUTTAKING_CORAL},
    {SequenceState.OUTTAKING_CORAL,         SequenceInput.CORAL_ABSENT,               "stopIntake",                     SequenceState.STOPPING_ROLLERS},
    {SequenceState.STOPPING_ROLLERS,        SequenceInput.ROLLERS_STOPPED,            "moveElevatorHome",               SequenceState.MOVING_ELEVATOR_HOME},
    {SequenceState.MOVING_ELEVATOR_HOME,    SequenceInput.ELEVATOR_HOME,              "moveSlapdownUp",                 SequenceState.MOVING_SLAPDOWN_UP},
    {SequenceState.MOVING_SLAPDOWN_UP,      SequenceInput.SLAPDOWN_UP,                "resetState",                     SequenceState.HOME},

    // Abort sequences
    {SequenceState.RAISING_ELEVATOR,        SequenceInput.BUTTON_RELEASED,            "startReset",                     SequenceState.FINISHING}
  };

  public static Object[][] getTransitionTable() {
    return transitionTable;
  }
}
