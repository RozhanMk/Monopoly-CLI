import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Player> players = new ArrayList<>();
    private Integer time;
    private int gameRound = 0;
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
                }else{
                    System.out.println("no game created");
                }
            }else{
                players.add(new Player(input , id));
                id++;
            }
        }
        GameHandler gameHandler = new GameHandler(this);
        gameHandler.startGame();
    }
}
