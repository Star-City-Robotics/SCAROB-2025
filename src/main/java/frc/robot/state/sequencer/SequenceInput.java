package frc.robot.state.sequencer;

import frc.robot.state.Input;

public enum SequenceInput implements Input {
  BEGIN,
  SCORE,
  RESET_DONE,
  BUTTON_RELEASED,
  SENSOR_SCORE,
  LEVEL_CHANGED,
  SLAPDOWN_OUT,

  // Subsystem feedback
  ELEVATOR_THRESHOLD_MET,
  ELEVATOR_DONE,
  ARM_DONE,
  HAND_DONE,
  DETECTED_PIECE,
  RELEASED_PIECE,
  DONE_SCORING,
  STOPPED_INTAKE,
  SLAPDOWN_DOWN,
  SLAPDOWN_UP,
  ALGAE_PRESENT,
  SLAPDOWN_MIDDLE,
  ALGAE_ABSENT,
  MOVE_SLAPDOWN_UP,
  MOVE_SLAPDOWN_OUT,
  ELEVATOR_HOME,
  ELEVATOR_BARGE,
  CORAL_ABSENT,
  CORAL_PRESENT,
  ROTATIONS_FINISHED,
  ROLLERS_STOPPED
}
