package KniffelProjekt.Kniffel;

import KniffelProjekt.Spieler.Spieler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/kniffel")
public class KniffelController {

    @Autowired
    private KniffelService kniffelService;

    @PostMapping("/neuesSpielStarten")
    public Kniffel getNeuesSpiel(@RequestBody ArrayList<Spieler> teilnehmer) {
        return kniffelService.spielStarten(teilnehmer);
    }
}
