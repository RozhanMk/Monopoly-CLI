import exceptions.NegativeCashException;

public class Airport extends Field{
    public Airport(int id, boolean colored) {
        super(id, colored);
        setFine(50);
    }
    @Override
    public void onFieldActions(Game game , Player player) throws NegativeCashException{
        player.decreaseCash(getFine());
    }
}
