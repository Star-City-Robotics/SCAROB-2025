package frc.robot.State;

public class SequenceFunctions {

  private static Sequence.Action actionSelection;
  private static Sequence.GamePiece gamePieceSelection;
  private static Sequence.Level levelSelction;

  public static void setAction(Sequence.Action action) {
    actionSelection = action;
  }

  public static void setGamePiece(Sequence.GamePiece gamePiece) {
    gamePieceSelection = gamePiece;
  }

  public static void setLevel(Sequence.Level level) {
    levelSelction = level;
  }

  public static boolean isAction(Sequence.Action action) {
    if (action == actionSelection) {
      return true;
    }
    else {
      return false;
    }
  }

  public static Sequence.GamePiece getGamePiece() {
    return gamePieceSelection;
  }

  public static Sequence.Level getLevel() {
    return levelSelction;
  }
}
