package KniffelProjekt.Kniffel;

import KniffelProjekt.Spieler.Spieler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Kniffel {
    private Long id;
    private List<Spieler> teilnehmer;
    private int aktiverSpielerIndex = 0;
    private int runde = 0;
    private int uebrigeWuerfe = 3;
    private List<Integer> wuerfel = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1));
    private Set<Integer> freieWuerfel =new HashSet<>();


    // Konstruktor
//    public Kniffel(List<Spieler> teilnehmer) {
//        this.teilnehmer = teilnehmer;
//        this.aktiverSpieler = teilnehmer.get(0);
//    }

//    public List<Integer> wuerfeln() {
//        List<Integer> retval = new ArrayList<Integer>();
//        for (int i = 0; i < wuerfel.size(); i++) {
//            int zahl = ThreadLocalRandom.current().nextInt(1, 7);
//            retval.set(i, zahl);
//        }
//
//        return retval;
//    }
}
