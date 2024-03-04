package KniffelProjekt.Score;

import KniffelProjekt.Kniffel.Kniffel;
import KniffelProjekt.Kniffel.KniffelService;
import KniffelProjekt.Spieler.Spieler;
import KniffelProjekt.Spieler.SpielerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScoreController {

    @PostMapping
    public int score(String kategorie,List<Integer> wuerfel)
    {
        Score score=new Score(kategorie,wuerfel);
        return score.getScore();
    }



}
