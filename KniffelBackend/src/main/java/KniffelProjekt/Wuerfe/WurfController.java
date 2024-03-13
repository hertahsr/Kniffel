package KniffelProjekt.Wuerfe;

import KniffelProjekt.Kniffel.Kniffel;
import KniffelProjekt.Kniffel.KniffelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/wuerfe")
public class WurfController {

    @Autowired
    private KniffelService kniffelService;

    @PostMapping()
    public List<Integer> wuerfeln(@RequestBody Long kniffelID) {
        Kniffel kniffel = kniffelService.findeKniffel(kniffelID);
        Set<Integer> freieWuerfel = kniffel.getFreieWuerfel();
        Wurf wurf = new Wurf(freieWuerfel);
        return wurf.getErgebnis();

    }

}
