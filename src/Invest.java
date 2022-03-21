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
    
}
