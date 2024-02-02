package KniffelProjekt.Spieler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpielerController {

    @Autowired
    private SpielerService spielerService;

    @PostMapping("/spieler")
    public Spieler postSpieler(@RequestBody String name) {
        return spielerService.neuerSpieler(name);
    }

    @PutMapping("/kniffel/{kniffelId}/spieler/{spielerId}/name")
    public Spieler namenAendern(@PathVariable Long kniffelId, @PathVariable Long spielerId, @RequestBody String neuerName) {
        return spielerService.namenAendern(kniffelId, spielerId, neuerName);
    }
}
