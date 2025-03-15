package frc.robot.state.sequencer.transitions;

import frc.robot.state.sequencer.SequenceInput;
import frc.robot.state.sequencer.SequenceState;

public class IntakeAlgaeGroundTransitions {
    private static final Object transitionTable[][] = {
        // CURRENT                              INPUT                                     OPERATION                     NEXT
        {SequenceState.HOME,                    SequenceInput.BEGIN,                      "moveSlapdownDown",           SequenceState.MOVING_SLAPDOWN_DOWN},
        {SequenceState.MOVING_SLAPDOWN_DOWN,    SequenceInput.SLAPDOWN_DOWN,              "intakeRollers",              SequenceState.INTAKING_ROLLERS},
        {SequenceState.INTAKING_ROLLERS,        SequenceInput.ALGAE_PRESENT,              "stopRollers",                SequenceState.STOPPING_ROLLERS},
        {SequenceState.STOPPING_ROLLERS,        SequenceInput.ROLLERS_STOPPED,            "moveSLapdownUp",             SequenceState.MOVING_SLAPDOWN_UP},
        {SequenceState.MOVING_SLAPDOWN_UP,      SequenceInput.SLAPDOWN_UP,                "resetState",                 SequenceState.HOME},
        
        // Abort sequences
        {SequenceState.INTAKING_ROLLERS,        SequenceInput.BUTTON_RELEASED,            "startReset",                 SequenceState.FINISHING}
    };

    public static Object[][] getTransitionTable() {
        return transitionTable;
    }
}
