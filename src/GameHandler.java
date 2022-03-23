import exceptions.*;

import java.util.ArrayList;
import java.util.List;
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
                    game.players.get(i).setPrevDice(game.players.get(i).getDice());
                    game.players.get(i).setDice(diceNumber);
                    game.players.get(i).updatePosition();
                    game.players.get(i).getField().onFieldActions(game.players.get(i));
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
                if(player.getField() instanceof Invest){
                    try{
                        player.buy( (Invest) player.getField());
                    }catch (InvestOutOfCashException | InvestOwnedByAnotherException e){
                        System.out.println(e.getMessage());
                    }
                }else{
                    System.out.println("This field is not buyable");
                }
            }
            else if(input.contains("sell")){
                String[] inputs = input.split(" ");
                try{
                    int position = Integer.parseInt(inputs[1]);
                    if(player.getField(position) instanceof Invest){
                        try{
                            player.sell((Invest) player.getField(position));
                        }catch (InvestNotOwnedException e){
                            System.out.println(e.getMessage());
                        }
                    }else{
                        System.out.println("This field is not sellable");
                    }
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("You didn't enter the position");
                }
            }
            else if(input.equals("free")){
                //todo
            }
            else if(input.equals("invest")){
                try{
                    player.invest();
                }
                catch (NotTypeException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(input.contains("fly")){
                String[] inputs = input.split(" ");
                if(inputs.length < 2){
                    System.out.println("You didn't enter the destination");
                }else{
                    if(player.getField() instanceof Airport){
                        try{
                            player.fly((Airport) player.getField() , Integer.parseInt(inputs[1]));
                        }
                        catch (SameAirportException | NotTypeException e){
                            System.out.println(e.getMessage());
                        }
                    }else{
                        System.out.println("You are not at airport");
                    }
                }
            }
            else if(input.equals("build")){
                //todo
            }
            else if(input.equals("index")){
                System.out.println(player.getPosition() + 1);
            }else if(input.equals("property")){
                System.out.println("Cash : " + player.getCash());
                if(player.getProperties() == null){
                    System.out.println("You don't own anything");
                }else{
                    System.out.println("Properties : " + player.getProperties());
                }
            }else if(input.equals("time")){
                //todo
            }else if(input.equals("rank")){
                System.out.println("Your rank is : " + game.getRank(player));
            }else if(input.equals("end")){
                turnFinished = true;
            }else {
                System.out.println("Wrong input please try again");
            }
        }

    }
    public void checkWin(){
        //todo
        gameRound++;
        startRound();
    }
}
