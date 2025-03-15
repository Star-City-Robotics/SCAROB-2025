package frc.robot.state.sequencer.transitions;

import frc.robot.state.sequencer.SequenceInput;
import frc.robot.state.sequencer.SequenceState;

public class ScoreAlgaeBargeTransitions {
    private static final Object transitionTable[][] = {
        // CURRENT                              INPUT                                     OPERATION                     NEXT
        {SequenceState.HOME,                    SequenceInput.BEGIN,                      "moveSlapdownOut",            SequenceState.MOVING_SLAPDOWN_OUT},
        {SequenceState.MOVING_SLAPDOWN_OUT,     SequenceInput.SLAPDOWN_OUT,               "raiseElevator",              SequenceState.RAISING_ELEVATOR},
        {SequenceState.RAISING_ELEVATOR,        SequenceInput.ELEVATOR_DONE,              "moveSlapdownDown",           SequenceState.MOVING_SLAPDOWN_DOWN},
        {SequenceState.MOVING_SLAPDOWN_DOWN,    SequenceInput.SLAPDOWN_DOWN,              "intakeRollers",              SequenceState.INTAKING_ROLLERS},
        {SequenceState.INTAKING_ROLLERS,        SequenceInput.ALGAE_PRESENT,              "stopRollers",                SequenceState.ROLLERS_STOPPED},
        {SequenceState.ROLLERS_STOPPED,         SequenceInput.MOVE_SLAPDOWN_OUT,          "moveSlapdownOut",            SequenceState.MOVING_SLAPDOWN_OUT},
        {SequenceState.MOVING_SLAPDOWN_OUT,     SequenceInput.SLAPDOWN_OUT,               "moveElevatorHome",           SequenceState.MOVING_ELEVATOR_HOME},
        {SequenceState.MOVING_ELEVATOR_HOME,    SequenceInput.ELEVATOR_HOME,              null,                         SequenceState.WAITING},
        {SequenceState.WAITING,                 SequenceInput.BUTTON_RELEASED,            "raiseElevator",              SequenceState.RAISING_ELEVATOR},
        {SequenceState.RAISING_ELEVATOR,        SequenceInput.ELEVATOR_DONE,              "outtakeRollers",             SequenceState.OUTTAKING_ALGAE},
        {SequenceState.OUTTAKING_ALGAE,         SequenceInput.ALGAE_ABSENT,               "stopRollers",                SequenceState.ROLLERS_STOPPED},
        {SequenceState.ROLLERS_STOPPED,         SequenceInput.ELEVATOR_HOME,              "moveElevatorHome",           SequenceState.MOVING_ELEVATOR_HOME},
        {SequenceState.MOVING_ELEVATOR_HOME,    SequenceInput.ELEVATOR_HOME,              "moveSlapdownUp",             SequenceState.MOVING_SLAPDOWN_UP},
        {SequenceState.MOVING_SLAPDOWN_UP,      SequenceInput.SLAPDOWN_UP,                "resetState",                 SequenceState.HOME},

        // Abort sequences
        {SequenceState.INTAKING_ROLLERS,        SequenceInput.BUTTON_RELEASED,            "startReset",                 SequenceState.FINISHING}
    };

    public static Object getTransitionTable() {
        return transitionTable;
    }
}
