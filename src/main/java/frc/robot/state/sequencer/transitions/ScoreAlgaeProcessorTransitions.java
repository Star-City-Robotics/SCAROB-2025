package frc.robot.state.sequencer.transitions;

import frc.robot.state.sequencer.SequenceInput;
import frc.robot.state.sequencer.SequenceState;

public class ScoreAlgaeProcessorTransitions {
    private static final Object transitionTable[][] = {
        // CURRENT                              INPUT                                     OPERATION                     NEXT
        {SequenceState.HOME,                    SequenceInput.BEGIN,                      "moveSlapdownDown",          SequenceState.MOVING_SLAPDOWN_DOWN},
        {SequenceState.MOVING_SLAPDOWN_DOWN,    SequenceInput.SLAPDOWN_DOWN,              "outtakeAlgae",              SequenceState.OUTTAKING_ALGAE},
        {SequenceState.OUTTAKING_ALGAE,         SequenceInput.ALGAE_ABSENT,               "moveSlapdownUp",            SequenceState.MOVING_SLAPDOWN_UP},
        {SequenceState.MOVING_SLAPDOWN_UP,      SequenceInput.SLAPDOWN_UP,                "resetState",                SequenceState.HOME}
        

        // Abort sequences
        
    };

    public static Object getTransitionTable() {
        return transitionTable;
    }
}
