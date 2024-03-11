package KniffelProjekt.Kniffel;

import KniffelProjekt.Block.Block;
import KniffelProjekt.Spieler.Spieler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

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

    public void naechsterSpieler() {

        if (aktiverSpielerIndex < teilnehmer.size() - 1) {
            aktiverSpielerIndex++;
        } else {
            runde++;
            if (runde >= 13) {
                // Auswertung nur am Ende, da sonst letzte Spieler im Vorteil
                auswertung();
                return;
            }
            aktiverSpielerIndex = 0;
        }
        uebrigeWuerfe = 3;
    }

    public void auswertung() {

        for (Spieler spieler : teilnehmer) {
            Block block = spieler.getBlock();

            // Berechnung oberer Teil
            Integer punkteObererTeil = block.getNurEinser() + block.getNurZweier() + block.getNurDreier() +
                    block.getNurVierer() + block.getNurFuenfer() + block.getNurSechser();
            if (punkteObererTeil >= 63) {
                block.setBonus(35);
            }
            Integer punkteOTMitBonus = punkteObererTeil + block.getBonus();
            block.setPunkteObererTeil(punkteOTMitBonus);

            // Berechnung unterer Teil
            Integer punkteUntererTeil = block.getDreierPasch() + block.getViererPasch() + block.getFullHouse() +
                    block.getKleineStrasse() + block.getGrosseStrasse() + block.getKniffel() + block.getChance();
            block.setPunkteUntererTeil(punkteUntererTeil);

            // Gesamtpunkte
            Integer gesamtPunkte = block.getPunkteObererTeil() + block.getPunkteUntererTeil();
            block.setGesamtPunkte(gesamtPunkte);
        }
    }
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


    public Spieler getAktiverSpieler()
    {
        return teilnehmer.get(aktiverSpielerIndex);
    }
}
