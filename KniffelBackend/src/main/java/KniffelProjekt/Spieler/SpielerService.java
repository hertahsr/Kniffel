package KniffelProjekt.Spieler;

import KniffelProjekt.Kniffel.Kniffel;
import KniffelProjekt.Kniffel.KniffelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SpielerService {

    @Autowired
    private KniffelService kniffelService;

    public Spieler neuerSpieler(String name) {
        Spieler neuerSpieler = new Spieler();
        neuerSpieler.setName(name);
        neuerSpieler.setSpielerId(System.currentTimeMillis());
        return neuerSpieler;
    }

    public Spieler namenAendern(Long kniffelId, Long spielerId, String neuerName) {
        Spieler spieler = kniffelService.getKniffel(kniffelId)
                .getTeilnehmer().stream().filter(teilnehmer -> teilnehmer.getSpielerId()
                        .equals(spielerId)).findFirst().get();
        spieler.setName(neuerName);
        return spieler;
    }
}

