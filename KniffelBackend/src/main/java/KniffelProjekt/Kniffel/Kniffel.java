package KniffelProjekt.Kniffel;

import KniffelProjekt.Block.Block;
import KniffelProjekt.Spieler.Spieler;
import KniffelProjekt.Wuerfe.Wurf;
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
    private final int maxWuerfe = 3;
    private final Set<Integer> alleWuerfel = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));

    private Long id;
    private List<Spieler> teilnehmer;
    private int aktiverSpielerIndex = 0;
    private int runde = 0;
    private int uebrigeWuerfe = maxWuerfe;
    private List<Integer> wuerfel = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1));
    private Set<Integer> freieWuerfel = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));

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
        uebrigeWuerfe = getMaxWuerfe();
        freieWuerfel = new HashSet<>(alleWuerfel);//alle Wuerfel unfixieren beim Spielerwaechsel

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

    public Spieler getAktiverSpieler() {
        return teilnehmer.get(aktiverSpielerIndex);
    }

    /*public void wuerfelnMitFixWuerfeln(HashSet<Integer> freieWuerfel) {
        Wurf wurf = new Wurf(freieWuerfel);
        int i = 0;
        for (Integer fW : freieWuerfel) {
            wuerfel.set(fW, wurf.getErgebnis().get(i));
            i++;
        }
    }
*/
    public void wuerfelStatusAendern(int wuerfel) {
        if (freieWuerfel.contains(wuerfel)) {
            wuerfelFixieren(wuerfel);
        } else {
            wuerfelUnFixieren(wuerfel);
        }
    }

    private void wuerfelFixieren(int fixierteWuerfel) {
        freieWuerfel.removeIf(w -> w == fixierteWuerfel);
    }

    private void wuerfelUnFixieren(int unfixierteWuerfel) {
        freieWuerfel.add(unfixierteWuerfel);
    }

}
