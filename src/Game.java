import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    public List<Player> players = new ArrayList<>();
    private Integer time;

    public void setUpGame( Scanner scanner){
        boolean gameStarted = false;
        int id = 0;
        System.out.println("Enter player names(You can enter time with time x)");
        String input = scanner.nextLine();
        while(!gameStarted){
            if(input.contains("Time")){
                String[] inputArray = input.split(" ");
                try {
                    time = Integer.parseInt(inputArray[1]);
                } catch (Exception e) {
                    System.out.println("Your input for time was Wrong");
                }
            }else if(input.equals("start_game")){
                if(players.size() >=2 && players.size() <=4){
                    gameStarted = true;
                    System.out.println("Game Started");
                    Empty.setNumberOfBuildings(players.size() * 5);
                }else{
                    System.out.println("no game created");
                    System.out.println("The total count of your players is " + players.size());
                }
            //delete the player
            }else if(input.contains("delete")){
                try{
                    String player_name = input.split(" ")[1];
                    players.removeIf(player -> player.getName().equals(player_name));
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("Your input is wrong");
                }
            } else{
                //adding players in here
                players.add(new Player(input , id));
                id++;
            }
            if(!gameStarted){
                input = scanner.nextLine();
            }
        }
        GameHandler gameHandler = new GameHandler(this);
        gameHandler.startRound(scanner);
    }
    public int getRank(Player player){
        List<Player> temp = new ArrayList<>(this.getPlayers());
        for(int i = 0 ; i < temp.size() - 1 ; i++){
            for(int j = i ; j < temp.size() ; j++){
                if(getfortune(temp.get(j))  > getfortune(temp.get(i))){
                    Collections.swap(temp , j , i);
                }
            }
        }
        for (int i = 0 ; i < temp.size() ; i++){
            if(temp.get(i).equals(player)){
                return i + 1;
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
    public void turnByDice(){
        for (int i = 0 ; i < players.size() - 1 ; i++){
            for (int j = i ; j < players.size() ; j++){
                if(players.get(j).getDice() > players.get(i).getDice()){
                    Collections.swap(players , i , j);
                }
            }
        }
    }
    public  List<Player> getPlayers() {
        return players;
    }
}
