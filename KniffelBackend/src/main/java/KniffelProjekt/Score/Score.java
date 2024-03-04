package KniffelProjekt.Score;

import lombok.Getter;

import java.util.List;
@Getter
public class Score {
    private int score=0;

    private final int nurEinsenScore=1;
    private final int nurZweierScore=2;
    private final int nurDreierScore=3;
    private final int nurViererScore=4;
    private final int nurFunferScore=5;
    private final int nurSechserScore=6;
    private final int bonusScore=35;
    private final int dreierPaschScore=1;
    private final int viererPaschScore=1;
    private final int fullHouseScore =25;
    private final int kleineStrasseScore=30;
    private final int grosseStrasseScore=40;
    private final int kniffelScore=50;
    private final int chanceScore=1;
    //void Enum{

    //}

public Score(String kategorie, List<Integer> wuerfel)
{
    this.score=scoreBerechnen(kategorie, wuerfel);
}
    private int scoreBerechnen(String kategorie, List<Integer> wuerfel){

        int score= switch(kategorie) {
            case "NUREINSEN"->nurEinsenScore*(int)wuerfel.stream()
                    .filter(e->e==1)
                    .count();
                                //wenn Zeit-ENUM
            //wo kommt die Methode hin um den score einzutragen-
            // im Spieler score object und im richtigen feld methode
            // scoreEintragen(kategorie, wert)

            case "NURZWEIER"->nurZweierScore*(int)wuerfel.stream()
                    .filter(e->e==2)
                    .count();

            case "NURDREIER"->nurDreierScore*(int)wuerfel.stream()
                    .filter(e->e==3)
                    .count();

            case "NURVIERER"->nurViererScore*(int)wuerfel.stream()
                    .filter(e->e==4)
                    .count();

            case "NURFUNFER"->nurFunferScore*(int)wuerfel.stream()
                    .filter(e->e==5)
                    .count();

            case "NURSECHSER"->nurSechserScore*(int)wuerfel.stream()
                    .filter(e->e==6)
                    .count();

            case "BONUS"->bonusScore;

            case "DREIERPASCH"->dreierPaschScore*wuerfel.stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            case "VIERERPASCH"->viererPaschScore*wuerfel.stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            case "FULLHOUSE"->fullHouseScore;

            case "KLEINESTRASSE"->kleineStrasseScore;

            case "GROSSESTRASSE"->grosseStrasseScore;

            case "KNIFFEL"->kniffelScore;

            case "CHANCE"->chanceScore*wuerfel.stream()
                    .mapToInt(Integer::intValue)
                    .sum();


            default -> throw new IllegalStateException("Unexpected value: " + kategorie);
        };
        return score;
    }



}
