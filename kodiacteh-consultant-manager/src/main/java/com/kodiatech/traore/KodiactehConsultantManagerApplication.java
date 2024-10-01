package com.kodiatech.traore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//TODO @EnableMongoRepositories
public class KodiactehConsultantManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KodiactehConsultantManagerApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner commandLineRunner(UtilisateurRepository utilisateurRepository){
		return e->{
		final Adresse address = new Adresse("Thomas edison", "bat a, appt 68", "31400", "Toulouse");

			final Utilisateur utilisateur=new Utilisateur("Traore","moussa","password8","hij@gmail.com","00-00-00-00-00-00",address);

			//utilisateurRepository.save(utilisateur);


		};

	}
*/

	@Bean
	public Function<String, String> reverse() {
		return (s) -> String.valueOf(new StringBuilder(s).reverse());
	}

/* fait l'affaire aussi
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*")
						.allowCredentials(false).maxAge(3600);
			}
		};
	}*/

	// CLEAN ARCHI
	//https://www.youtube.com/watch?v=p029gSnlnfU&ab_channel=DevoxxFR
}
