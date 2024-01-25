package tn.isamm.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.isamm.spring.entities.Medecin;
import tn.isamm.spring.entities.Patient;
import tn.isamm.spring.repository.MedecinRepository;
import tn.isamm.spring.repository.PatientRepository;
import tn.isamm.spring.repository.RendezVousRepository;

import java.util.List;

@EnableAspectJAutoProxy
@EnableScheduling
@SpringBootApplication
public class GestionCliniqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionCliniqueApplication.class, args);
	}

	//@Bean
	//@Transactional
	CommandLineRunner commandLineRunner(
			PatientRepository patientRepository,
			RendezVousRepository rendezVousDAO,
			MedecinRepository medecinRepository
	){
		return args-> {
			System.out.println("test");
			/*Stream.of("Xavier", "norman", "kai").forEach(name->{
						Patient patient= new Patient();
						patient.setNom(name);
						patient.setAdresse("adresse");
						patient.setTitre(Titre.Mr);
						patient.setCodePostal("code" +(int)(Math.random()*58));
						patient.setNumeroTelephone("06"+(int)(Math.random()*90000));
						patient.setDateNaissance(new Date());
						patient.setMalade((Math.random()*568)>0.5);
						patient.setRendezVous(null);
						patientRepository.save(patient);
					}


			);*/
			List<Patient> listp= patientRepository.findAll();
			for (Patient p:listp
			) {
				p.getNom();

			}
			/*Stream.of("loubna", "hayat", "hamza")
					.forEach(name -> {
								Medecin medecin = new Medecin();
								medecin.setNom(name);
								medecin.setEmail(name + "@gmail.com");
								medecin.setSpecialite("dentaire");
								medecin.setRendezVous(null);
								medecinRepository.save(medecin);
								System.out.println("done");
							}

					);*/
			List<Medecin> medecins= medecinRepository.findAll();
			for (Medecin m: medecins
			) {

				System.out.println("nom=>"+m.getNom());
			}
			/*Stream.of(RendezVous.StatusRDV.CANCELED,RendezVous.StatusRDV.DONE,RendezVous.StatusRDV.PENDING )
					.forEach( status->{
						RendezVous rendezVous= new RendezVous();
						rendezVous.setDate(new Date());
						rendezVous.setStatusRDV(status);
						rendezVous.setPatient(listp.get(Math.random()>0.5?(Math.random()<0.5?1:2):(Math.random()<0.5?4:3)));
						rendezVous.setMedecin(medecins.get(Math.random()>0.5?(Math.random()<0.5?1:2):(Math.random()<0.5?4:3)));
						rendezVousRepository.save(rendezVous);
					}

			);
			List<RendezVous> RDVs = rendezVousRepository.findAll();
			for (RendezVous r :
					RDVs) {
				r.getId();
			}*/


		};





	}
	//@Bean
	//CommandLineRunner saveUsers(SecurityService securityService){
		//return args ->{
		//	securityService.saveNewUser("loubna","1234","1234");
		//	securityService.saveNewUser("testuser1","1234","1234");
		//	securityService.saveNewUser("testuser2","1234","1234");

		//	securityService.saveNewRole("USER","");
		//	securityService.saveNewRole("ADMIN","");

		//	securityService.addRoleToUser("loubna","USER");
		//	securityService.addRoleToUser("loubna","ADMIN");
		//	securityService.addRoleToUser("testuser1","USER");
		//	securityService.addRoleToUser("testuser2","USER");


		};
	//}
//}

//}
