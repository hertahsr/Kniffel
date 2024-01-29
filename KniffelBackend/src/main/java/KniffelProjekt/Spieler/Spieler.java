package KniffelProjekt.Spieler;

import KniffelProjekt.Block.Block;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Spieler {
    private String name;
    private Block block = new Block();
    private int siege;
    private int punktestand;
}
