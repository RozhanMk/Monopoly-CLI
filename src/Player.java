import exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Player {
    private String name;
    private int id; // 1 2 3 ...
    private double cash;
    private double saved; //invested money in bank
    private int position; // from 0 to 23
    private int prevDice;
    private boolean[] areas; // from 0 to 23. All the areas
    private boolean isInJail;
    private int dice;
    private boolean hasInvestInBank;
    private boolean noTax; //implement the 6th luck card
    private List<Empty> buildings = new ArrayList<>();

    public Player(String name , int id){
        this.name = name;
        this.id = id;
        cash = 1500;
        saved = 0;
        position = 0;
        dice = 0;
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

    public void decreaseCash(double cost) throws NegativeCashException{
        cash -= cost;
        if(cash<0){
            throw new NegativeCashException("negative cash");
        }
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

    public boolean buy(Invest invest) throws InvestOwnedByAnotherException, InvestOutOfCashException, NegativeCashException {
        
        if(invest.getCost() <= cash){
            if(invest.getOwner() == null){
                decreaseCash(invest.getCost());
                areas[invest.getId()] = true;
                invest.setOwner(this);
                if(invest instanceof Empty){
                    buildings.add((Empty) invest);
                }
                return true;
            }else{
                throw new InvestOwnedByAnotherException("this place has been bought!");
            }
            
        }else{
            throw new InvestOutOfCashException("you can't afford to buy this place!");
        }
    }
    public boolean free() throws InvestOutOfCashException, NegativeCashException{
        if(cash >= 50){
            decreaseCash(50);
            isInJail = false;
            Prison.removePrisoner(this);
            return true;
        }else{
            throw new InvestOutOfCashException("You don't have enough money!");
        }
        
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

    //invest half of cash in the bank
    public boolean invest() throws NotTypeException, NegativeCashException{
        if(position == 20){
            if(!hasInvestInBank){
                saved = cash/2;
                decreaseCash(cash/2);
                hasInvestInBank = true;
            }
            return true;
        }else{
            throw new NotTypeException("you're not in bank!");
        }
    }

    // return a list of properties
    public ArrayList<Integer> getProperties(){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int i = 0 ; i < 24 ; i++){
            if(areas[i]){
                temp.add(i+1);
            }
        }
        return temp;
    }

    public boolean isBankrupt(){
        if(cash>=0){
            return false;
        }
        double res = 0;
        for (int i = 0; i < getProperties().size(); i++) {
            if(getField(getProperties().get(i)) instanceof Cinema){
                res+= (getField(getProperties().get(i)).getCost())/2;
            }else if(getField(getProperties().get(i)) instanceof Empty){
                if(((Empty)getField(getProperties().get(i))).getLevel() == 5){
                    res+=(((Empty)getField(getProperties().get(i))).getLevel()*150+100-50)/2;
               }else{
                    res+=(((Empty)getField(getProperties().get(i))).getLevel()*150+100)/2;
               }
            }
        }
        return (int)res<(-(int)cash);
    }

    public void build(Empty empty) throws InvestNotOwnedException, BuildingsNotEqual, MaxBuildingsReached, InvestOutOfCashException, NegativeCashException {
        if(empty.getOwner().equals(this)){
 
            if(empty.getLevel() == 5){
                throw new MaxBuildingsReached("You can't build any more buildings");
            }
            if(Empty.getNumberOfBuildings() == 0 && empty.getLevel() != 4){
                throw new MaxBuildingsReached("Board have reached its maximum buildings");
            }
            int maxBuildings = 0;
            for(int i = 0 ; i < buildings.size() ; i++){
                if(buildings.get(i).getLevel() > maxBuildings){
                    maxBuildings = buildings.get(i).getLevel();
                }
            }
            boolean is_equal = true;
            outer: for(int i = 0 ; i < buildings.size() ; i++){
                for (int j = i+1; j < buildings.size(); j++) {
                    if(buildings.get(i).getLevel() != buildings.get(j).getLevel()){
                        is_equal = false;
                        break outer;
                    }
                }
            }
            //check if buildings are equal
            if(empty.getLevel() < maxBuildings || is_equal){
                if(empty.getLevel() == 4){
                    //build Hotel
                    if(cash >= 100){
                        decreaseCash(100);
                        empty.setLevel(empty.getLevel() + 1);
                        empty.setFine(600);
                        empty.setHotel(true);
                        Empty.setNumberOfBuildings(Empty.getNumberOfBuildings() + 4);
                    }else{
                        throw new InvestOutOfCashException("You don't have enough money to build here!");
                    }
                }else{
                    //build building in empty houses
                    if(cash >= 150){
                        decreaseCash(150);
                        empty.setLevel(empty.getLevel() + 1);
                        empty.setFine(empty.getLevel()*100 + 50);
                        Empty.setNumberOfBuildings(Empty.getNumberOfBuildings()-1);
                    }else{
                        throw new InvestOutOfCashException("You don't have enough money to build here!");
                    }
                }
            }else{
                throw new BuildingsNotEqual("Your buildings are not equal!");
            }
        }else{
            throw new InvestNotOwnedException("You don't own this empty field!");
        }

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && Double.compare(player.cash, cash) == 0 && Double.compare(player.saved, saved) == 0 && position == player.position && isInJail == player.isInJail && dice == player.dice && hasInvestInBank == player.hasInvestInBank && Objects.equals(name, player.name) && Arrays.equals(areas, player.areas);
    }
}