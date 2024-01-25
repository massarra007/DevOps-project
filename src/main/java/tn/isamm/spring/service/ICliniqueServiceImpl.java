package tn.isamm.spring.service;

import org.springframework.stereotype.Service;
import tn.isamm.spring.entities.Consultation;
import tn.isamm.spring.entities.Medecin;
import tn.isamm.spring.entities.Patient;
import tn.isamm.spring.entities.RendezVous;
import tn.isamm.spring.repository.ConsultationRepository;
import tn.isamm.spring.repository.MedecinRepository;
import tn.isamm.spring.repository.PatientRepository;
import tn.isamm.spring.repository.RendezVousRepository;

import java.util.List;

@Service
public class ICliniqueServiceImpl implements ICliniqueService {
    private ConsultationRepository consultationRepository;
    private MedecinRepository medecinRepository;
    private PatientRepository patientRepository;
    private RendezVousRepository rendezVousRepository;
    public ICliniqueServiceImpl(ConsultationRepository consultationRepository, MedecinRepository medecinRepository, PatientRepository patientRepository, RendezVousRepository rendezVousRepository) {
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save((patient));
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save((medecin));
    }

    @Override
    public RendezVous saveRendezVous(String patientName, String medecinName, RendezVous rendezVous) {
        // Retrieve patient and medic entities from their respective repositories
        Patient patient = patientRepository.findByNom(patientName);
        Medecin medecin = medecinRepository.findByNom(medecinName);

        if (patient != null && medecin != null) {
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);

            // Save the rendezvous
            return rendezVousRepository.save(rendezVous);
        } else {
            // Handle scenario where either patient or medic is not found
            // You can throw an exception or handle it as needed
            return null;
        }
    }


    @Override
    public Consultation saveConsultation(Consultation consultation) {
        Long selectedId;
        if (consultation.getRendezVous() == null) {
            if (Math.random() > 0.5) {
                if (Math.random() > 0.5) {
                    selectedId = 4L;
                } else {
                    selectedId = 5L;
                }
            } else {
                if (Math.random() > 0.5) {
                    selectedId = 1L;
                } else {
                    selectedId = 2L;
                }
            }
            consultation.setRendezVous(rendezVousRepository.getById(selectedId));
        }
        return consultationRepository.save(consultation);
    }

    @Override
    public List<Consultation> showAllConsultations() {
        return consultationRepository.findAll();
    }

}
