package frc.State;

public class SequenceFunctions {

    private static Sequence.State stateSelection;
    private static Sequence.Input inputSelection;
    private static Sequence.Action actionSelection;
    private static Sequence.GamePiece gamePieceSelection;
    private static Sequence.Level levelSelction;
    
    public void setState(Sequence.State state) {
        stateSelection = state;
    }

    public void setInput(Sequence.Input input) {
        inputSelection = input;
    }

    public void setAction(Sequence.Action action) {
        actionSelection = action;
    }

    public void setGamePiece(Sequence.GamePiece gamePiece) {
        gamePieceSelection = gamePiece;
    }

    public void setLevel(Sequence.Level level) {
        levelSelction = level;
    }

    public Sequence.State getState() {
        return stateSelection;
    }

    public Sequence.Input geInput() {
        return inputSelection;
    }

    public Sequence.Action getAction() {
        return actionSelection;
    }

    public Sequence.GamePiece getGamePiece() {
        return gamePieceSelection;
    }

    public Sequence.Level getLevel() {
        return levelSelction;
    }

}
