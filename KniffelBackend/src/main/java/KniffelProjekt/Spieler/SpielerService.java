package KniffelProjekt.Spieler;

import KniffelProjekt.Kniffel.Kniffel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SpielerService {

    private ArrayList<Kniffel> spielliste = new ArrayList<>();

    public Spieler neuerSpieler(String name) {
        Spieler neuerSpieler = new Spieler();
        neuerSpieler.setName(name);
        neuerSpieler.setSpielerId(System.currentTimeMillis());
        return neuerSpieler;
    }

    public Spieler namenAendern(Long kniffelId,Long spielerId, String neueNamen) {
        Spieler spieler=spielliste.stream().filter(kniffel -> kniffel.getId().equals(kniffelId)).findFirst().get().getTeilnehmer().stream().filter(teilnehmer -> teilnehmer.getSpielerId().equals(spielerId)).findFirst().get();
        spieler.setName(neueNamen);
        return spieler;
    }
}

