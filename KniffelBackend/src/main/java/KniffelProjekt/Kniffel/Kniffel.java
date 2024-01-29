package KniffelProjekt.Kniffel;

import KniffelProjekt.Spieler.Spieler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Kniffel {

    private List<Spieler> teilnehmer;

    private Spieler aktiverSpieler;

    private int runde = 0;

    private int uebrigeWuerfe = 3;

    private List<Integer> aktiveWuerfel= new ArrayList<Integer>(5);

    private List<Integer> fixeWuerfel = new ArrayList<Integer>();

    // Konstruktor
    public Kniffel(List<Spieler> teilnehmer) {
        this.teilnehmer = teilnehmer;
        this.aktiverSpieler = teilnehmer.get(0);
    }

    public List<Integer> wuerfeln() {
        List<Integer> retval = new ArrayList<Integer>();
        for (int i = 0; i < aktiveWuerfel.size(); i++) {
            int zahl = ThreadLocalRandom.current().nextInt(1, 7);
            retval.set(i, zahl);
        }
        return retval;
    }


    // Getter & Setter
    public List<Spieler> getTeilnehmer() {
        return teilnehmer;
    }

    public void setTeilnehmer(List<Spieler> teilnehmer) {
        this.teilnehmer = teilnehmer;
    }

    public Spieler getAktiverSpieler() {
        return aktiverSpieler;
    }

    public void setAktiverSpieler(Spieler aktiverSpieler) {
        this.aktiverSpieler = aktiverSpieler;
    }

    public int getRunde() {
        return runde;
    }

    public void setRunde(int runde) {
        this.runde = runde;
    }

    public int getUebrigeWuerfe() {
        return uebrigeWuerfe;
    }

    public void setUebrigeWuerfe(int uebrigeWuerfe) {
        this.uebrigeWuerfe = uebrigeWuerfe;
    }

    public List<Integer> getAktiveWuerfel() {
        return aktiveWuerfel;
    }

    public void setAktiveWuerfel(List<Integer> aktiveWuerfel) {
        this.aktiveWuerfel = aktiveWuerfel;
    }

    public List<Integer> getFixeWuerfel() {
        return fixeWuerfel;
    }

    public void setFixeWuerfel(List<Integer> fixeWuerfel) {
        this.fixeWuerfel = fixeWuerfel;
    }
}
