package tp.leothy.springboot.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tp.leothy.springboot.dto.LivreCreationModificationDto;
import tp.leothy.springboot.dto.LivreDto;
import tp.leothy.springboot.exception.RessourceNonTrouveeException;
import tp.leothy.springboot.model.Auteur;
import tp.leothy.springboot.model.CategorieLivre;
import tp.leothy.springboot.model.Livre;
import tp.leothy.springboot.repository.AuteurRepository;
import tp.leothy.springboot.repository.LivreRepository;
import tp.leothy.springboot.repository.LivreSpecifications;
import tp.leothy.springboot.service.ServiceLivre;

@Service
public class ServiceLivreImpl implements ServiceLivre {

    private final LivreRepository repository;
    private final AuteurRepository auteurRepository;

    public ServiceLivreImpl(LivreRepository repository, AuteurRepository auteurRepository) {
        this.repository = repository;
        this.auteurRepository = auteurRepository;
    }

    @Override
    public Page<LivreDto> rechercher(String titre, Long identifiantAuteur, CategorieLivre categorie,
                                     Integer anneeMin, Integer anneeMax, Pageable pageable) {

        Specification<Livre> spec = Specification
                .where(LivreSpecifications.titreContient(titre))
                .and(LivreSpecifications.auteurEgal(identifiantAuteur))
                .and(LivreSpecifications.categorieEgale(categorie))
                .and(LivreSpecifications.anneeMin(anneeMin))
                .and(LivreSpecifications.anneeMax(anneeMax));

        return repository.findAll(spec, pageable)
                .map(this::versDto);
    }

    @Override
    public LivreDto trouverParIdentifiant(Long identifiant) {
        Livre livre = repository.findById(identifiant)
                .orElseThrow(() -> new RessourceNonTrouveeException("Livre " + identifiant + " introuvable."));
        return versDto(livre);
    }

    @Override
    public LivreDto creer(LivreCreationModificationDto dto) {
        verifierIsbnUnique(dto.getCodeIsbn(), null);

        Auteur auteur = auteurRepository.findById(dto.getIdentifiantAuteur())
                .orElseThrow(() -> new RessourceNonTrouveeException("Auteur " + dto.getIdentifiantAuteur() + " introuvable."));

        Livre livre = Livre.builder()
                .titre(dto.getTitre())
                .codeIsbn(dto.getCodeIsbn())
                .anneeParution(dto.getAnneeParution())
                .categorie(dto.getCategorie())
                .auteur(auteur)
                .build();
        try {
            Livre enregistre = repository.save(livre);
            return versDto(enregistre);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Un livre avec le même code ISBN existe déjà.");
        }
    }

    @Override
    public LivreDto modifier(Long identifiant, LivreCreationModificationDto dto) {
        Livre livre = repository.findById(identifiant)
                .orElseThrow(() -> new RessourceNonTrouveeException("Livre " + identifiant + " introuvable."));

        verifierIsbnUnique(dto.getCodeIsbn(), identifiant);

        Auteur auteur = auteurRepository.findById(dto.getIdentifiantAuteur())
                .orElseThrow(() -> new RessourceNonTrouveeException("Auteur " + dto.getIdentifiantAuteur() + " introuvable."));

        livre.setTitre(dto.getTitre());
        livre.setCodeIsbn(dto.getCodeIsbn());
        livre.setAnneeParution(dto.getAnneeParution());
        livre.setCategorie(dto.getCategorie());
        livre.setAuteur(auteur);

        Livre enregistre = repository.save(livre);
        return versDto(enregistre);
    }

    @Override
    public void supprimer(Long identifiant) {
        Livre livre = repository.findById(identifiant)
                .orElseThrow(() -> new RessourceNonTrouveeException("Livre " + identifiant + " introuvable."));
        repository.delete(livre);
    }

    private void verifierIsbnUnique(String isbn, Long identifiantLivreCourant) {
        boolean existe = repository.existsByCodeIsbn(isbn);
        if (existe && identifiantLivreCourant == null) {
            throw new IllegalArgumentException("Un livre avec ce code ISBN existe déjà.");
        }
        // Pour une mise à jour, on pourrait exclure le livre courant dans un cas plus avancé.
    }

    private LivreDto versDto(Livre livre) {
        String nomCompletAuteur = livre.getAuteur().getPrenom() + " " + livre.getAuteur().getNom();
        return LivreDto.builder()
                .identifiant(livre.getIdentifiant())
                .titre(livre.getTitre())
                .codeIsbn(livre.getCodeIsbn())
                .anneeParution(livre.getAnneeParution())
                .categorie(livre.getCategorie())
                .identifiantAuteur(livre.getAuteur().getIdentifiant())
                .nomCompletAuteur(nomCompletAuteur)
                .build();
    }
}
