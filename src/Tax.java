import exceptions.NegativeCashException;

public class Tax extends Field{
    public Tax(int id, boolean colored) {
        super(id, colored);
    }

    @Override
    public void onFieldActions(Game game, Player player) throws NegativeCashException {
        if(!player.isNoTax()){
            player.decreaseCash((player.getCash()*10)/100);
        }else{
            player.setNoTax(false);
        }
    }
}
