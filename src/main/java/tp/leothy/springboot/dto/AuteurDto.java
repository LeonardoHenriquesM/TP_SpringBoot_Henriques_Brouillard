package tp.leothy.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuteurDto {

    private Long identifiant;
    private String prenom;
    private String nom;
    private Integer anneeNaissance;
}
