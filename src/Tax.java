public class Tax extends Field{
    public Tax(int id, boolean colored) {
        super(id, colored);
    }

    @Override
    public void onFieldActions(Game game, Player player) {
        super.onFieldActions(game,player);
        if(!player.isNoTax()){
            player.decreaseCash((player.getCash()*10)/100);
        }
    }
}
