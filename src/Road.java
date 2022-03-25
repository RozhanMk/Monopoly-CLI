import exceptions.NegativeCashException;

public class Road extends Field{
    public Road(int id, boolean colored) {
        super(id, colored);
    }

    @Override
    public void onFieldActions(Game game,Player player) throws NegativeCashException {
        player.decreaseCash(100);
    }
}
