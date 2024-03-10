package KniffelProjekt.Score;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Score {
    private int score=0;

    private final int nurEinserScore=1;
    private final int nurZweierScore=2;
    private final int nurDreierScore=3;
    private final int nurViererScore=4;
    private final int nurFuenferScore=5;
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
            case "NUREINSER"->nurEinserScore*(int)wuerfel.stream()
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

            case "NURFUENFER"->nurFuenferScore*(int)wuerfel.stream()
                    .filter(e->e==5)
                    .count();

            case "NURSECHSER"->nurSechserScore*(int)wuerfel.stream()
                    .filter(e->e==6)
                    .count();

            case "BONUS"->bonusScore;

            //needs review
            case "DREIERPASCH" -> {int res=0;
                if(wuerfel.stream()
                        .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                        .values()
                        .stream()
                        .anyMatch(count -> count >= 3))
                {res=dreierPaschScore*wuerfel.stream()
                        .mapToInt(Integer::intValue)
                        .sum();}
                yield res;
            }

            case "VIERERPASCH"-> {int res=0;
                if(wuerfel.stream()
                        .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                        .values()
                        .stream()
                        .anyMatch(count -> count >= 4))
                {res=viererPaschScore * wuerfel.stream()
                        .mapToInt(Integer::intValue)
                        .sum();}
                yield res;
            }
            case "FULLHOUSE"->{
                int res=0;
                Map<Integer, Long> frequencyMap = wuerfel.stream()
                        .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

                boolean hasSetOf3 = false;
                boolean hasSetOf2 = false;
//                boolean flag=false;

                for (Map.Entry<Integer, Long> entry : frequencyMap.entrySet()) {
                    long count = entry.getValue();
                    if (count == 3) {
                        hasSetOf3 = true;
                    } else if (count == 2) {
                        hasSetOf2 = true;
                        }
                }
                if( hasSetOf3 && hasSetOf2)
                    res=fullHouseScore;
            yield res;
            }

            case "KLEINESTRASSE"->{
                int res=0;
                if(wuerfel.stream().distinct().count() >= 4) {
                    List<Integer> wuerfelC = new ArrayList<>(wuerfel);
                    Collections.sort(wuerfelC);
                    String s = wuerfelC.stream().distinct()
                            .map(String::valueOf)
                            .collect(Collectors.joining());
                    if (s.contains("1234") ||
                            s.contains("2345") ||
                            s.contains("3456"))
                        res = kleineStrasseScore;
                }
                yield res;}

            case "GROSSESTRASSE"->{
                int res=0;
                if(wuerfel.stream().distinct().count() >= 5) {
                    List<Integer> wuerfelC = new ArrayList<>(wuerfel);
                    Collections.sort(wuerfelC);
                    String s = wuerfelC.stream().distinct()
                            .map(String::valueOf)
                            .collect(Collectors.joining());
                    if (s.contains("12345") ||
                            s.contains("23456"))
                        res = grosseStrasseScore;
                }
                yield res;}

            case "KNIFFEL"->{
                int res=0;
                if(wuerfel.stream().distinct().count() == 1) {
                    res = kniffelScore;
                }
                yield res;}

            case "CHANCE"->chanceScore*wuerfel.stream()
                    .mapToInt(Integer::intValue)
                    .sum();


            default -> throw new IllegalStateException("Unexpected value: " + kategorie);
        };
        return score;
    }



}
