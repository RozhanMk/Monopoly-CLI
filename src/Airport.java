public class Airport extends Field{
    public Airport(int id, boolean colored) {
        super(id, colored);
        setFine(50);
    }
    @Override
    public void onFieldActions(Game game , Player player){
        player.decreaseCash(getFine());
    }
}
