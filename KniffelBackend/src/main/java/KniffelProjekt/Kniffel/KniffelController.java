package KniffelProjekt.Kniffel;

import KniffelProjekt.Spieler.Spieler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
}
