package KniffelProjekt.Spieler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpielerController {

    @Autowired
    private SpielerService spielerService;

    @PostMapping("/spieler")
    public Spieler postNeuerSpieler(@RequestBody String name) {
        return spielerService.neuerSpieler(name);
    }
}
