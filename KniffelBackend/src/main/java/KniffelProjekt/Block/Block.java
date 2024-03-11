package KniffelProjekt.Block;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class Block {
    private Integer nurEinser;
    private Integer nurZweier;
    private Integer nurDreier;
    private Integer nurVierer;
    private Integer nurFuenfer;
    private Integer nurSechser;
    private Integer bonus;
    private Integer punkteObererTeil;
    private Integer dreierPasch;
    private Integer viererPasch;
    private Integer fullHouse;
    private Integer kleineStrasse;
    private Integer grosseStrasse;
    private Integer kniffel;
    private Integer chance;
    private Integer punkteUntererTeil;
    private Integer gesamtPunkte;

    public void setKat(String kategorie, int scoreRes) {

        switch(kategorie) {
            case "NUREINSER"-> {
                nurEinser = scoreRes;
                if(oberSum()>62)
                {bonus=35;}
            }

            case "NURZWEIER"-> {
                nurZweier = scoreRes;
                if(oberSum()>62)
                {bonus=35;}
            }

            case "NURDREIER"-> {
                nurDreier = scoreRes;
                if(oberSum()>62)
                {bonus=35;}
            }

            case "NURVIERER"-> {
                nurVierer = scoreRes;
                if(oberSum()>62)
                {bonus=35;}
            }

            case "NURFUENFER"-> {
                nurFuenfer = scoreRes;
                if(oberSum()>62)
                {bonus=35;}
            }

            case "NURSECHSER"-> {
                nurSechser = scoreRes;
                if(oberSum()>62)
                {bonus=35;}
            }

           // case "BONUS"->bonus; //Logik hier


            case "DREIERPASCH" -> dreierPasch=scoreRes;

            case "VIERERPASCH"-> viererPasch=scoreRes;
            case "FULLHOUSE"->fullHouse=scoreRes;

            case "KLEINESTRASSE"->kleineStrasse=scoreRes;

            case "GROSSESTRASSE"->grosseStrasse=scoreRes;

            case "KNIFFEL"->kniffel=scoreRes;

            case "CHANCE"->chance=scoreRes;


            default -> throw new IllegalStateException("Unexpected value: " + kategorie);
        };
    }

    private int oberSum() {
        return nurEinser+ nurZweier+ nurDreier+ nurVierer+ nurFuenfer+ nurSechser;
    }

//    public int gesamtOTeil()
//    { int sum=0;
//
//        sum=nurEinsen+nurZweier+nurDreier+
//                nurVierer+nurFunfer+nurSechser;
//        if(sum>=63)
//        {
//            chance=35;
//            sum+=35;
//        }
//
//        return sum;}
//    public int gesamtUTeil()
//    { int sum=0;
//        sum=dreierP+viererP+full+kleineS+
//                grosseS+kniffel+chance;
//        return sum;}


}
