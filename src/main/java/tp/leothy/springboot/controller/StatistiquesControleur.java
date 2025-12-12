package tp.leothy.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tp.leothy.springboot.service.ServiceStatistiques;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistiques")
public class StatistiquesControleur {

    private final ServiceStatistiques serviceStatistiques;

    public StatistiquesControleur(ServiceStatistiques serviceStatistiques) {
        this.serviceStatistiques = serviceStatistiques;
    }

    @GetMapping("/livres_par_categorie")
    public Map<String, Long> livresParCategorie() {
        return serviceStatistiques.compterLivresParCategorie();
    }

    @GetMapping("/top-auteurs")
    public List<Map<String, Object>> topAuteurs(
            @RequestParam(name = "limite", defaultValue = "3") int limite) {
        return serviceStatistiques.topAuteurs(limite);
    }
}
