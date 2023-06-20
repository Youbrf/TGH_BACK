package be.icc.tgh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateReservation;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String remarquesSpeciales;
    private String statutReservation;
    private double montantTotal;
    private String modePaiement;
    private LocalDate dateCreation;
    private LocalDate dateModification;
    private LocalDate dateAnnulation;
    private String etatPaiement;
    @ManyToOne
    @JsonIgnore
    private Client client;
    @ManyToOne
    @JsonIgnore
    private Estheticienne estheticienne;
    @ManyToMany
    @JoinTable(name = "rendezvous_service",joinColumns = @JoinColumn(name = "id_rendezvous"), inverseJoinColumns = @JoinColumn(name = "id_service"))
    private List<Service> services;

}