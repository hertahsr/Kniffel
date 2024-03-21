package KniffelProjekt.Score;

import KniffelProjekt.Kniffel.Kniffel;
import KniffelProjekt.Kniffel.KniffelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ScoreController {

    private final KniffelService kniffelService;

    @PostMapping("/kniffel/{kniffelId}/scores")
    public Kniffel score(@RequestBody Object kategorie, @PathVariable Long kniffelId) {
        Kniffel kniffel = kniffelService.findeKniffel(kniffelId);
        if (kniffel.getUebrigeWuerfe() < kniffel.getMaxWuerfe()) {
            Score score = new Score((String) kategorie, kniffel.getWuerfel());
            scoreEintragen((String) kategorie, score.getScore(), kniffelId);
        }
        return kniffel;
    }

    @GetMapping("/kniffel/{kniffelId}/scores/{kategorie}")
    private int validateScore(@PathVariable Object kategorie, @PathVariable Long kniffelId) {
        Kniffel kniffel = kniffelService.findeKniffel(kniffelId);
        if (kniffel.getUebrigeWuerfe() < kniffel.getMaxWuerfe()) {
            Score score = new Score((String) kategorie, kniffel.getWuerfel());
            return score.getScore();
        } else throw new RuntimeException("Noch nicht gewürfelt.");
    }

    private void scoreEintragen(String kategorie, int score, Long kniffelId) {
        Kniffel kniffel = kniffelService.findeKniffel(kniffelId);
        kniffel.getAktiverSpieler().getBlock().setKat(kategorie, score);
        kniffel.auswertung();
        kniffel.naechsterSpieler();
    }

}
