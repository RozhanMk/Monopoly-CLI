import java.util.ArrayList;
import java.util.List;

import exceptions.NegativeCashException;

public class Prison extends Field{
    public Prison(int id, boolean colored) {
        super(id, colored);
    }
    public static List<Player> prisoners = new ArrayList<>();
    public static void removePrisoner(Player p){
        prisoners.remove(p);
    }
    public static void prisonCheck() throws NegativeCashException{
        for (int i = 0 ; i < prisoners.size() ; i++){
            prisoners.get(i).decreaseCash(10);
        }
    }
}
