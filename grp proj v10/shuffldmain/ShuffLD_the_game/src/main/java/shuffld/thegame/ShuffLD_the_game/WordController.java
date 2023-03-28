package shuffld.thegame.ShuffLD_the_game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import shuffld.ShuffLDModel;

@RestController 
public class WordController {
    // setting a 2D array that holds the suffled array from the word randomiser
    @GetMapping(value = { "/words" })
    public static char[][] getShuffledWords() {
        return Word_randomiser.getShuffledWords();
    }
    @GetMapping(value = { "/solvedWords"})
    public String[] solvedWords()
    {
        return ShuffLDModel.getWords();
    }
    
    @GetMapping (value = { "/swaps"})
    public static int swaps()
    {
        Digger dig = new Digger();
        return dig.solve();
    }

}
