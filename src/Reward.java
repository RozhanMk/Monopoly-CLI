public class Reward extends Field{
    public Reward(int id, boolean colored) {
        super(id, colored);
    }

    @Override
    public void onFieldActions(Player player) {
        super.onFieldActions(player);
        player.increaseCash(200);
    }
}
