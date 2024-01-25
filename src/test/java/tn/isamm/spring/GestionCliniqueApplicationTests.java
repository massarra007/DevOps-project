package tn.isamm.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.isamm.spring.entities.Consultation;
import tn.isamm.spring.entities.Medecin;
import tn.isamm.spring.entities.Patient;
import tn.isamm.spring.entities.RendezVous;
import tn.isamm.spring.repository.ConsultationRepository;
import tn.isamm.spring.repository.MedecinRepository;
import tn.isamm.spring.repository.PatientRepository;
import tn.isamm.spring.repository.RendezVousRepository;
import tn.isamm.spring.service.ICliniqueServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GestionCliniqueApplicationTests {

    @InjectMocks
    private ICliniqueServiceImpl yourService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private MedecinRepository medecinRepository;

    @Mock
    private RendezVousRepository rendezVousRepository;

    @Mock
    private ConsultationRepository consultationRepository;

    @Test
    public void testSavePatient() {
        Patient patient = new Patient();
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient savedPatient = yourService.savePatient(patient);

        assertNotNull(savedPatient);
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    public void testSaveMedecin() {
        Medecin medecin = new Medecin();
        when(medecinRepository.save(any(Medecin.class))).thenReturn(medecin);

        Medecin savedMedecin = yourService.saveMedecin(medecin);

        assertNotNull(savedMedecin);
        verify(medecinRepository, times(1)).save(any(Medecin.class));
    }

    @Test
    public void testSaveRendezVous() {
        Patient patient = new Patient();
        Medecin medecin = new Medecin();
        RendezVous rendezVous = new RendezVous();
        when(patientRepository.findByNom(anyString())).thenReturn(patient);
        when(medecinRepository.findByNom(anyString())).thenReturn(medecin);
        when(rendezVousRepository.save(any(RendezVous.class))).thenReturn(rendezVous);

        RendezVous savedRendezVous = yourService.saveRendezVous("patientName", "medecinName", new RendezVous());

        assertNotNull(savedRendezVous);
        verify(patientRepository, times(1)).findByNom(anyString());
        verify(medecinRepository, times(1)).findByNom(anyString());
        verify(rendezVousRepository, times(1)).save(any(RendezVous.class));
    }

    @Test
    public void testSaveConsultation() {
        Consultation consultation = new Consultation();
        when(consultationRepository.save(any(Consultation.class))).thenReturn(consultation);

        Consultation savedConsultation = yourService.saveConsultation(new Consultation());

        assertNotNull(savedConsultation);
        verify(consultationRepository, times(1)).save(any(Consultation.class));
    }
}

