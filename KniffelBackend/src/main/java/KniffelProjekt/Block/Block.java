package KniffelProjekt.Block;

import lombok.Getter;
import lombok.Setter;

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
