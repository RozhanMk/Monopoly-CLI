import java.util.ArrayList;
import java.util.List;

public class Prison extends Field{
    public Prison(int id, boolean colored) {
        super(id, colored);
    }
    public static List<Player> prisoners = new ArrayList<>();
    @Override
    public void onFieldActions(Game game,Player player) {
        super.onFieldActions(game,player);
    }
    public static void removePrisoner(Player p){
        prisoners.remove(p);
    }
    public static void prisonCheck(){
        for (int i = 0 ; i < prisoners.size() ; i++){
            prisoners.get(i).setJailCount(prisoners.get(i).getJailCount() + 1);
            prisoners.get(i).decreaseCash(10);
        }
    }
}
