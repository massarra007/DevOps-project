package tn.isamm.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.isamm.spring.entities.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByNomContains(String nom, Pageable pageable);
    Patient findByNom(String patientName);
}
