package be.icc.tgh.controller;

import be.icc.tgh.service.StatistiqueS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/statistiques")
public class StatistiqueC {
    @Autowired
    private StatistiqueS statistiqueService;

    @GetMapping("/totalReservations")
    public Long getTotalReservations() {
        return statistiqueService.getTotalReservations();
    }

    @GetMapping("/averageReservationDuration")
    public Double getAverageReservationDuration() {
        return statistiqueService.getAverageReservationDuration();
    }

    @GetMapping("/reservationsByDay")
    public Map<LocalDate, Long> getReservationsByDay() {
        return statistiqueService.getReservationsByDay();
    }

    @GetMapping("/reservationsByWeek")
    public Map<Integer, Long> getReservationsByWeek() {
        return statistiqueService.getReservationsByWeek();
    }

    @GetMapping("/reservationsByMonth")
    public Map<Integer, Long> getReservationsByMonth() {
        return statistiqueService.getReservationsByMonth();
    }

    @GetMapping("/reservationStatus")
    public Map<String, Long> getReservationStatus() {
        return statistiqueService.getReservationStatus();
    }

    @GetMapping("/mostDemandedServices")
    public Map<String, Long> getMostDemandedServices() {
        return statistiqueService.getMostDemandedServices();
    }

    @GetMapping("/mostRequestedEmployees")
    public Map<String, Long> getMostRequestedEmployees() {
        return statistiqueService.getMostRequestedEmployees();
    }

    @GetMapping("/bookingOccupancyRate")
    public Double getBookingOccupancyRate() {
        return statistiqueService.getBookingOccupancyRate();
    }
    /*
    @GetMapping("/reservationConversionRate")
    public Double getReservationConversionRate() {
        return statistiqueService.getReservationConversionRate();
    }

    @GetMapping("/customerCommentsAndReviews")
    public List<Commentaire> getCustomerCommentsAndReviews() {
        return statistiqueService.getCustomerCommentsAndReviews();
    }*/

    @GetMapping("/totalRevenueFromBookings")
    public Double getTotalRevenueFromBookings() {
        return statistiqueService.getTotalRevenueFromBookings();
    }
}
