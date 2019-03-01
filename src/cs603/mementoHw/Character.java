package cs603.mementoHw;

public class Character {
    int currentLife, attackPoints;
    int maxLife=0;
    public Character(int currentLife, int attackPoints){
        this.currentLife=currentLife;
        this.attackPoints=attackPoints;
    }
    public void damage(int amount){
        currentLife = currentLife-amount;
    }
    public int getLife(){
        return currentLife;
    }
    public int getAttack(){
        return attackPoints;
    }
    public String toString(){
        return("Next Monster : Life = " + currentLife+", attack = "+attackPoints );
    }
}
