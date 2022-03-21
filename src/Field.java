public abstract class Field {
    private int id;
    private double fine;
    private boolean colored;
    private ColorType color;

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


}
