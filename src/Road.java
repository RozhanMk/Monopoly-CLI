public class Road extends Field{
    public Road(int id, boolean colored) {
        super(id, colored);
    }

    @Override
    public void onFieldActions(Player player) {
        super.onFieldActions(player);
        player.decreaseCash(100);
    }
}
