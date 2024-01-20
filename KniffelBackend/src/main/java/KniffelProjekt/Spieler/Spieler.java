package KniffelProjekt.Spieler;

import KniffelProjekt.Block.Block;

import java.util.Random;

public class Spieler {

    private String name;
    private Block block;
    private int siege;
    private int punktstand;//Punktestand der Session

    Random rand = new Random();

    //Geters&Setters
    public String getName() {//set only with the Constructor- no need for Setter
        return name;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {//may play several games, needed
        this.block = block;
    }

    public int getSiege() {
        return siege;
    }

    public void setSiege(int siege) {
        this.siege = siege;
    }

    public int getPunktstand() {
        return punktstand;
    }

    public void setPunktstand(int punktstand) {
        this.punktstand = punktstand;
    }

//Constructor

    //where should we set the nr victories from
    public Spieler(String name, int siege) {
        this.name = name;
        this.siege = siege;
    }

    /**
     * Der Spieler startet ein neues Spiel.
     * @param block ein genaues Spielblock wird zugeordnet, kein neues Objekt,
     *              damit auch andere Spieler am gleichen Spiel teilnehmen k"onnen.
     * */
    public void startSpiel(Block block)
    {
        setBlock(block);
        setPunktstand(0);
    }

    /**
     * Das W"urfeln von nur einem W"urfel.
     * Hilfsklasse, ohne Logik.
     * */
    public int wurf1W()
    {
        return  1+rand.nextInt(6);
    }
}
