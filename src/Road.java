public class Road extends Field{
    public Road(int id, boolean colored) {
        super(id, colored);
    }

    @Override
    public void onFieldActions(Game game,Player player) {
        super.onFieldActions(game,player);
        player.decreaseCash(100);
    }
}
