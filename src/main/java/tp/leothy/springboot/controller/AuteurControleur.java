package tp.leothy.springboot.controller;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.leothy.springboot.dto.AuteurCreationModificationDto;
import tp.leothy.springboot.dto.AuteurDto;
import tp.leothy.springboot.service.ServiceAuteur;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auteurs")
public class AuteurControleur {

    private final ServiceAuteur serviceAuteur;

    public AuteurControleur(ServiceAuteur serviceAuteur) {
        this.serviceAuteur = serviceAuteur;
    }

    @GetMapping
    public List<AuteurDto> listerTous() {
        return serviceAuteur.listerTous();
    }

    @GetMapping("/{identifiant}")
    public AuteurDto recuperer(@PathVariable Long identifiant) {
        return serviceAuteur.trouverParIdentifiant(identifiant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuteurDto creer(@Valid @RequestBody AuteurCreationModificationDto dto) {
        return serviceAuteur.creer(dto);
    }

    @PutMapping("/{identifiant}")
    public AuteurDto modifier(@PathVariable Long identifiant,
                              @Valid @RequestBody AuteurCreationModificationDto dto) {
        return serviceAuteur.modifier(identifiant, dto);
    }

    @DeleteMapping("/{identifiant}")
    public ResponseEntity<Map<String, String>> supprimer(@PathVariable Long identifiant) {
        serviceAuteur.supprimer(identifiant);

        Map<String, String> reponse = new HashMap<>();
        reponse.put("message", "Auteur supprimé avec succès.");
        return ResponseEntity.ok(reponse);
    }


}
