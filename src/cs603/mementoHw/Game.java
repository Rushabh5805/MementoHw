package cs603.mementoHw;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.function.BooleanSupplier;

public class Game {
    public Hero hero;
    //Arraylist and ListIterator to maintain the list of monsters
    ArrayList<Character> monstersLevel1 = new ArrayList<>();
    ArrayList<Character> monstersLevel2 = new ArrayList<>();
    ListIterator<Character> listIteratorLevel1, listIteratorLevel2;

    //Temporary arraylists and ListIterator created in order to
    //restore on the particular level
    ArrayList<Character> temp1 = new ArrayList<>();
    ArrayList<Character> temp2 = new ArrayList<>();
    ListIterator<Character> listLevel1temp, listLevel2temp;


    BooleanSupplier booleanSupplier;
    Hero.HeroState saved;
    int gameWon = 0;
    static ArrayList<Integer> MONSTERS_PER_LEVEL = new ArrayList<>();
    static int TOTAL_MONSTERS = 5;
    int fightNextBattleCalled = 0;

    /**
     * Constructor of Game class which will initialize all the
     * monsters and adding them into the ArrayList and initializing
     * the ListIterator
     */
    public Game(){
        Character regularMonster = new Character(2, 1);
        Character bossMonster1 = new Character(4, 2);
        Character regularMonster1 = new Character(3, 2);
        Character regularMonster2 = new Character(3, 2);
        Character bossMonster2 = new Character(6, 3);
        monstersLevel1.add(regularMonster);
        monstersLevel1.add(bossMonster1);
        monstersLevel2.add(regularMonster1);
        monstersLevel2.add(regularMonster2);
        monstersLevel2.add(bossMonster2);
        listIteratorLevel1 = monstersLevel1.listIterator();
        listIteratorLevel2 = monstersLevel2.listIterator();
        listLevel1temp = monstersLevel1.listIterator();
        listLevel2temp = monstersLevel2.listIterator();
        MONSTERS_PER_LEVEL.add(0,2);
        MONSTERS_PER_LEVEL.add(1,3);
    }

    /**
     * setUpHero method to initialize the Hero with his life, maxLife,
     * attackPoints and heroName
     * @param heroName : name of the hero
     */
    public void setUpHero(String heroName){
        hero = new Hero(5, 1, 5,heroName);
    }

    /**
     * Getter method for the hero
     * @return the object of hero
     */
    public Hero getHero(){
        return hero;
    }

    /**
     * getNextOpponent() method to get the next opponent after the
     * current opponent
     * @return the object of character class
     */
    public Character getNextOpponent(){
        if (listLevel1temp.hasNext()) {
            if (fightNextBattleCalled == 1) {
                listLevel1temp.next();
            }
            return listLevel1temp.next();
        }
        return listLevel2temp.next();
    }

    /**
     * currentOpponent() method is used to return the current opponent
     * to which hero is fighting currently
     * @return the object of the character class
     */
    public Character currentOpponent(){
        if (listIteratorLevel1.hasNext()) {
            fightNextBattleCalled=1;
            return listIteratorLevel1.next();
        }
        return listIteratorLevel2.next();
    }

    /**
     * setAttackSucceeds() method is used set the value
     * of the booleanSupplier
     * @param bs
     */
    public void setAttackSucceeds(BooleanSupplier bs){
        this.booleanSupplier=bs;
    }

    /**
     * fightNextBattle() method carries out the fight between
     * the hero and the Monster. They attack each other alternatively
     * until the life of one of them is zero.
     * also, after every battle hero wins the checkpoint is created.
     * and if he loses then his life and attackpoints is restored.
     * @return true if Hero wins and false if hero loses.
     */
    public boolean fightNextBattle(){
        Character character = currentOpponent();
        System.out.println(character);
        int counter = 0;
        int temp = character.getLife();
        while (hero.getLife()>0 && character.getLife()>0){
            if(counter%2==0){
                if(booleanSupplier.getAsBoolean()){
                    character.damage(hero.getAttack());
                }

            }
            else if(counter%2!=0){
                if(booleanSupplier.getAsBoolean()) {
                    hero.damage(character.getAttack());
                }
            }
            counter++;
        }
        if(hero.getLife()>0){
            hero.maxLife=hero.maxLife+1;
            hero.attackPoints+=1;
            saved = hero.checkpoint();
            if(!listIteratorLevel2.hasNext()){
                gameWon=1;
            }
        }
        else if(hero.getLife()<=0){
            if(temp==2||temp==4){
                Character regularMonster = new Character(2, 1);
                Character bossMonster1 = new Character(4, 2);
                temp1.add(regularMonster);
                temp1.add(bossMonster1);
                listIteratorLevel1=temp1.listIterator();
                listLevel1temp = temp1.listIterator();
                fightNextBattleCalled=0;
            }
            else if(temp==3||temp==6){
                Character regularMonster1 = new Character(3, 2);
                Character regularMonster2 = new Character(3, 2);
                Character bossMonster2 = new Character(6, 3);
                temp2.add(regularMonster1);
                temp2.add(regularMonster2);
                temp2.add(bossMonster2);
                listIteratorLevel2=temp2.listIterator();
                listLevel1temp.next();
                listLevel1temp.next();
                listLevel2temp=temp2.listIterator();
                fightNextBattleCalled=0;
            }
            hero.restore(saved);
            return false;
        }
        return true;
    }

    /**
     * won() method is used to check if hero completes both the level
     * after winning them
     * @return true if hero wins and flase if hero loses.
     */
    public  boolean won(){
       if(gameWon==1){
           return true;
       }
        else{
           return false;
       }
    }
}
