import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameCreated = false;
        // Waiting for user to enter create_game
        while(!gameCreated){
            String input = scanner.next();
            if(input.equals("create_game")){
                gameCreated = true;
            }else{
                // Will be replaced with exception
                System.out.println("Wrong input");
            }
        }
        scanner.close();
        Game game = new Game();
        game.setUpGame();
    }
}
