package tp.leothy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp.leothy.springboot.model.Auteur;

@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Long> {
}
