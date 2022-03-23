public class Cinema extends Invest{
    public Cinema(int id, boolean colored , ColorType color) {
        super(id, colored , color);
        setCost(200);
    }
    public void autoActs(Player player){
        if(!player.checkIsOwner(super.getId())){
            Player owner = super.getOwner();
            //paying fine
            if(owner != null){
                int countCinema = 0;
                if(owner.checkIsOwner(14)){
                    countCinema++;
                }
                if(owner.checkIsOwner(7)){
                    countCinema++;
                }
                if(owner.checkIsOwner(3)){
                    countCinema++;
                }
                if(countCinema == 1){
                    setFine(25);
                }
                if(countCinema == 2){
                    setFine(50);
                }
                if(countCinema == 3){
                    setFine(100);
                }
                player.decreaseCash(getFine());
            }
    
        }
    }
}
