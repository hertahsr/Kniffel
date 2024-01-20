package KniffelProjekt.Block;

public class Block {



    private int nurEinsen=0;
    private int nurZweier=0;

    private int nurDreier=0;
    private int nurVierer=0;
    private int nurFunfer=0;
    private int nurSechser=0;
    //>63->=35; set nur in der Methode furt die Summe
    private int bonus=0;


    public int getNurEinsen() {
        return nurEinsen;
    }

    public void setNurEinsen(int nrWmit1) {
        this.nurEinsen = 1*nrWmit1;
    }

    public int getNurZweier() {
        return nurZweier;
    }

    public void setNurZweier(int nrWmit2) {
        this.nurZweier = 2*nrWmit2;
    }

    public int getNurDreier() {
        return nurDreier;
    }

    public void setNurDreier(int nrWmit3) {
        this.nurDreier = 3*nrWmit3;
    }

    public int getNurVierer() {
        return nurVierer;
    }

    public void setNurVierer(int nrWmit4) {
        this.nurVierer = 4*nrWmit4;
    }

    public int getNurFunfer() {
        return nurFunfer;
    }

    public void setNurFunfer(int nrWmit5) {
        this.nurFunfer = 5*nrWmit5;
    }

    public int getNurSechser() {
        return nurSechser;
    }

    public void setNurSechser(int nrWmit6) {
        this.nurSechser = 6*nrWmit6;
    }

    //kein setter- in der SUM Methode
    public int getBonus() {
        return bonus;
    }



    private int dreierP=0;
    private int viererP=0;
    private int full=0;
    private int kleineS=0;
    private int grosseS=0;
    private int kniffel=0;
    private int chance=0;

    public int getDreierP() {
        return dreierP;
    }

    public void setDreierP(int dreierP) {
        this.dreierP = dreierP;
    }

    public int getViererP() {
        return viererP;
    }

    public void setViererP(int viererP) {
        this.viererP = viererP;
    }

    public int getFull() {
        return full;
    }

    public void setFull() {
        this.full = 25;
    }

    public int getKleineS() {
        return kleineS;
    }

    public void setKleineS() {
        this.kleineS = 30;
    }

    public int getGrosseS() {
        return grosseS;
    }

    public void setGrosseS() {
        this.grosseS = 40;
    }

    public int getKniffel() {
        return kniffel;
    }

    public void setKniffel() {
        this.kniffel = 50;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }


    public Block() {

    }

    public int gesamtOTeil()
    { int sum=0;

        sum=nurEinsen+nurZweier+nurDreier+
                nurVierer+nurFunfer+nurSechser;
        if(sum>=63)
        {
            chance=35;
            sum+=35;
        }

        return sum;}
    public int gesamtUTeil()
    { int sum=0;
        sum=dreierP+viererP+full+kleineS+
                grosseS+kniffel+chance;
        return sum;}

    public int endSumme()
    {
        return gesamtOTeil()+gesamtUTeil();
    }
}
