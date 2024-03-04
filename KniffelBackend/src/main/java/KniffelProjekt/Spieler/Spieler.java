package KniffelProjekt.Spieler;

import KniffelProjekt.Block.Block;
import KniffelProjekt.Score.Score;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Spieler {
    private String name;
    private Block block = new Block();
    private int siege = 0;
    private int punktestand = 0;
    private Long spielerId;
    private Score score;
}
