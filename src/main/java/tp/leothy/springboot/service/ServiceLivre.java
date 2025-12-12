package tp.leothy.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tp.leothy.springboot.dto.LivreCreationModificationDto;
import tp.leothy.springboot.dto.LivreDto;
import tp.leothy.springboot.model.CategorieLivre;

public interface ServiceLivre {

    Page<LivreDto> rechercher(
            String titre,
            Long identifiantAuteur,
            CategorieLivre categorie,
            Integer anneeMin,
            Integer anneeMax,
            Pageable pageable
    );

    LivreDto trouverParIdentifiant(Long identifiant);

    LivreDto creer(LivreCreationModificationDto dto);

    LivreDto modifier(Long identifiant, LivreCreationModificationDto dto);

    void supprimer(Long identifiant);
}
