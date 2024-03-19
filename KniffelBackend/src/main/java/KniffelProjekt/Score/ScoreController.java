package KniffelProjekt.Score;

import KniffelProjekt.Kniffel.Kniffel;
import KniffelProjekt.Kniffel.KniffelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ScoreController {

    private final KniffelService kniffelService;

    @PostMapping("/kniffel/{kniffelId}")
    public Kniffel score(@RequestBody Object kategorie, @PathVariable Long kniffelId) {
        Kniffel kniffel = kniffelService.findeKniffel(kniffelId);
        Score score = new Score((String) kategorie, kniffel.getWuerfel());
        scoreEintragen((String) kategorie, score.getScore(), kniffelId);
        return kniffel;
    }

    private int validateScore (@RequestBody Object kategorie, @PathVariable Long kniffelId) {
        Kniffel kniffel = kniffelService.findeKniffel(kniffelId);
        Score score = new Score((String)kategorie, kniffel.getWuerfel());
        return score.getScore();
    }

    private void scoreEintragen(String kategorie, int score, Long kniffelId) {
        Kniffel kniffel = kniffelService.findeKniffel(kniffelId);
        //kein Eintragen bevor der Spieler gewuerfelt hat
        if (kniffel.getUebrigeWuerfe() >= kniffel.getMaxWuerfe()) {
            kniffel.getAktiverSpieler().getBlock().setKat(kategorie, score);
            kniffel.auswertung();
            kniffel.naechsterSpieler();
        }
    }

}
