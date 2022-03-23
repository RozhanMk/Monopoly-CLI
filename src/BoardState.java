public class BoardState {
    private static BoardState boardState = null;
    private Field[] fields = new Field[24];
    private BoardState(){
        fields[0] = new Parking(0 , false);
        fields[1] = new Empty(1 , true , ColorType.green);
        fields[2] = new Airport(2 , false);
        fields[3] = new Cinema(3 , true , ColorType.pink);
        fields[4] = new Road(4 , false);
        fields[5] = new Reward(5 , false);
        fields[6] = new Empty(6 , true , ColorType.yellow);
        fields[7] = new Cinema(7 , true , ColorType.blue);
        fields[8] = new Empty(8 , true , ColorType.pink);
        fields[9] = new Road(9 , false);
        fields[10] = new Airport(10 , false);
        fields[11] = new Empty(11 , true , ColorType.green);
        fields[12] = new Prison(12 , false);
        fields[13] = new Empty(13 , true , ColorType.blue);
        fields[14] = new Cinema(14 , true , ColorType.green);
        fields[15] = new Road(15 , false);
        fields[16] = new Tax(16 , false);
        fields[17] = new Empty(17 , true , ColorType.pink);
        fields[18] = new Empty(18 , true , ColorType.yellow);
        fields[19] = new Airport(19 , false);
        fields[20] = new Bank(20 , false);
        fields[21] = new Cinema(21 , true , ColorType.yellow);
        fields[22] = new Empty(22 , true , ColorType.blue);
        fields[23] = new RandomField(23 , false);

    }
    public static BoardState getInstance() {
        if(boardState == null){
            boardState = new BoardState();
        }
        return boardState;
    }
    public static Field getFieldStatic(int id){
        return BoardState.getInstance().getField(id);
    }

    public Field getField(int id){
        if(id >=0 && id<=23){
            return fields[id];
        }else{
            return null;
        }
    }
}
