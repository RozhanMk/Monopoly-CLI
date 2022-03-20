import java.util.Scanner;

public class GameHandler {
    private Game game;
    private int gameRound = 1;
    public GameHandler(Game game) {
        this.game = game;
    }
    public void startRound(){
        System.out.println("Round " + gameRound);
        Scanner scanner = new Scanner(System.in);
        for(int i = 0 ; i < game.players.size() ; i++){
            System.out.println(game.players.get(i).getName() + "'s " + "turn:");
            boolean diceEntered = false;
            while (!diceEntered){
                int diceNumber = scanner.nextInt();
                if(diceNumber>6 || diceNumber<1){
                    System.out.println("Wrong dice number please try again");
                }else{
                    diceEntered = true;
                    int newPosition = (game.players.get(i).getPosition() + diceNumber) % 24;
                    game.players.get(i).setPosition(newPosition);
                    playerTurn(game.players.get(i));
                }
            }
        }
        gameRound++;
        checkWin();
    }
    public void playerTurn(Player player){

    }
    public void checkWin(){
        //todo
        gameRound++;
        startRound();
    }
}
