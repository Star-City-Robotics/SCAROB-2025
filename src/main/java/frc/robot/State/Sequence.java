package frc.robot.State;

public class Sequence {

  public static int number = 0;

  public static void incrementNumber() {
    number = number + 1;
  }

  public static void setNumber(int setNumber) {
    number = setNumber;
  }

  public static int getNumber() {
    return number;
  }

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
