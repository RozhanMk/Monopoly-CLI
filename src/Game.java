import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static List<Player> players = new ArrayList<>();
    private Integer time;
//    private int
    public void setUpGame(){
        Scanner scanner = new Scanner(System.in);
        boolean gameStarted = false;
        int id = 0;
        while(!gameStarted){
            String input = scanner.next();
            if(input.contains("Time")){
                String[] inputArray = input.split(" ");
                try {
                    time = Integer.parseInt(inputArray[1]);
                } catch (Exception e) {
                    System.out.println("Your input for time was Wrong");
                }
            }else if(input.equals("start_game")){
                if(players.size() >=2 && players.size() <=4){
                    scanner.close();
                    gameStarted = true;
                    Empty.setNumberOfBuildings(players.size() * 5);
                }else{
                    System.out.println("no game created");
                }
            }else{
                players.add(new Player(input , id));
                id++;
            }
        }
        GameHandler gameHandler = new GameHandler(this);
        gameHandler.startRound();
    }
    public int getRank(Player player){
        List<Player> temp = new ArrayList<>(this.getPlayers());
        for(int i = 0 ; i < temp.size() - 1 ; i++){
            for(int j = i ; j < temp.size() ; j++){
                if(getfortune(temp.get(j))  < getfortune(temp.get(i))){
                    Collections.swap(temp , j , i);
                }
            }
        }
        for (int i = 0 ; i < temp.size() ; i++){
            if(temp.get(i).equals(player)){
                return i;
            }
        }
        return -1;
    }
    public double getfortune(Player player) {
        double fortune = 0;
        for(int i = 0 ; i < player.getProperties().size() ; i++){
            fortune += BoardState.getFieldStatic(player.getProperties().get(i) - 1).getCost();
        }
        fortune += player.getCash();
        return fortune;
    }
    public  List<Player> getPlayers() {
        return players;
    }
}
