package shuffld.thegame.ShuffLD_the_game;
import java.util.*;

import shuffld.ShuffLDModel;

class Word_randomiser


{

    static private Random rand = new Random();
    //Makes a new random object for use later. This will be used to set the Seed of the words

    public static void shuffle(char[][] char2D_words,int i){

        for(int a=0; a<5;a++) {
            int i1 = (int) (Math.random() * char2D_words.length);
            // temporary store  words in char
            char temp_words = char2D_words[a][i];
            char2D_words[a][i] = char2D_words[i1][i];
            char2D_words[i1][i] = temp_words;
        }
    }

    // We want to return the shuffled array for the controller to take use of the 2d array
    //Instead of just printing out the shuffled Array
    public static char[][] getShuffledWords() {
        String[] daily_words = ShuffLDModel.getWords();
        rand.setSeed(Arrays.hashCode(daily_words));
        //Makes sure the same words are used until a new day arrives.
        //Words will be shuffled the same way. 
        //Reloading the site will not effect changing the values, the only time the values will change is when the timer restarts daily
        //A 2d-array that store  the length of daily words length
        char[][] char2D_words = new char[daily_words.length][];
        // covert an array into 2d char array
        for (int i = 0; i < daily_words.length; i++) {
            char2D_words[i] = daily_words[i].toCharArray();
        }
        
        for (int i = 0; i < char2D_words.length; i++) {
            shuffle(char2D_words, i);
        }
        return char2D_words;
    }


  




    public static void main(String args[])

    {
        ShuffLDModel.getWords();
        
       
        
// An array to store daily words
        String[] daily_words = ShuffLDModel.getWords();
//A 2d-array that store  the length of daily words length
        char[][] char2D_words = new char[daily_words.length][];
// covert an array into 2d char array
        for (int i = 0; i < daily_words.length; i++) {
            char2D_words[i] = daily_words[i].toCharArray();
        }


        for (char[] char1D_words : char2D_words) {
            for (char chr : char1D_words)
                System.out.print(chr + " ");
            System.out.println();
        }
        System.out.println("                 ") ;
        System.out.print(char2D_words.length);
        System.out.println("                 ") ;

        for(int i=0; i<char2D_words.length;i++) {
            shuffle(char2D_words,i);
        }

        // print shuffled 2d array
        for(int i =0;i<char2D_words.length;i++){
            for(int j =0;j<char2D_words[i].length;j++){
                System.out.print(char2D_words[i][j]  + " ");
            }
            System.out.println();
          
        }
        


    }












}

