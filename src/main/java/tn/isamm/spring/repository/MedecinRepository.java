package tn.isamm.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.isamm.spring.entities.Medecin;
@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Page<Medecin> findByNomContains(String nom, Pageable pageable);
    Medecin findByNom(String medecinName);
}
