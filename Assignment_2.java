package MyTP_1;

import java.util.Arrays;
import java.util.Scanner;

class GameInstruction {
    public void gameInstruct() {
        /**The goal of this class is to inform players about the game. in other word this must
         * be read before playing the game.*/
        System.out.println("===============================================");
        System.out.println("""
                   READ THIS BEFORE PLAYING:
                   This is a guess game built up for 5 players
                   You are asked to guess a secret number
                   guessed by a guesser which is between
                   0 to 100. Good lucky !
                """);
        System.out.println("================================================");
        System.out.println();
    }

}

class Guesser {
    /**
     * This class will prompt the guesser to guess a secret number.
     */
    int secretNumber;

    public int getSecretNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Hi guesser what is your secret number ? ");
        secretNumber = input.nextInt();
        return secretNumber;
    }
}

class Players {
    /**
     * This class will prompt players all necessary information needed: number of players,
     * names of the players, guesses of the players. And returned these data for next step
     * in the game.
     */
    int numberOfPlayers;
    String[] playersNames;
    int[] playersGuesses;
    String[] countNumber = {"first", "second", "third", "fourth", "fifth"};
    int gameLimit = 5;
    char answer = 'y';

    public String[] getPlayersNames() {
        /**This is the method where we are going to prompt players, nr. of player, their names */

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of players please: ");  // Prompt the player to enter the number of players
        numberOfPlayers = input.nextInt();
        if (numberOfPlayers > gameLimit) {  //This condition control the number of players
            System.out.println("Sorry, unfortunately we are limited to 5 players only not more. See later !");
            System.exit(0);
        } else {
            System.out.println("You entered " + numberOfPlayers + " players, can you confirm it (y/n)");
        }
        answer = input.next().charAt(0);
        if (answer == 'Y' || answer == 'y') {
            System.out.println("Thanks, number of player registered: " + numberOfPlayers + " .");
        } else {
            System.out.println(" Welcome back later ");
            System.exit(0);
        }

        input.nextLine();  //Just to ignore the line store into String array
        playersNames = new String[numberOfPlayers];
        for (int i = 0; i < playersNames.length; i++) {
            System.out.println("Enter the name of the " + countNumber[i] + "  player please:  ");  // Prompt the player their names
            playersNames[i] = input.nextLine();
        }
        return playersNames;

    }


    public int[] getPlayersGuesses() {
        /**After getting all information need in the previous method, we can
         * prompt players to enter their guess number.*/
        getPlayersNames();
        Scanner input = new Scanner(System.in);
        System.out.println("Be ready to enter your guesses please : ");
        playersGuesses = new int[numberOfPlayers];
        for (int i = 0; i < playersGuesses.length; i++) {
            System.out.println("Hi " + playersNames[i] + " Enter your guess number please: ");
            playersGuesses[i] = input.nextInt();
        }
        System.out.println(Arrays.toString(playersGuesses));
        Guesser secretG = new Guesser();
        System.out.println();
        int getSecretNumFromGuesser = secretG.secretNumber;
        int[] getGuessNumFromPlayers = playersGuesses;
        String[] getPlayersNames = playersNames;
        return playersGuesses;
    }

}

class Umpire  // Umpire collects all data and make comparison
{
    /**
     * Our Umpire collect all data need in other to compare and make decision
     */
    String gameInfo;
    int getSecretNumFromGuesser;
    int[] getGuessNumFromPlayers;
    String[] getPlayersNames;

    public void collectGameInfo() {
        GameInstruction gameInstr = new GameInstruction();
        gameInstr.gameInstruct();
    }

    public void collectGuesserNum() {
        Guesser guesser = new Guesser();
        getSecretNumFromGuesser = guesser.getSecretNumber();
    }

    public void collectPlayersNames() {
        Players names = new Players();
        getPlayersNames = names.getPlayersNames();
    }

    public void collectPlayersGuesses() {
        Players playersGuesses = new Players();
        getGuessNumFromPlayers = playersGuesses.getPlayersGuesses();
    }

    public void compareGuesses() {
        /**After gathering or collecting all data, this method will compare date and determine the winner,
         * or the loser of the game*/
        Scanner input = new Scanner(System.in);
        String[] countNumber = {"first", "second", "third", "fourth", "fifth"};
        int[] mark = {0, 10};
        System.out.println("This is the secret number: " + getSecretNumFromGuesser);
        for (int i = 0; i < getPlayersNames.length; i++) {
            System.out.println("The " + countNumber[i] + " player's names: " + getPlayersNames[i]);
        }
        input.nextLine();
        for (int j = 0; j < getGuessNumFromPlayers.length; j++) {
            System.out.println(getGuessNumFromPlayers[j] + " is the guess of the " + countNumber[j] + " player maned " + getPlayersNames[j]);
            if (getSecretNumFromGuesser == getGuessNumFromPlayers[j]) {
                System.out.println("Congratulation !" + getPlayersNames[j] + " you are the winner and you get " + mark[1] + " points");
            } else {
                System.out.println(getPlayersNames[j] + " you loose and you get  " + mark[0] + " point");
            }

        }
    }
}

public class Assignment_2 {
    public static void main(String[] args) {
        Umpire getIt = new Umpire();
        getIt.collectGameInfo();
        getIt.collectGuesserNum();
        getIt.collectPlayersNames();
        getIt.collectPlayersGuesses();
        getIt.compareGuesses();


    }
}
