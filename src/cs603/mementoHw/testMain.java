package cs603.mementoHw;

import java.util.Scanner;
import java.util.function.BooleanSupplier;

public class testMain {
    public static void main(String args[]){
        Game game = new Game();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to MiniFight\n\n");
        System.out.println("Please enter hero's name:");
        String heroName = sc.next();
        game.setUpHero(heroName);
        Hero hero = game.getHero();
        System.out.println("Please choose one:");
        System.out.println("0. Quit game");
        System.out.println("1. Fight next monster");
        int choice = sc.nextInt();
        do{
            //Check if player wants to play the game or quit the game
            if(choice==0){
                System.out.println("Thanks for playing");
            }
            else if(choice==1){
                game.setAttackSucceeds(new WinLoseAlternating());
                System.out.println(heroName+" "+hero);
                if(game.fightNextBattle()){
                    System.out.println("Congratulations!");
                }
                else{
                    System.out.println("You lost the battle. Game resets to the start of the level.");
                }
                System.out.print("Your new status: "+heroName + ": ");
                System.out.println(hero);
                System.out.println();

            }
            //If there are no more monsters then its done.
            if(!game.listIteratorLevel2.hasNext()){
                break;
            }
            System.out.println("Welcome to MiniFight");
            System.out.println("Please choose one:");
            System.out.println("0. Quit game");
            System.out.println("1. Fight next monster");
            choice = sc.nextInt();
        }while (choice!=0);

        if(game.won()){
            System.out.println("Victory is yours!");
        }

    }

    /**
     * WinLoseAlternating class which implements the BooleanSupplier
     * class and it generate the random boolean values.
     */
    static class WinLoseAlternating implements BooleanSupplier {
        /**
         * getAsBoolean method of BooleanSupplier
         * @return true or false randomly.
         */
        @Override
        public boolean getAsBoolean() {
            return Math.random() < 0.5;
        }
    }
}
