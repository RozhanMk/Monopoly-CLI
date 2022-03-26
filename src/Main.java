import java.util.Scanner;

import exceptions.NegativeCashException;

public class Main {
    public static void main(String[] args) throws NegativeCashException {
        Scanner scanner = new Scanner(System.in);
        boolean gameCreated = false;
        // Waiting for user to enter create_game
        while(!gameCreated){
            String input = scanner.nextLine();
            if(input.equals("create_game")){
                gameCreated = true;
            }else{
                // Will be replaced with exception
                System.out.println("no game created");
            }
        }
        Game game = new Game();
        game.setUpGame(scanner);
    }
}
