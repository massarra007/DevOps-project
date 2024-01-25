package tn.isamm.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.isamm.spring.entities.Consultation;
@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}

