package tp.leothy.springboot.service.impl;

import org.springframework.stereotype.Service;
import tp.leothy.springboot.dto.AuteurCreationModificationDto;
import tp.leothy.springboot.dto.AuteurDto;
import tp.leothy.springboot.exception.RessourceNonTrouveeException;
import tp.leothy.springboot.model.Auteur;
import tp.leothy.springboot.repository.AuteurRepository;
import tp.leothy.springboot.service.ServiceAuteur;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceAuteurImpl implements ServiceAuteur {

    private final AuteurRepository repository;

    public ServiceAuteurImpl(AuteurRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AuteurDto> listerTous() {
        return repository.findAll().stream()
                .map(this::versDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuteurDto trouverParIdentifiant(Long identifiant) {
        Auteur auteur = repository.findById(identifiant)
                .orElseThrow(() -> new RessourceNonTrouveeException("Auteur " + identifiant + " introuvable."));
        return versDto(auteur);
    }

    @Override
    public AuteurDto creer(AuteurCreationModificationDto dto) {
        Auteur auteur = Auteur.builder()
                .prenom(dto.getPrenom())
                .nom(dto.getNom())
                .anneeNaissance(dto.getAnneeNaissance())
                .build();
        Auteur enregistre = repository.save(auteur);
        return versDto(enregistre);
    }

    @Override
    public AuteurDto modifier(Long identifiant, AuteurCreationModificationDto dto) {
        Auteur auteur = repository.findById(identifiant)
                .orElseThrow(() -> new RessourceNonTrouveeException("Auteur " + identifiant + " introuvable."));
        auteur.setPrenom(dto.getPrenom());
        auteur.setNom(dto.getNom());
        auteur.setAnneeNaissance(dto.getAnneeNaissance());
        Auteur enregistre = repository.save(auteur);
        return versDto(enregistre);
    }

    @Override
    public void supprimer(Long identifiant) {
        Auteur auteur = repository.findById(identifiant)
                .orElseThrow(() -> new RessourceNonTrouveeException("Auteur " + identifiant + " introuvable."));
        repository.delete(auteur);
    }

    private AuteurDto versDto(Auteur auteur) {
        return AuteurDto.builder()
                .identifiant(auteur.getIdentifiant())
                .prenom(auteur.getPrenom())
                .nom(auteur.getNom())
                .anneeNaissance(auteur.getAnneeNaissance())
                .build();
    }
}
