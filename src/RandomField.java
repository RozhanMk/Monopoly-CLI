import exceptions.NegativeCashException;

public class RandomField extends Field{
    public RandomField(int id, boolean colored) {
        super(id, colored);
    }
    public int getRandomNumber() {
        return (int) ((Math.random() * 6) + 1); //random number from 1 to 7
    }
    @Override
    public void onFieldActions(Game game , Player player) throws NegativeCashException{
        int number = getRandomNumber();
        if(number == 1){
            System.out.println("Get 200$ from the bank");
            player.increaseCash(200);
        }
        if(number == 2){
            System.out.println("Go to the prison");
            Prison.prisoners.add(player);
            player.setIsInJail(true);
            player.setPosition(12);
        }
        if(number == 3){
            System.out.println("Give 10 percent of your money to the bank");
            player.decreaseCash( (1.0/10)*player.getCash() );
        }
        if(number == 4){
            System.out.println("Go 3 houses further");
            player.setPosition((player.getPosition()+3) % 24);
        }
        if(number == 5){
            System.out.println("You are free");
            Prison.removePrisoner(player);
            player.setIsInJail(false);
        }
        if(number == 6){
            System.out.println("You don't need to pay taxes for once");
            player.setNoTax(true);
        }
        if(number == 7){
            System.out.println("Give 10$ to each player");
            int playerIndex = player.getId();
            for (int i = 0; i < game.getPlayers().size(); i++) {
                if( i != playerIndex ){
                    game.getPlayers().get(i).increaseCash(10);
                    player.decreaseCash(10);
                }
            }
        }

    }
}
