package KniffelProjekt.Kniffel;

import KniffelProjekt.Spieler.Spieler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/kniffel")
@AllArgsConstructor
public class KniffelController {

    private KniffelService kniffelService;

    @PostMapping("/neuesSpielStarten")
    public Kniffel postNeuesSpiel(@RequestBody ArrayList<Spieler> teilnehmer) {
        return kniffelService.spielStarten(teilnehmer);
    }

    @PutMapping("/{kniffelId}/wuerfel")
    public Kniffel putWuerfel(@PathVariable Long kniffelId, @RequestBody int wuerfel) {
        Kniffel kniffel = kniffelService.findeKniffel(kniffelId);
        if (kniffel.getUebrigeWuerfe() < kniffel.getMaxWuerfe()) {
            kniffel.wuerfelStatusAendern(wuerfel);
        }
        return kniffel;
    }
}
