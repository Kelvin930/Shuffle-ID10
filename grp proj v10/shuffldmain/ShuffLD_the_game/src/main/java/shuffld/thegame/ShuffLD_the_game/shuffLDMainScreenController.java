package shuffld.thegame.ShuffLD_the_game;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class shuffLDMainScreenController {
    @RequestMapping(value = "")
    public String ShuffLDStart() {
        return "mainGame.html";
                                          
    }


}
