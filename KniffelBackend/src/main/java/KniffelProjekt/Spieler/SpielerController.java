package KniffelProjekt.Spieler;

import KniffelProjekt.Kniffel.Kniffel;
import KniffelProjekt.Kniffel.KniffelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SpielerController {
    
    private SpielerService spielerService;

    @PostMapping("/spieler")
    public Spieler postSpieler(@RequestBody String name) {
        return spielerService.neuerSpieler(name);
    }

    @PutMapping("/kniffel/{kniffelId}/spieler/{spielerId}/name")
    public Kniffel namenAendern(@PathVariable Long kniffelId, @PathVariable Long spielerId, @RequestBody String neuerName) {
        return spielerService.namenAendern(kniffelId, spielerId, neuerName);
    }
}
