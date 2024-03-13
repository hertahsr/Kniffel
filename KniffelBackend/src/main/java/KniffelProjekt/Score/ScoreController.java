package KniffelProjekt.Score;

import KniffelProjekt.Kniffel.Kniffel;
import KniffelProjekt.Kniffel.KniffelService;
import KniffelProjekt.Spieler.Spieler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScoreController {

    private KniffelService kniffelService;

    @PostMapping
    public int score(String kategorie,List<Integer> wuerfel,Long kniffelId)
    {
        Score score=new Score(kategorie,wuerfel);
        scoreEintragen(kategorie, score.getScore(), kniffelId);
        return score.getScore();
    }

    private void scoreEintragen(String kategorie, int score, Long kniffelId) {
        Kniffel kniffel= kniffelService.findeKniffel(kniffelId);
        kniffel.getAktiverSpieler().getBlock().setKat(kategorie,score);
    }

}
