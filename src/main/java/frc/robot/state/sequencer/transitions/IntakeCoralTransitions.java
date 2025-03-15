package frc.robot.state.sequencer.transitions;

import frc.robot.state.sequencer.SequenceInput;
import frc.robot.state.sequencer.SequenceState;

public class IntakeCoralTransitions {
  private static final Object transitionTable[][] = {

    // CURRENT                              INPUT                                     OPERATION                         NEXT
    {SequenceState.HOME,                    SequenceInput.BEGIN,                      "intakeCoral",                    SequenceState.INTAKING_CORAL},
    {SequenceState.INTAKING_CORAL,          SequenceInput.CORAL_PRESENT,              "detectRotations",                SequenceState.RUNNING_ROTATIONS},
    {SequenceState.RUNNING_ROTATIONS,       SequenceInput.ROTATIONS_FINISHED,         "stopIntakeMotors",               SequenceState.STOPPING_INTAKE},
    {SequenceState.STOPPING_INTAKE,         SequenceInput.STOPPED_INTAKE,             "resetState",                     SequenceState.HOME},

    // Abort sequences
    {SequenceState.INTAKING_CORAL,          SequenceInput.BUTTON_RELEASED,            "startReset",                     SequenceState.FINISHING}
  };

  public static Object[][] getTransitionTable() {
    return transitionTable;
  }
}