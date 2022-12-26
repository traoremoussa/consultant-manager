package com.kodiatech.traore;

import com.kodiatech.traore.profiles.models.Adresse;
import com.kodiatech.traore.profiles.models.Utilisateur;
import com.kodiatech.traore.profiles.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KodiactehConsultantManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KodiactehConsultantManagerApplication.class, args);
	}
@Bean
	public CommandLineRunner commandLineRunner(UtilisateurRepository utilisateurRepository){
		return e->{
		/*	final Adresse address = new Adresse("Thomas edison", "bat a, appt 68", "31400", "Toulouse");

			final Utilisateur utilisateur=new Utilisateur("Traore","moussa","password8","hij@gmail.com","00-00-00-00-00-00",address);

			//utilisateurRepository.save(utilisateur);

		 */
		};

	}


}
