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
    private final int maxWuerfe = 3;
    private final Set<Integer> alleWuerfel = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
    private final Integer bonusGrenze = 63;
    private final Integer bonusScore = 35;
    private final int anzahlRunden = 13;

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
            if (runde >= anzahlRunden) {
                // Sieger feststellen Spiel beenden
                return;
            }
            aktiverSpielerIndex = 0;
        }
        uebrigeWuerfe = getMaxWuerfe();
        freieWuerfel = new HashSet<>(alleWuerfel);  //alle Wuerfel unfixieren beim Spielerwechsel

    }

    public void auswertung() {

        for (Spieler spieler : teilnehmer) {
            Block block = spieler.getBlock();

            // Berechnung oberer Teil
            Integer punkteObererTeil = getPunkteObererTeil(block);
            block.setPunkteUntererTeil(punkteObererTeil);

            // Berechnung Bonus
            if (punkteObererTeil >= bonusGrenze) {
                block.setBonus(bonusScore);
            } else {
                block.setBonus(0);
            }

            // Berechnung oberer Teil mit Bonus
            Integer punkteOTMitBonus = punkteObererTeil + block.getBonus();
            block.setPunkteObererTeil(punkteOTMitBonus);

            // Berechnung unterer Teil
            Integer punkteUntererTeil = getPunkteUntererTeil(block);
            block.setPunkteUntererTeil(punkteUntererTeil);

            // Berechnung Gesamtpunkte
            Integer gesamtPunkte = block.getPunkteObererTeil() + block.getPunkteUntererTeil();
            block.setGesamtPunkte(gesamtPunkte);
        }
    }
    private static Integer getPunkteObererTeil(Block block) {
        List<Integer> obererTeil =
                new ArrayList<>(Arrays.asList(block.getNurEinser(), block.getNurZweier(), block.getNurDreier(),
                        block.getNurVierer(), block.getNurFuenfer(), block.getNurSechser()));
        Integer punkteObererTeil = 0;
        for (Integer eintrag : obererTeil) {
            if (eintrag != null) {
                punkteObererTeil += eintrag;
            }
        }
        return punkteObererTeil;
    }

    private static Integer getPunkteUntererTeil(Block block) {
        List<Integer> untererTeil =
                new ArrayList<>(Arrays.asList(block.getDreierPasch(), block.getViererPasch(), block.getFullHouse(),
                        block.getKleineStrasse(), block.getGrosseStrasse(), block.getKniffel(), block.getChance()));
        Integer punkteUntererTeil = 0;
        for (Integer eintrag : untererTeil) {
            if (eintrag != null) {
                punkteUntererTeil += eintrag;
            }
        }
        return punkteUntererTeil;
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
