package KniffelProjekt.Wuerfe;

import KniffelProjekt.Kniffel.Kniffel;
import KniffelProjekt.Kniffel.KniffelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/wuerfe")
public class WurfController {

    @Autowired
    private KniffelService kniffelService;

    @PostMapping()
    public Kniffel wuerfeln(@RequestBody Long kniffelID) {
        Kniffel kniffel = kniffelService.findeKniffel(kniffelID);
        if (kniffel.getUebrigeWuerfe() != 0) {
            Set<Integer> freieWuerfel = kniffel.getFreieWuerfel();
            Wurf wurf = new Wurf(freieWuerfel);
            List<Integer> wurfErgebnis = wurf.getErgebnis();

            // uebrigeWuerfe in Kniffel-Klasse heruntersetzen
            kniffel.setUebrigeWuerfe(kniffel.getUebrigeWuerfe() - 1);

            // neuen Wurf an Kniffel-Klasse uebergeben
            List<Integer> neueWuerfel = kniffel.getWuerfel();
            int indexWurfErgebnis = 0;
            for (Integer FW : freieWuerfel) {
                neueWuerfel.set(FW - 1, wurfErgebnis.get(indexWurfErgebnis));
                indexWurfErgebnis++;
            }
            kniffel.setWuerfel(neueWuerfel);
        }
        return kniffel;
    }

}
