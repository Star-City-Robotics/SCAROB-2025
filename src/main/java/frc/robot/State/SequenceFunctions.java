package frc.robot.State;

public class SequenceFunctions {

  private static Sequence.State stateSelection;
  private static Sequence.Input inputSelection;
  private static Sequence.Action actionSelection;
  private static Sequence.GamePiece gamePieceSelection;
  private static Sequence.Level levelSelction;

  public static boolean checkState(Sequence.State state) {
    return state == stateSelection;
  }

  public static boolean checkInput(Sequence.Input input) {
    return input == inputSelection;
  }

  public static boolean checkAction(Sequence.Action action) {
    return action == actionSelection;
  }

  public static boolean checkGamePiece(Sequence.GamePiece gamePiece) {
    return gamePiece == gamePieceSelection;
  }

  public static boolean checkLevel(Sequence.Level level) {
    return level == levelSelction;
  }

  public static void setState(Sequence.State state) {
    stateSelection = state;
  }

  public static void setInput(Sequence.Input input) {
    inputSelection = input;
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

  public static Sequence.State getState() {
    return stateSelection;
  }
}
