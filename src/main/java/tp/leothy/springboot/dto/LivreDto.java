package tp.leothy.springboot.dto;

import lombok.*;
import tp.leothy.springboot.model.CategorieLivre;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivreDto {

    private Long identifiant;
    private String titre;
    private String codeIsbn;
    private Integer anneeParution;
    private CategorieLivre categorie;
    private Long identifiantAuteur;
    private String nomCompletAuteur;
}
