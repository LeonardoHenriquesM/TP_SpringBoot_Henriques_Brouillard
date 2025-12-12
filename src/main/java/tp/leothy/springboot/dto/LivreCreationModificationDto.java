package tp.leothy.springboot.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import tp.leothy.springboot.model.CategorieLivre;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivreCreationModificationDto {

    @NotBlank(message = "Le titre est obligatoire.")
    private String titre;

    @NotBlank(message = "Le code ISBN est obligatoire.")
    @Pattern(
            regexp = "^[0-9\\-]{10,17}$",
            message = "Le code ISBN doit contenir uniquement des chiffres et des tirets (10 à 17 caractères)."
    )
    private String codeIsbn;

    @NotNull(message = "L'année de parution est obligatoire.")
    @Min(value = 1450, message = "L'année de parution doit être supérieure ou égale à 1450.")
    private Integer anneeParution;

    @NotNull(message = "La catégorie est obligatoire.")
    private CategorieLivre categorie;

    @NotNull(message = "L'identifiant de l'auteur est obligatoire.")
    private Long identifiantAuteur;
}
