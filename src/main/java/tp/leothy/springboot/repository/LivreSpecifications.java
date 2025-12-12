package tp.leothy.springboot.repository;

import org.springframework.data.jpa.domain.Specification;
import tp.leothy.springboot.model.CategorieLivre;
import tp.leothy.springboot.model.Livre;

public final class LivreSpecifications {

    private LivreSpecifications() {}

    public static Specification<Livre> titreContient(String titre) {
        return (racine, requete, cb) -> {
            if (titre == null || titre.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(racine.get("titre")), "%" + titre.toLowerCase() + "%");
        };
    }

    public static Specification<Livre> auteurEgal(Long identifiantAuteur) {
        return (racine, requete, cb) -> {
            if (identifiantAuteur == null) {
                return cb.conjunction();
            }
            return cb.equal(racine.get("auteur").get("identifiant"), identifiantAuteur);
        };
    }

    public static Specification<Livre> categorieEgale(CategorieLivre categorie) {
        return (racine, requete, cb) -> {
            if (categorie == null) {
                return cb.conjunction();
            }
            return cb.equal(racine.get("categorie"), categorie);
        };
    }

    public static Specification<Livre> anneeMin(Integer anneeMin) {
        return (racine, requete, cb) -> {
            if (anneeMin == null) {
                return cb.conjunction();
            }
            return cb.greaterThanOrEqualTo(racine.get("anneeParution"), anneeMin);
        };
    }

    public static Specification<Livre> anneeMax(Integer anneeMax) {
        return (racine, requete, cb) -> {
            if (anneeMax == null) {
                return cb.conjunction();
            }
            return cb.lessThanOrEqualTo(racine.get("anneeParution"), anneeMax);
        };
    }
}
