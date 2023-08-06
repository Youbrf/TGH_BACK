package be.icc.tgh.service;

import be.icc.tgh.model.Reservation;
import be.icc.tgh.model.Service;
import be.icc.tgh.repository.ReservationR;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class StatistiqueS {
    @Autowired
    private ReservationR reservationRepository;

    public Long getTotalReservations() {
        return reservationRepository.count();
    }

    public Double getAverageReservationDuration() {
        List<Reservation> reservationList = reservationRepository.findAll();
        return reservationList.stream()
                .mapToLong(r -> Duration.between(r.getHeureDebut(), r.getHeureFin()).toMinutes())
                .average()
                .orElse(0.0);
    }

    public Map<LocalDate, Long> getReservationsByDay() {
        List<Reservation> reservationList = reservationRepository.findAll();
        return reservationList.stream()
                .collect(Collectors.groupingBy(Reservation::getDateReservation, Collectors.counting()));
    }

    public Map<Integer, Long> getReservationsByWeek() {
        List<Reservation> reservationList = reservationRepository.findAll();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return reservationList.stream()
                .collect(Collectors.groupingBy(date -> date.getDateReservation().get(weekFields.weekOfYear()), Collectors.counting()));
    }

    public Map<Integer, Long> getReservationsByMonth() {
        List<Reservation> reservationList = reservationRepository.findAll();
        return reservationList.stream()
                .collect(Collectors.groupingBy(date -> date.getDateReservation().getMonthValue(), Collectors.counting()));
    }

    public Map<String, Long> getReservationStatus() {
        List<Reservation> reservationList = reservationRepository.findAll();
        return reservationList.stream()
                .collect(Collectors.groupingBy(Reservation::getStatutReservation, Collectors.counting()));
    }

    public Map<String, Long> getMostDemandedServices() {
        List<Reservation> reservationList = reservationRepository.findAll();
        Map<String, Long> serviceCountMap = reservationList.stream()
                .flatMap(r -> r.getServices().stream())
                .collect(Collectors.groupingBy(Service::getNom, Collectors.counting()));
        return serviceCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public Map<String, Long> getMostRequestedEmployees() {
        List<Reservation> reservationList = reservationRepository.findAll();
        return reservationList.stream()
                .collect(Collectors.groupingBy(r -> r.getEmployer().getFirstname(), Collectors.counting()));
    }

    public Double getBookingOccupancyRate() {
        List<Reservation> reservationList = reservationRepository.findAll();
        long totalSlots = reservationList.stream()
                .mapToLong(r -> Duration.between(r.getHeureDebut(), r.getHeureFin()).toMinutes())
                .sum();
        long occupiedSlots = reservationList.stream()
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
        List<Reservation> reservationList = reservationRepository.findAll();
        return reservationList.stream()
                .mapToDouble(Reservation::getMontantTotal)
                .sum();
    }

}
