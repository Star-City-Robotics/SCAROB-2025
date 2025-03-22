package frc.robot.State;

public class SequenceFunctions {

  private static Sequence.Action actionSelection;
  private static Sequence.GamePiece gamePieceSelection;
  private static Sequence.Level levelSelction;
  private static double levelConstantSelection;

  public static boolean checkAction(Sequence.Action action) {
    return action == actionSelection;
  }

  public static boolean checkGamePiece(Sequence.GamePiece gamePiece) {
    return gamePiece == gamePieceSelection;
  }

  public static boolean checkLevel(Sequence.Level level) {
    return level == levelSelction;
  }

  public static void setAction(Sequence.Action action) {
    actionSelection = action;
  }

  public static void setGamePiece(Sequence.GamePiece gamePiece) {
    gamePieceSelection = gamePiece;
  }

  public static void setLevel(Sequence.Level level) {
    levelSelction = level;
  }

  public static void setLevelConstant(Double level) {
    level = levelConstantSelection;
  }

  public static double getElevatorConstant() {
    return levelConstantSelection;
  }
}
