public class Reward extends Field{
    public Reward(int id, boolean colored) {
        super(id, colored);
    }

    @Override
    public void onFieldActions(Game game,Player player) {
        player.increaseCash(200);
    }
}
