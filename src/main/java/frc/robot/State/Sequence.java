package frc.robot.State;

public class Sequence {

  public static int number = 0;

  public enum Action {
    SCORE,
    INTAKE
  }

  public enum GamePiece {
    CORAL,
    ALGAE
  }

  public enum Level {
    L1,
    L2,
    L3,
    L4
  }
}
