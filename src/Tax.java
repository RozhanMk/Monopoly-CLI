public class Tax extends Field{
    public Tax(int id, boolean colored) {
        super(id, colored);
    }

    @Override
    public void onFieldActions(Player player) {
        super.onFieldActions(player);
        player.decreaseCash((player.getCash()*10)/100);
    }
}
