package cs603.mementoHw;

public class Hero extends Character{
    String name;
    int maxLife, attackBonus = 1;

    /**
     * Constructor of hero class to initialize the properties of the hero
     * @param currentLife current life of the hero
     * @param attackPoints value of how much damage hero can cause with one attack
     * @param maxLife maximum life that hero has
     * @param name name of the hero
     */
    public Hero(int currentLife, int attackPoints, int maxLife, String name) {
        super(currentLife, attackPoints);
        this.maxLife=maxLife;
        this.name=name;
    }

    @Override
    public int getAttack() {
        return super.getAttack();
    }

    @Override
    public void damage(int amount) {
        super.damage(amount);
    }

    @Override
    public int getLife() {
        return super.getLife();
    }

    public int getMaxLife(){ return maxLife;}

    /**
     * Creates the checkpoint of the current state of the hero
     * @return the object of class HeroState
     */
    public HeroState checkpoint(){
        HeroState hs = new HeroState();
        hs.setAttackPoints(attackPoints);
        hs.setMaxLife(maxLife);
        return hs;
    }

    /**
     * Restores the hero's state from the herostate object
     * @param heroState object that contains the value of hero's
     *                  properties
     */
    public void restore(HeroState heroState){
        attackPoints = heroState.getAttackPoints();
        maxLife = heroState.getMaxLife();
        currentLife=heroState.getMaxLife();
    }

    public String toString(){
        return ("life/maxLife = " + currentLife+"/"+maxLife+", attack = "+ attackPoints);
    }

    /**
     * HeroState class that will have the methods to set and get the
     * properties of the Hero
     */
    class HeroState{
        private int attackPoints,maxLife;


        private void setAttackPoints(int attackPoints){
            this.attackPoints=attackPoints;
        }
        private void setMaxLife(int maxLife){
            this.maxLife=maxLife;
        }
        private int getAttackPoints(){
            return attackPoints;
        }
        private int getMaxLife() {
            return maxLife;
        }
    }
}
