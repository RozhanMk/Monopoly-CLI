import exceptions.NegativeCashException;

public abstract class Field {
    private final int id;
    private double fine;
    private final boolean colored;
    private final ColorType color;

    public Field(int id, boolean colored , ColorType color) {
        this.id = id;
        this.colored = colored;
        this.color = color;
    }

    public Field(int id, boolean colored) {
        this.id = id;
        this.colored = colored;
        color = ColorType.white;
    }

    public boolean isColored() {
        return this.colored;
    }

    public ColorType getColor() {
        return this.color;
    }

    public int getId() {
        return id;
    }

    public double getFine() {
        return fine;
    }
    public void setFine( double fine ){
        this.fine = fine;
    }
    public double getCost(){
        return 0;
    }
    public void onFieldActions(Game game , Player player) throws NegativeCashException{
    }
}
