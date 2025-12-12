package tp.leothy.springboot.service.impl;

import org.springframework.stereotype.Service;
import tp.leothy.springboot.model.CategorieLivre;
import tp.leothy.springboot.model.Livre;
import tp.leothy.springboot.repository.AuteurRepository;
import tp.leothy.springboot.repository.LivreRepository;
import tp.leothy.springboot.service.ServiceStatistiques;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceStatistiquesImpl implements ServiceStatistiques {

    private final LivreRepository livreRepository;
    private final AuteurRepository auteurRepository;

    public ServiceStatistiquesImpl(LivreRepository livreRepository, AuteurRepository auteurRepository) {
        this.livreRepository = livreRepository;
        this.auteurRepository = auteurRepository;
    }

    @Override
    public Map<String, Long> compterLivresParCategorie() {
        List<Livre> livres = livreRepository.findAll();

        Map<CategorieLivre, Long> parCategorie = livres.stream()
                .collect(Collectors.groupingBy(Livre::getCategorie, Collectors.counting()));

        Map<String, Long> resultat = new LinkedHashMap<>();
        for (CategorieLivre categorie : CategorieLivre.values()) {
            resultat.put(categorie.name(), parCategorie.getOrDefault(categorie, 0L));
        }
        return resultat;
    }

    @Override
    public List<Map<String, Object>> topAuteurs(int limite) {
        List<Livre> livres = livreRepository.findAll();

        Map<Long, Long> compteur = livres.stream()
                .collect(Collectors.groupingBy(
                        livre -> livre.getAuteur().getIdentifiant(),
                        Collectors.counting()
                ));

        List<Map.Entry<Long, Long>> tries = compteur.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .limit(limite)
                .collect(Collectors.toList());

        List<Map<String, Object>> resultat = new ArrayList<>();
        for (Map.Entry<Long, Long> entree : tries) {
            Long idAuteur = entree.getKey();
            Long nbLivres = entree.getValue();
            auteurRepository.findById(idAuteur).ifPresent(auteur -> {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("identifiantAuteur", auteur.getIdentifiant());
                map.put("nomCompletAuteur", auteur.getPrenom() + " " + auteur.getNom());
                map.put("nombreLivres", nbLivres);
                resultat.add(map);
            });
        }
        return resultat;
    }
}
