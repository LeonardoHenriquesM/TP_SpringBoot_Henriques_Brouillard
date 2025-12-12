package tp.leothy.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuteurCreationModificationDto {

    @NotBlank(message = "Le prénom est obligatoire.")
    private String prenom;

    @NotBlank(message = "Le nom est obligatoire.")
    private String nom;

    @NotNull(message = "L'année de naissance est obligatoire.")
    @PositiveOrZero(message = "L'année de naissance doit être positive ou nulle.")
    private Integer anneeNaissance;
}
