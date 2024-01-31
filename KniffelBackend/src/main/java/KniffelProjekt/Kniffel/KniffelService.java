package KniffelProjekt.Kniffel;

import KniffelProjekt.Spieler.Spieler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class KniffelService {

    private ArrayList<Kniffel> spielliste = new ArrayList<>();

    public Kniffel spielStarten(ArrayList<Spieler> teilnehmer) {
        Kniffel neuesKniffel = new Kniffel();
        neuesKniffel.setId(System.currentTimeMillis());
        neuesKniffel.setTeilnehmer(teilnehmer);
        spielliste.add(neuesKniffel);
        return neuesKniffel;
    }

    public Kniffel getKniffel(Long kinffelId) {
        return spielliste.stream().filter(kniffel -> kniffel.getId().equals(kinffelId)).findFirst().get();
    }
}
