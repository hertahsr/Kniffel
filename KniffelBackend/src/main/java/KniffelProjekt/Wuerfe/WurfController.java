package KniffelProjekt.Wuerfe;

import KniffelProjekt.Kniffel.Kniffel;
import KniffelProjekt.Kniffel.KniffelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WurfController {

private KniffelService kniffelService;

    public List<Integer> wuerfeln(Long kniffelID){
        Kniffel kniffel= kniffelService.findeKniffel(kniffelID);
        Set<Integer> freieWuerfel=kniffel.getFreieWuerfel();
        Wurf wurf= new Wurf(freieWuerfel);
        return wurf.getErgebnis();

    }

}
