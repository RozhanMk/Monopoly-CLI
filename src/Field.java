public abstract class Field {
    private int id;
    private double fine;
    private boolean colored;

    public Field(int id, boolean colored) {
        this.id = id;
        this.colored = colored;
    }

    public int getId() {
        return id;
    }

    public double getFine() {
        return fine;
    }
}
