import exceptions.InvestOutOfCashException;
import exceptions.InvestOwnedByAnotherException;
import exceptions.NotInvestException;

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
                    game.players.get(i).setDice(diceNumber);
                    game.players.get(i).updatePosition();
                    playerTurn(game.players.get(i) , scanner);
                }
            }
        }
        scanner.close();
        gameRound++;
        checkWin();
    }
    public void playerTurn(Player player , Scanner scanner){
        boolean turnFinished = false;
        while (!turnFinished){
            String input = scanner.next();
            if(input.equals("buy")){
                if(BoardState.getInstance().getField(player.getPosition()) instanceof Invest){
                    try{
                        player.buy( (Invest) BoardState.getInstance().getField(player.getPosition()));
                    }catch (InvestOutOfCashException e){
                        System.out.println("You don't have enough Money to buy this field");
                    }catch (InvestOwnedByAnotherException e){
                        System.out.println("This field is owned by another player");
                    }
                }else{
                    System.out.println("This field is not buyable");
                }
            }
        }

    }
    public void checkWin(){
        //todo
        gameRound++;
        startRound();
    }
}
