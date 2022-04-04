import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameCreated = false;
        // Waiting for user to enter create_game
        while(!gameCreated){
            String input = scanner.nextLine();
            if(input.equals("create_game")){
                gameCreated = true;
                System.out.println("new game created");
            }else{
                // Will be replaced with exception
                System.out.println("no game created");
            }
        }
        Game game = new Game();
        game.setUpGame(scanner);
    }
}
