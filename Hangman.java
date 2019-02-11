/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_2;

import javax.swing.JOptionPane;
import java.util.Random;

/**
 *
 * @author Carlos Vasquez and Joseph Torii
 */
public class Hangman {
    public static String randomWordGenerator(){
        String[] wordSet = new String[8];
        wordSet[0] = "gonzaga";
        wordSet[1] = "kennel";
        wordSet[2] = "washington";
        wordSet[3] = "subaru";
        wordSet[4] = "alchemy";
        wordSet[5] = "wallflower";
        wordSet[6] = "supra";
        wordSet[7] = "soccer";
        Random rand = new Random();
        int choice = rand.nextInt(8);
        String randWord = wordSet[choice];
        return randWord;
    }
    
    public static boolean inputValidator(char input){
        int ascii = input;
        int upperCase = ascii - 65;
        int lowerCase = ascii - 97;
        if((upperCase >= 0 && upperCase <= 25) || (lowerCase >= 0 && lowerCase <= 25)) 
            return true;
        else
            return false;
    }
    public static void theGame(String theWord){
        int wordLength = theWord.length();
        char[] letters = new char[wordLength];
        letters = theWord.toCharArray();
        char[] visibleArray = new char[wordLength];
        for (int i = 0; i < wordLength; i++){
            visibleArray[i] = '*';
        }
        System.out.println("OK! You get SIX guesses!");
        System.out.println();
        System.out.println("Here is your word!");
        for (int i = 0; i < wordLength; i++)
            System.out.print(visibleArray[i]);
        System.out.println();
        char[] guesses = new char[26];
        
        int counter = 0;
        int strikes = 0;
        int numberOfGuesses = 0;
        
        while (counter < wordLength && strikes < 6){
            String letterInput = JOptionPane.showInputDialog("Enter in a letter: ");   
            char letter = letterInput.charAt(0);
            guesses[numberOfGuesses] = letter;
            numberOfGuesses++;
            int tempCounter = counter;
            if (inputValidator(letter)){
                for (int k = 0; k < wordLength; k++){
                    if (letter == letters[k]){
                        counter++;
                        visibleArray[k] = letter;
                    }                      
                }
            } 
            else 
                System.out.println("Invalid Input...that is a strike");
            
            if (counter == tempCounter)
                strikes++;    
            switch (strikes){
                case 1:
                    System.out.println("You have ONE strike!");
                    break;
                case 2:
                    System.out.println("You have TWO strikes!");
                    break;
                case 3:
                    System.out.println("You have THREE strikes!");
                    break;
                case 4:
                    System.out.println("You have FOUR strikes!");
                    break;
                case 5:
                    System.out.println("You have FIVE strikes!");
                    break;
                case 6:
                    System.out.println("You have SIX strikes and have lost :(");
                    System.out.print("The word was: ");
                    for (int m = 0; m < wordLength; m++)
                        System.out.print(letters[m]);
                    System.out.println();
                    break;
            }
            
            System.out.println("Here is the current word with your guesses");
            for (int i = 0; i < wordLength; i++){                
                System.out.print(visibleArray[i]);                
            }
            System.out.println();
            System.out.println("And here are all of your guesses so far");
            int temp = numberOfGuesses;
            while (temp >= 0) {
                System.out.print(guesses[temp] + " ");
                temp--;
            }
            System.out.println();
            System.out.println();
            if (counter == wordLength){
                System.out.println("Congragulations! You have won!");
                System.out.println();
                System.out.println();
                System.out.println();
            }
            
        }
    }
    public static void randomWordGame(){
        String theWord = randomWordGenerator();
        theGame(theWord);
    } 
    
    public static void inputWordGame(){
        String userWord = JOptionPane.showInputDialog("Enter the word you would like to use: ");
        theGame(userWord);
    }
    
    public static void menu() {
        System.out.println("Hello! Welcome to hangman! Would you like to:");
        System.out.println("1. Play a game from a randomly chosen word in a list");
        System.out.println("2. Play game from a word entered by another user");
        System.out.println("3. Exit Game");
        String menuOption = JOptionPane.showInputDialog("Enter an option number: ");
        int option = Integer.parseInt(menuOption);
        switch (option) {
            case 1: 
                randomWordGame();
                menu();
                break;
            case 2:
                inputWordGame();
                menu();
                break;
            case 3:
                System.out.println("Thank you for playing!");
                System.exit(0);
                break;
            default:
                System.out.println("Please enter a valid option!");
                menu();
                break;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();
    }    
}
