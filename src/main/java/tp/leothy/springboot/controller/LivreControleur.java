package tp.leothy.springboot.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.leothy.springboot.dto.LivreCreationModificationDto;
import tp.leothy.springboot.dto.LivreDto;
import tp.leothy.springboot.model.CategorieLivre;
import tp.leothy.springboot.service.ServiceLivre;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/livres")
public class LivreControleur {

    private final ServiceLivre serviceLivre;

    public LivreControleur(ServiceLivre serviceLivre) {
        this.serviceLivre = serviceLivre;
    }

    @GetMapping
    public Page<LivreDto> rechercher(
            @RequestParam(name = "titre", required = false) String titre,
            @RequestParam(name = "identifiantAuteur", required = false) Long identifiantAuteur,
            @RequestParam(name = "categorie", required = false) CategorieLivre categorie,
            @RequestParam(name = "anneeMin", required = false) Integer anneeMin,
            @RequestParam(name = "anneeMax", required = false) Integer anneeMax,
            Pageable pageable
    ) {
        return serviceLivre.rechercher(titre, identifiantAuteur, categorie, anneeMin, anneeMax, pageable);
    }

    @GetMapping("/{identifiant}")
    public LivreDto recuperer(@PathVariable Long identifiant) {
        return serviceLivre.trouverParIdentifiant(identifiant);
    }

    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public LivreDto creer(@Valid @RequestBody LivreCreationModificationDto dto) {
        return serviceLivre.creer(dto);
    }

    @PutMapping("/{identifiant}")
    public LivreDto modifier(@PathVariable Long identifiant,
                             @Valid @RequestBody LivreCreationModificationDto dto) {
        return serviceLivre.modifier(identifiant, dto);
    }

    @DeleteMapping("/{identifiant}")
    public ResponseEntity<Map<String, String>> supprimer(@PathVariable Long identifiant) {
        serviceLivre.supprimer(identifiant);
        Map<String, String> reponse = new HashMap<>();
        reponse.put("message", "Livre supprimé");
        return ResponseEntity.ok(reponse);
    }

}
