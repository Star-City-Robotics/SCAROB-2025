package frc.robot.state.sequencer;

public enum Sequence {
  RESET,

  INTAKE_ALGAE_L2,
  INTAKE_ALGAE_L3,
  INTAKE_ALGAE_FLOOR,
  SHOOT_ALGAE, // Barge score
  HANDOFF_ALGAE, // Processor score

  INTAKE_CORAL,
  SCORE_CORAL_L1,
  SCORE_CORAL_L2,
  SCORE_CORAL_L3,
  SCORE_CORAL_L4
}
