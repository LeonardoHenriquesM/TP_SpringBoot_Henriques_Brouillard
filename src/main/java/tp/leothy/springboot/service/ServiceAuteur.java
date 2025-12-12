package tp.leothy.springboot.service;

import tp.leothy.springboot.dto.AuteurCreationModificationDto;
import tp.leothy.springboot.dto.AuteurDto;

import java.util.List;

public interface ServiceAuteur {

    List<AuteurDto> listerTous();

    AuteurDto trouverParIdentifiant(Long identifiant);

    AuteurDto creer(AuteurCreationModificationDto dto);

    AuteurDto modifier(Long identifiant, AuteurCreationModificationDto dto);

    void supprimer(Long identifiant);
}
