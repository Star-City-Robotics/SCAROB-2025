package frc.robot.State;

public class Sequence {

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

  public enum State {
    HOME,
    ElEVATOR_RAISED,
    ELEVATOR_HOME,
    SLAPDOWN_UP,
    SLAPDOWN_DOWN,
    SLAPDOWN_OUT
  }

  public enum Input {
    BEGIN,
    FINISHED,
    RAISE_ELEVATOR,
    MOVE_ELEVATOR_HOME,
    MOVE_SLAPDOWN_UP,
    MOVE_SLAPDOWN_DOWN,
    MOVE_SLAPDOWN_OUT
  }
}
