package KniffelProjekt.Spieler;

import KniffelProjekt.Kniffel.Kniffel;
import KniffelProjekt.Kniffel.KniffelService;
import KniffelProjekt.Score.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Spieler spieler = kniffelService.findeKniffel(kniffelId)
                .getTeilnehmer().stream().filter(teilnehmer -> teilnehmer.getSpielerId()
                        .equals(spielerId)).findFirst().get();
        spieler.setName(neuerName);
        return spieler;
    }



    public int score(Long kniffelId,String kategorie){
        Kniffel kniffel= kniffelService.findeKniffel(kniffelId);
        List<Integer> wuerfel=kniffel.getWuerfel();
        Score score=new Score(kategorie,wuerfel);
        return score.getScore();
    }


    private void scoreEintragen(Long kniffelId,String kategorie,List<Integer> wuerfel){
        Kniffel kniffel= kniffelService.findeKniffel(kniffelId);
        Score score=new Score(kategorie,wuerfel);
        int scoreRes=score.getScore();
        kniffel.getAktiverSpieler().getBlock().setKat(kategorie,scoreRes);
    }

}

