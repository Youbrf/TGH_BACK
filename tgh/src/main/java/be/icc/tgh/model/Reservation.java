package be.icc.tgh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate dateReservation;
    @NotNull
    private LocalTime heureDebut;
    @NotNull
    private LocalTime heureFin;
    private String remarquesSpeciales;
    @NotNull
    private String statutReservation;
    @NotNull
    private double montantTotal;
    @NotNull
    private String modePaiement;
    @NotNull
    private LocalDate dateCreation;
    private LocalDate dateModification;
    private LocalDate dateAnnulation;
    @NotNull
    private String etatPaiement;
    @ManyToOne
    @NotNull
    private User user;
    @ManyToOne
    @NotNull
    private User employer;
    @ManyToMany
    @JoinTable(name = "Reservation_service",joinColumns = @JoinColumn(name = "id_Reservation"), inverseJoinColumns = @JoinColumn(name = "id_service"))
    private List<Service> services;
    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    @JsonIgnore
    private Review review;

}