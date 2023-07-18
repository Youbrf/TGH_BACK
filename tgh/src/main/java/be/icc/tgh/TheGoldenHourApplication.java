package be.icc.tgh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheGoldenHourApplication {
	public static void main(String[] args) {
		SpringApplication.run(TheGoldenHourApplication.class, args);
	}
/*
	@Bean
	CommandLineRunner run(ServiceS s, CategorieServiceS c, RendezVousS r, UserS u) {
		return args -> {
			u.creerUser(new User("admin@gmail.com","admin", Role.ADMIN));
			u.creerUser(new User("user@gmail.com","user",Role.USER));
			c.creerCategorieService(new CategorieService(null, "Beauté Des Mains",null));
			c.creerCategorieService(new CategorieService(null, "Beauté Des Pieds",null));
			c.creerCategorieService(new CategorieService(null, "Faux Ongles",null));
			c.creerCategorieService(new CategorieService(null, "Beauté Du Regard",null));
			c.creerCategorieService(new CategorieService(null, "Soin Du Visage",null));
			c.creerCategorieService(new CategorieService(null, "Beauté De Regard",null));
			s.creerService(new Service(null, "Pose de vernis - mains",15, 15,c.getCategorieServiceByID(1L)));
			s.creerService(new Service(null, "Pose de vernis semi-permanent - mains",60, 40, c.getCategorieServiceByID(1L)));
			s.creerService(new Service(null, "Manucure spa + pose vernis classique",40, 30, c.getCategorieServiceByID(1L)));
			s.creerService(new Service(null, "Soin à la paraffine mains",30, 30, c.getCategorieServiceByID(1L)));
			s.creerService(new Service(null, "Pack semi-permanent mains et pieds",120, 75, c.getCategorieServiceByID(1L)));
			s.creerService(new Service(null, "Pack soins paraffine + manucure vernis classique",70, 55, c.getCategorieServiceByID(1L)));
			s.creerService(new Service(null, "Dépose de vernis semi-permanent mains",30, 20, c.getCategorieServiceByID(1L)));
			s.creerService(new Service(null,"Pose de vernis - pieds",15,  15, c.getCategorieServiceByID(2L)));
			s.creerService(new Service(null, "Pose de vernis semi-permanent - pieds",60, 40, c.getCategorieServiceByID(2L)));
			s.creerService(new Service(null,  "Pédicure spa + pose vernis classique",60, 50, c.getCategorieServiceByID(2L)));
			s.creerService(new Service(null,  "Pédicure spa + vernis semi-permanent",75, 80, c.getCategorieServiceByID(2L)));
			s.creerService(new Service(null,  "Soin à la paraffine - pieds",35, 30, c.getCategorieServiceByID(2L)));
			s.creerService(new Service(null,  "Dépose de vernis semi-permanent - pieds",25, 20, c.getCategorieServiceByID(2L)));
			s.creerService(new Service(null,  "Pose d'ongles en résine / Acrylique",110, 65, c.getCategorieServiceByID(3L)));
			s.creerService(new Service(null,  "Retouche d'ongles en résine / Acrylique",90, 55, c.getCategorieServiceByID(3L)));
			s.creerService(new Service(null,  "Dépose d'ongles en gel/acrylique",35, 25, c.getCategorieServiceByID(3L)));
			s.creerService(new Service(null,  "Teinture des sourcils",15, 20, c.getCategorieServiceByID(4L)));
			s.creerService(new Service(null,  "Pack teinture cils + sourcils",35, 35, c.getCategorieServiceByID(4L)));
			s.creerService(new Service(null,  "Soin du visage complet",60, 70, c.getCategorieServiceByID(5L)));
			s.creerService(new Service(null, "Teinture des cils", 20, 25, c.getCategorieServiceByID(6L)));
			s.creerService(new Service(null,  "Dépose d'extensions de cils",30, 25, c.getCategorieServiceByID(6L)));
			// Création de l'instance du générateur avec les repositories
			Generator rendezVousGenerator = new Generator(u, s, r);

			for (int i = 0; i < 100; i++) {
				User user = rendezVousGenerator.generateFakeUser();
			}

			// Création et enregistrement des employeurs
			for (int i = 0; i < 5; i++) {
				User employer = rendezVousGenerator.generateFakeEmployer();
			}

			// Génération des rendez-vous factices et enregistrement
			for (int i = 0; i < 1000; i++) {
				RendezVous rendezVous = rendezVousGenerator.generateFakeRendezVous();
			}

		};
	}*/
	}