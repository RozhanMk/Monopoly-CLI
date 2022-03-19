import java.util.ArrayList;

public class Player {
    private String name;
    private int id; // 1 2 3 ...
    private double cash;
    private int position; // from 0 to 23
    private boolean[] areas; // from 0 to 23. All the areas
    private boolean isInJail;
    private int jailCount;
    private int dice;
    private boolean hasInvestInBank;

    public Player(String name , int id){
        this.name = name;
        this.id = id;
        cash = 1500;
        position = 0;
        dice = 0;
        jailCount = 0;
        areas = new boolean[24];
    }


    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public double getCash() {
        return this.cash;
    }

    public void increaseCash(double cost) {
        cash += cost;
    }

    public void decreaseCash(double cost) {
        cash -= cost;
    }

    public boolean checkIsOwner(int investId){
        return areas[investId];
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void updatePosition(){
        int newPosition = (position + dice) % 24;
        position = newPosition;
    }

    public boolean getIsInJail() {
        return this.isInJail;
    }

    public void setIsInJail(boolean isInJail) {
        this.isInJail = isInJail;
    }

    public int getJailCount() {
        return this.jailCount;
    }

    public void setJailCount(int jailCount) {
        this.jailCount = jailCount;
    }

    public int getDice() {
        return this.dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public boolean getHasInvestInBank() {
        return this.hasInvestInBank;
    }

    public void setHasInvestInBank(boolean hasInvestInBank) {
        this.hasInvestInBank = hasInvestInBank;
    }

    public void buy(Invest invest){
        if(invest.getCost() <= cash){
            decreaseCash(invest.getCost());
            areas[invest.getId()] = true;
            invest.setOwner(this);
            return;
        }
        System.out.println("Not enough money to buy here!");
        return;
    }
    public void sell(Invest invest){
        if( areas[invest.getId()] ){
            increaseCash(invest.getCost()/2);
            areas[invest.getId()] = false;
            invest.setOwner(null);
            return;
        }
        System.out.println("You don't own here!");
        return;
    }
}
