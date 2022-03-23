import exceptions.InvestNotOwnedException;
import exceptions.InvestOutOfCashException;
import exceptions.InvestOwnedByAnotherException;
import exceptions.NotTypeException;
import exceptions.NotInvestException;
import exceptions.SameAirportException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Player {
    private String name;
    private int id; // 1 2 3 ...
    private double cash;
    private double saved;
    private int position; // from 0 to 23
    private int prevDice;
    private boolean[] areas; // from 0 to 23. All the areas
    private boolean isInJail;
    private int jailCount;
    private int dice;
    private boolean hasInvestInBank;
    private boolean noTax;


    public Player(String name , int id){
        this.name = name;
        this.id = id;
        cash = 1500;
        saved = 0;
        position = 0;
        dice = 0;
        jailCount = 0;
        areas = new boolean[24];
        noTax = false;
    }

    public int getPrevDice() {
        return prevDice;
    }
    public void setPrevDice(int prevDice) {
        this.prevDice = prevDice;
    }
    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
    public double getSaved() {
        return this.saved;
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

    public void setSaved(double saved) {
        this.saved = saved;
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

    public Field getField(){
        return BoardState.getInstance().getField(position);
    }

    public Field getField(int position){
        return BoardState.getInstance().getField(position);
    }

    public boolean isIsInJail() {
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

    public boolean isHasInvestInBank() {
        return this.hasInvestInBank;
    }
    public void setHasInvestInBank(boolean hasInvestInBank) {
        this.hasInvestInBank = hasInvestInBank;
    }
    public boolean isNoTax() {
        return this.noTax;
    }

    public void setNoTax(boolean noTax) {
        this.noTax = noTax;
    }

    public boolean buy(Invest invest) throws InvestOwnedByAnotherException, InvestOutOfCashException {
        
        if(invest.getCost() <= cash){
            if(invest.getOwner() == null){
                decreaseCash(invest.getCost());
                areas[invest.getId()] = true;
                invest.setOwner(this);
                return true;
            }else{
                throw new InvestOwnedByAnotherException("this place has been bought!");
            }
            
        }else{
            throw new InvestOutOfCashException("you can't afford to buy this place!");
        }
    }
    public boolean free(){
        if(this.getCash() >= 50){
            this.decreaseCash(50);
            this.isInJail = false;
            Prison.removePrisoner(this);
            return true;
        }
        return false;
    }
    public boolean sell(Invest invest) throws InvestNotOwnedException{
        if( areas[invest.getId()] ){
            increaseCash(invest.getCost()/2);
            areas[invest.getId()] = false;
            invest.setOwner(null);
            return true ;
        }else{
            throw new InvestNotOwnedException("you don't own this place!");
        }
    }
    public boolean fly(Airport airport , int airportId) throws NotTypeException , SameAirportException{
        if( airport.getId() != airportId ){
            if(getField(airportId) instanceof Airport){
                position = airportId;
                cash -= airport.getFine();
                return true;
            }else{
                throw new NotTypeException("the destination is not an airport!");
            }
        }else{
            throw new SameAirportException("enter another airport!");
        }
    }
    public boolean invest() throws NotTypeException{
        if(position == 20){
            if(!hasInvestInBank){
                saved = cash/2;
                cash -= (cash/2);
                hasInvestInBank = true;
            }
            return true;
        }else{
            throw new NotTypeException("you're not in bank!");
        }
    }
    // return a list of properties
    public ArrayList<Integer> getProperties(){
        boolean withoutProperty = true;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int i = 0 ; i < 24 ; i++){
            if(areas[i]){
                temp.add(i+1);
                withoutProperty = false;
            }
        }
        if(withoutProperty){
            return null;
        }
        return temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && Double.compare(player.cash, cash) == 0 && Double.compare(player.saved, saved) == 0 && position == player.position && isInJail == player.isInJail && jailCount == player.jailCount && dice == player.dice && hasInvestInBank == player.hasInvestInBank && Objects.equals(name, player.name) && Arrays.equals(areas, player.areas);
    }
}