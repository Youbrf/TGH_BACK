package be.icc.tgh.faker;

import be.icc.tgh.model.RendezVous;
import be.icc.tgh.model.Role;
import be.icc.tgh.model.Service;
import be.icc.tgh.model.User;
import be.icc.tgh.service.RendezVousS;
import be.icc.tgh.service.ServiceS;
import be.icc.tgh.service.UserS;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    @Autowired
    private UserS userService;

    @Autowired
    private ServiceS serviceService;

    @Autowired
    private RendezVousS rendezVousS;

    public Generator(UserS userService, ServiceS serviceService, RendezVousS rendezVousS) {
        this.userService = userService;
        this.serviceService = serviceService;
        this.rendezVousS = rendezVousS;
    }

    public RendezVous generateFakeRendezVous() {
        Faker faker = new Faker(new Locale("fr"));
        Random random = new Random();
        RendezVous rendezVous = new RendezVous();
        LocalDate dateReservation = LocalDate.now().plusDays(random.nextLong(90)); // Génère une date aléatoire dans les 30 prochains jours
        int heureD = ThreadLocalRandom.current().nextInt(10, 18); // Génère un nombre aléatoire entre 10 (inclus) et 18 (exclus)
        int minuteDebut = ThreadLocalRandom.current().nextInt(0, 4)*15; // Génère un nombre aléatoire entre 0 (inclus) et 60 (exclus)
        LocalTime heureDebut = LocalTime.of(heureD, minuteDebut);

        // Génération des services
        List<Service> services = new ArrayList<Service>();
        services.add(serviceService.getRandomService());
        rendezVous.setServices(services);

        int dureTotaleDesServices = 0;
        Double montantTotal = 0.0;
        for (Service service : rendezVous.getServices()) {
            if (service != null) {
                dureTotaleDesServices += service.getDuree();
                montantTotal += service.getPrix();
            }
        }

        LocalTime heureFin = heureDebut.plusMinutes(dureTotaleDesServices);
        LocalDate dateCreation = LocalDate.now().minusDays(random.nextLong(30)); // Génère une date de création aléatoire dans les 15 derniers jours
        LocalDate dateModification = dateCreation.plusDays(random.nextInt(10)); // Génère une date de modification aléatoire entre la date de création et 10 jours après


        rendezVous.setDateReservation(dateReservation);
        rendezVous.setHeureDebut(heureDebut);
        rendezVous.setHeureFin(heureFin);
        rendezVous.setRemarquesSpeciales(faker.lorem().sentence());
        rendezVous.setStatutReservation(faker.options().option("CONFIRMED", "PENDING", "CANCELLED"));
        rendezVous.setMontantTotal(montantTotal);
        rendezVous.setModePaiement(faker.options().option("CASH", "CREDIT_CARD", "DEBIT_CARD"));
        rendezVous.setDateCreation(dateCreation);
        rendezVous.setDateModification(dateModification);
        rendezVous.setDateAnnulation(null);
        rendezVous.setEtatPaiement(faker.options().option("PAID", "UNPAID"));

        // Génération de l'utilisateur
        User user = userService.getRandomUser(Role.USER);
        rendezVous.setUser(user);

        // Génération de l'employeur
        User employer = userService.getRandomUser(Role.EMPLOYEE);
        rendezVous.setEmployer(employer);

        rendezVousS.creerRendezVous(rendezVous);
        return rendezVous;
    }

    public User generateFakeUser() {
        Faker faker = new Faker(new Locale("fr"));

        User user = new User();
        user.setFirstname(faker.name().firstName());
        user.setLastname(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setGsm(faker.phoneNumber().cellPhone());
        user.setRole(Role.USER);

        // Sauvegarde de l'utilisateur dans la base de données
        userService.creerUser(user);

        return user;
    }

    public User generateFakeEmployer() {
        Faker faker = new Faker(new Locale("fr"));

        User employer = new User();
        employer.setFirstname(faker.name().firstName());
        employer.setLastname(faker.name().lastName());
        employer.setEmail(faker.internet().emailAddress());
        employer.setPassword(faker.internet().password());
        employer.setGsm(faker.phoneNumber().cellPhone());
        employer.setRole(Role.EMPLOYEE);

        // Sauvegarde de l'employeur dans la base de données
        userService.creerUser(employer);

        return employer;
    }

}
