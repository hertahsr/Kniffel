package KniffelProjekt.Block;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

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

    private HashSet<String> einmalEintrag = new HashSet<>();

    public void setKat(String kategorie, int scoreRes) {

        switch (kategorie) {
            case "NUREINSER" -> {
                if (!einmalEintrag.contains("NUREINSER")) {
                    nurEinser = scoreRes;
                    einmalEintrag.add("NUREINSER");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            case "NURZWEIER" -> {
                if (!einmalEintrag.contains("NURZWEIER")) {
                    nurZweier = scoreRes;
                    einmalEintrag.add("NURZWEIER");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            case "NURDREIER" -> {
                if (!einmalEintrag.contains("NURDREIER")) {
                    nurDreier = scoreRes;
                    einmalEintrag.add("NURDREIER");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            case "NURVIERER" -> {
                if (!einmalEintrag.contains("NURVIERER")) {
                    nurVierer = scoreRes;
                    einmalEintrag.add("NURVIERER");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            case "NURFUENFER" -> {
                if (!einmalEintrag.contains("NURFUENFER")) {
                    nurFuenfer = scoreRes;
                    einmalEintrag.add("NURFUENFER");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            case "NURSECHSER" -> {
                if (!einmalEintrag.contains("NURSECHSER")) {
                    nurSechser = scoreRes;
                    einmalEintrag.add("NURSECHSER");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            case "DREIERPASCH" -> {
                if (!einmalEintrag.contains("DREIERPASCH")) {
                    dreierPasch = scoreRes;
                    einmalEintrag.add("DREIERPASCH");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            case "VIERERPASCH" -> {
                if (!einmalEintrag.contains("VIERERPASCH")) {
                    viererPasch = scoreRes;
                    einmalEintrag.add("VIERERPASCH");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            case "FULLHOUSE" -> {
                if (!einmalEintrag.contains("FULLHOUSE")) {
                    fullHouse = scoreRes;
                    einmalEintrag.add("FULLHOUSE");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            case "KLEINESTRASSE" -> {
                if (!einmalEintrag.contains("KLEINESTRASSE")) {
                    kleineStrasse = scoreRes;
                    einmalEintrag.add("KLEINESTRASSE");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            case "GROSSESTRASSE" -> {
                if (!einmalEintrag.contains("GROSSESTRASSE")) {
                    grosseStrasse = scoreRes;
                    einmalEintrag.add("GROSSESTRASSE");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            case "KNIFFEL" -> {
                if (!einmalEintrag.contains("KNIFFEL")) {
                    kniffel = scoreRes;
                    einmalEintrag.add("KNIFFEL");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            case "CHANCE" -> {
                if (!einmalEintrag.contains("CHANCE")) {
                    chance = scoreRes;
                    einmalEintrag.add("CHANCE");
                } else {
                    throw new IllegalStateException("Besetzte Kategorie: " + kategorie);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + kategorie);
        }
    }
}
