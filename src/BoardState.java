public class BoardState {
    private static BoardState boardState = null;
    private Field[] fields = new Field[24];
    private BoardState(){

    }
    public static BoardState getInstance() {
        if(boardState == null){
            boardState = new BoardState();
        }
        return boardState;
    }
}
