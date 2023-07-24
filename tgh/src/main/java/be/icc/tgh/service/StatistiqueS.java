package be.icc.tgh.service;

import be.icc.tgh.model.RendezVous;
import be.icc.tgh.model.Service;
import be.icc.tgh.repository.RendezVousR;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class StatistiqueS {
    @Autowired
    private RendezVousR rendezVousRepository;

    public Long getTotalReservations() {
        return rendezVousRepository.count();
    }

    public Double getAverageReservationDuration() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        return rendezVousList.stream()
                .mapToLong(r -> Duration.between(r.getHeureDebut(), r.getHeureFin()).toMinutes())
                .average()
                .orElse(0.0);
    }

    public Map<LocalDate, Long> getReservationsByDay() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        return rendezVousList.stream()
                .collect(Collectors.groupingBy(RendezVous::getDateReservation, Collectors.counting()));
    }

    public Map<Integer, Long> getReservationsByWeek() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return rendezVousList.stream()
                .collect(Collectors.groupingBy(date -> date.getDateReservation().get(weekFields.weekOfYear()), Collectors.counting()));
    }

    public Map<Integer, Long> getReservationsByMonth() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        return rendezVousList.stream()
                .collect(Collectors.groupingBy(date -> date.getDateReservation().getMonthValue(), Collectors.counting()));
    }

    public Map<String, Long> getReservationStatus() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        return rendezVousList.stream()
                .collect(Collectors.groupingBy(RendezVous::getStatutReservation, Collectors.counting()));
    }

    public Map<String, Long> getMostDemandedServices() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        Map<String, Long> serviceCountMap = rendezVousList.stream()
                .flatMap(r -> r.getServices().stream())
                .collect(Collectors.groupingBy(Service::getNom, Collectors.counting()));
        return serviceCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public Map<String, Long> getMostRequestedEmployees() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        return rendezVousList.stream()
                .collect(Collectors.groupingBy(r -> r.getEmployer().getFirstname(), Collectors.counting()));
    }

    public Double getBookingOccupancyRate() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        long totalSlots = rendezVousList.stream()
                .mapToLong(r -> Duration.between(r.getHeureDebut(), r.getHeureFin()).toMinutes())
                .sum();
        long occupiedSlots = rendezVousList.stream()
                .mapToLong(r -> Duration.between(r.getHeureDebut(), r.getHeureFin()).toMinutes())
                .sum();
        return (double) occupiedSlots / totalSlots * 100;
    }
    /*
    public Double getReservationConversionRate() {
        // Implementer la logique pour suivre le taux de conversion des réservations
        return 0.0;
    }

    public List<Commentaire> getCustomerCommentsAndReviews() {
        // Implementer la logique pour récupérer les commentaires et évaluations des clients
        return null;
    }*/
    public Double getTotalRevenueFromBookings() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        return rendezVousList.stream()
                .mapToDouble(RendezVous::getMontantTotal)
                .sum();
    }

}
