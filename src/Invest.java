import exceptions.NegativeCashException;

public abstract class Invest extends Field {
    private Player owner;
    private double cost;

    public Invest(int id, boolean colored , ColorType color) {
        super(id, colored , color);
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public void onFieldActions(Game game , Player player) throws NegativeCashException{
        if(!getOwner().equals(player) && getOwner()!=null){
            //determine if the owner has properties with same color
            int sameColor = 0;
            for (int i = 0; i < player.getProperties().size(); i++) {
                if(getColor().equals(player.getField(player.getProperties().get(i)).getColor())){
                    sameColor++;
                }
            }
            if(sameColor == 3){
                player.decreaseCash(getFine()*2);
                getOwner().increaseCash(getFine()*2);
            }else{
                player.decreaseCash(getFine());
                getOwner().increaseCash(getFine());
            }
            
        }
    }

}
