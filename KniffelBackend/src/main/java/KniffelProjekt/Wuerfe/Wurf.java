package KniffelProjekt.Wuerfe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;


@Getter
public class Wurf {

   public  List<Integer> ergebnis;
    public Wurf(Set<Integer> freieWuerfel)
    {
        Random rand= new Random();
        ergebnis= rand.ints(freieWuerfel.size(), 1,6).boxed().toList();

    }

}
