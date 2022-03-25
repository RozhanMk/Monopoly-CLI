import exceptions.NegativeCashException;

public class Empty extends Invest{
    private int level;
    private boolean isHotel;
    static int numberOfBuildings;
    public static void setNumberOfBuildings(int numberOfBuildings) {
        Empty.numberOfBuildings = numberOfBuildings;
    }

    public static int getNumberOfBuildings() {
        return numberOfBuildings;
    }

    public boolean isHotel() {
        return isHotel;
    }

    public void setHotel(boolean hotel) {
        isHotel = hotel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Empty(int id, boolean colored , ColorType color) {
        super(id, colored , color);
    }

    @Override
    public void onFieldActions(Game game, Player player) throws NegativeCashException {
        super.onFieldActions(game, player);
    }
}
