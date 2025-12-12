package tp.leothy.springboot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "livres",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "code_isbn")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifiant;

    @Column(name = "titre")
    private String titre;

    @Column(name = "code_isbn")
    private String codeIsbn;

    @Column(name = "annee_parution")
    private Integer anneeParution;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie")
    private CategorieLivre categorie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "auteur_id")
    @ToString.Exclude
    private Auteur auteur;
}
