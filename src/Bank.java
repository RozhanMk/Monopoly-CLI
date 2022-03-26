public class Bank extends Field{
    public Bank(int id, boolean colored) {
        super(id, colored);
    }
    @Override
    public void onFieldActions(Game game, Player player){
        //get twice of invested money from the bank
        if(player.isHasInvestInBank()){
            player.increaseCash(player.getSaved()*2);
            player.setSaved(0);
            player.setHasInvestInBank(false);
        }
    }
}
