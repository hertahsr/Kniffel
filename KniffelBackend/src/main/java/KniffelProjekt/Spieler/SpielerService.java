package KniffelProjekt.Spieler;

import org.springframework.stereotype.Service;

@Service
public class SpielerService {

    public Spieler neuerSpieler(String name) {
        Spieler neuerSpieler = new Spieler();
        neuerSpieler.setName(name);
        neuerSpieler.setSpielerId(System.currentTimeMillis());
        return neuerSpieler;
    }
}

