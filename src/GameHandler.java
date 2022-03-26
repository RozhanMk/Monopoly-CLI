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
    public void startRound(Scanner scanner) throws NegativeCashException{
        System.out.println("Round " + gameRound);
        for(int i = 0 ; i < game.players.size() ; i++){
            System.out.println(game.players.get(i).getName() + "'s " + "turn:");
            boolean diceEntered = false;
            while (!diceEntered) {
                int diceNumber = 0;
                boolean diceCheck = false;
                while (!diceCheck){
                    try {
                        diceNumber = Integer.parseInt(scanner.nextLine());
                        diceCheck = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Please dice number");
                    }
                }
                if (diceNumber > 6 || diceNumber < 1) {
                    System.out.println("Wrong dice number please try again");
                } else {
                    diceEntered = true;
                    game.players.get(i).setPrevDice(game.players.get(i).getDice());
                    game.players.get(i).setDice(diceNumber);
                    //here player can enter dice twice
                    if (game.players.get(i).getDice() == 6) {
                        diceEntered = false;
                        while (!diceEntered) {
                            diceNumber = 0;
                            diceCheck = false;
                            while (!diceCheck){
                                try {
                                    diceNumber = Integer.parseInt(scanner.nextLine());
                                    diceCheck = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Please dice number");
                                }
                            }
                            if (diceNumber > 6 || diceNumber < 1) {
                                System.out.println("Wrong dice number please try again");
                            } else {
                                diceEntered = true;
                            }
                        }
                        game.players.get(i).setPrevDice(game.players.get(i).getDice());
                        game.players.get(i).updatePosition();
                        game.players.get(i).setDice(diceNumber);
                    }
                    //check if player go to prison or not
                    if (game.players.get(i).getDice() == 6 && game.players.get(i).getPrevDice() == 6) {
                        Prison.prisoners.add(game.players.get(i));
                        game.players.get(i).setIsInJail(true);
                        game.players.get(i).setPosition(12);
                    }
                    // check if Player is in jail or not ; if player is in jail he/she only can have dice number 1 to get out of jail
                    if (game.players.get(i).isIsInJail()) {
                        if (game.players.get(i).getDice() == 1) {
                            Prison.removePrisoner(game.players.get(i));
                            game.players.get(i).updatePosition();
                        }
                    } else {
                        game.players.get(i).updatePosition();
                    }
                    try{
                        game.players.get(i).getField().onFieldActions(game, game.players.get(i));
                    }
                    catch (NegativeCashException ignored){

                    }
                    playerTurn(game.players.get(i), scanner);
                }
            }
        }
        if(gameRound == 1){
            game.turnByDice();
        }
        roundActions();
        checkWin(scanner);
    }
    public void playerTurn(Player player , Scanner scanner){
        boolean turnFinished = false;
        boolean negativeCash = false;
        boolean builded = false;
        if(player.isBankrupt()){
            if(game.players.size() != 1) {
                System.out.println(player.getName() + " You have lost the game!!");
                game.players.remove(player);
            }
            turnFinished = true;
        }
        if(!player.isBankrupt() && player.getCash() < 0){
            System.out.println("You cash is negative ; you have to sell your items");
            negativeCash = true;
        }
        while (!turnFinished){
            //checks if user has built a house or not
            if(negativeCash && player.getCash()>0){
                negativeCash = false;
            }
            String input = scanner.nextLine();
            if(input.equals("buy")){
                if(player.getField() instanceof Invest){
                    try{
                        player.buy( (Invest) player.getField());
                    }catch (InvestOutOfCashException | InvestOwnedByAnotherException  | NegativeCashException e){
                        System.out.println(e.getMessage());
                    }
                }else{
                    System.out.println("This field is not buyable");
                }
            }
            else if(input.contains("sell")){
                String[] inputs = input.split(" ");
                try{
                    int position = Integer.parseInt(inputs[1]) - 1;
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
                if(player.getField() instanceof Prison){
                    try {
                        player.free();
                        System.out.println("You bought your freedom =)");
                    } catch (InvestOutOfCashException  | NegativeCashException e) {
                        System.out.println(e.getMessage());
                    }
                }else{
                    System.out.println("You can't use this input on nonPrison houses");
                }
            }
            else if(input.equals("invest")){
                try{
                    player.invest();
                }
                catch (NotTypeException  | NegativeCashException  e){
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
                            player.fly((Airport) player.getField() , Integer.parseInt(inputs[1]) - 1);
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
                if(!builded){
                    if(player.getField() instanceof Empty){
                        try{
                            player.build((Empty)player.getField());
                        }catch(InvestNotOwnedException| BuildingsNotEqual| MaxBuildingsReached| NegativeCashException |InvestOutOfCashException e){
                            System.out.println(e.getMessage());
                        }catch (NullPointerException e){
                            System.out.println("You don't own this empty field");
                        }
                    }
                    else{
                        System.out.println("You cannot build here!");
                    }
                    builded = true;
                }else{
                    System.out.println("you can't build any more house");
                }
            }
            else if(input.equals("index")){
                System.out.println(player.getPosition() + 1);
            }else if(input.equals("property")){
                System.out.println("Cash : " + player.getCash());
                if(player.getProperties().size() == 0){
                    System.out.println("You don't own anything");
                }else{
                    System.out.println("Properties : " + player.getProperties());
                }
            }else if(input.equals("time")){
                //todo
            }else if(input.equals("rank")){
                System.out.println("Your rank is : " + game.getRank(player));
            }else if(input.equals("end")){
                if(negativeCash){
                    System.out.println("You can't end your turn unless you sell your items ( Your debt is " + Math.abs(player.getCash()) + " )"  );
                }else{
                    turnFinished = true;
                }
            }else {
                System.out.println("Wrong input please try again");
            }
        }

    }
    public void roundActions() throws NegativeCashException{
        Prison.prisonCheck();
    }
    public void checkWin(Scanner scanner) throws NegativeCashException{
        if(game.players.size() == 1){
            System.out.println(game.players.get(0).getName() + " you have won the game");
            System.out.println("Game has finished");
            System.exit(0);
        }else{
            gameRound++;
            startRound(scanner);
        }
    }
}
