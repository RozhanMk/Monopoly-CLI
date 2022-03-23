
public class RandomField extends Field{
    public RandomField(int id, boolean colored) {
        super(id, colored);
    }
    public int getRandomNumber() {
        return (int) ((Math.random() * 6) + 1); //random number from 1 to 7
    }
    @Override
    public void onFieldActions(Game game , Player player){
        int number = getRandomNumber();
        if(number == 1){
            player.increaseCash(200);
        }
        if(number == 2){
            Prison.prisoners.add(player);
        }
        if(number == 3){
            player.decreaseCash( (1/10)*player.getCash() );
        }
        if(number == 4){
            player.setPosition(player.getPosition()+3);
        }
        if(number == 5){
            player.free();
        }
        if(number == 6){
            player.setNoTax(true);
        }
        if(number == 7){
            int playerIndex = player.getId();
            for (int i = 0; i < game.getPlayers().size(); i++) {
                if( i != playerIndex ){
                    game.getPlayers().get(i).increaseCash(10);
                    player.decreaseCash(10);
                }
            }
        }

    }
}
