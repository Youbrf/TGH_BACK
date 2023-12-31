package be.icc.tgh.faker;

import be.icc.tgh.model.*;
import be.icc.tgh.service.*;
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
    private ReservationS reservationS;

    @Autowired
    private ReviewS reviewService;

    @Autowired
    private PromotionS promotionService;


    public Generator(UserS userService, ServiceS serviceService, ReservationS reservationS, ReviewS reviewService, PromotionS promotionService) {
        this.userService = userService;
        this.serviceService = serviceService;
        this.reservationS = reservationS;
        this.reviewService = reviewService;
        this.promotionService = promotionService;
    }

    public Reservation generateFakeReservation() {
        Faker faker = new Faker(new Locale("fr"));
        Random random = new Random();
        Reservation reservation = new Reservation();
        LocalDate dateReservation = LocalDate.now().plusDays(random.nextLong(90)); // Génère une date aléatoire dans les 30 prochains jours
        int heureD = ThreadLocalRandom.current().nextInt(10, 18); // Génère un nombre aléatoire entre 10 (inclus) et 18 (exclus)
        int minuteDebut = ThreadLocalRandom.current().nextInt(0, 4)*15; // Génère un nombre aléatoire entre 0 (inclus) et 60 (exclus)
        LocalTime heureDebut = LocalTime.of(heureD, minuteDebut);

        // Génération des services
        List<Service> services = new ArrayList<Service>();
        services.add(serviceService.getRandomService());
        reservation.setServices(services);

        int dureTotaleDesServices = 0;
        Double montantTotal = 0.0;
        for (Service service : reservation.getServices()) {
            if (service != null) {
                dureTotaleDesServices += service.getDuree();
                montantTotal += service.getPrix();
            }
        }

        LocalTime heureFin = heureDebut.plusMinutes(dureTotaleDesServices);
        LocalDate dateCreation = LocalDate.now().minusDays(random.nextLong(30)); // Génère une date de création aléatoire dans les 15 derniers jours
        LocalDate dateModification = dateCreation.plusDays(random.nextInt(10)); // Génère une date de modification aléatoire entre la date de création et 10 jours après


        reservation.setDateReservation(dateReservation);
        reservation.setHeureDebut(heureDebut);
        reservation.setHeureFin(heureFin);
        reservation.setRemarquesSpeciales(faker.lorem().sentence());
        reservation.setStatutReservation(faker.options().option("CONFIRMED", "PENDING", "CANCELLED"));
        reservation.setMontantTotal(montantTotal);
        reservation.setModePaiement(faker.options().option("EN LIGNE", "SUR PLACE"));
        reservation.setDateCreation(dateCreation);
        reservation.setDateModification(dateModification);
        reservation.setDateAnnulation(null);
        reservation.setEtatPaiement(faker.options().option("PAID", "UNPAID"));

        // Génération de l'utilisateur
        User user = userService.getRandomUser(Role.USER);
        reservation.setUser(user);

        // Génération de l'employeur
        User employer = userService.getRandomUser(Role.EMPLOYEE);
        reservation.setEmployer(employer);

        reservationS.creerReservation(reservation);
        return reservation;
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

    public Review generateFakeReview(int number) {
        Faker faker = new Faker(new Locale("fr"));
        Random random = new Random();

        Review review = new Review();
        review.setRating(random.nextInt(6));
        review.setComment(faker.lorem().paragraph());

        Reservation reservationReview = reservationS.getAllReservation().get(number);

        review.setUser(reservationReview.getUser());
        review.setReservation(reservationReview);

        reviewService.creerReview(review);

        return review;
    }

    public Promotion generateFakePromotion() {
        Faker faker = new Faker(new Locale("fr"));

        Promotion promotion = new Promotion();
        promotion.setNom(faker.commerce().productName());
        promotion.setDescription(faker.lorem().sentence());
        promotion.setDateDebut(LocalDate.now().plusDays(ThreadLocalRandom.current().nextLong(30)));
        promotion.setDateFin(promotion.getDateDebut().plusDays(ThreadLocalRandom.current().nextLong(30, 90)));
        int randomIndex = ThreadLocalRandom.current().nextInt(0, 7);
        int[] reductionValues = {5, 10, 15, 20, 25, 30, 40};
        double reduction = reductionValues[randomIndex] / 100.0;
        promotion.setReduction(reduction);

        promotionService.creerPromotion(promotion);

        return promotion;
    }

}
