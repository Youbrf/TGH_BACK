package be.icc.tgh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @ManyToMany
    @JoinTable(name = "promotions_services", joinColumns = @JoinColumn(name = "id_promotion"), inverseJoinColumns = @JoinColumn(name = "id_service"))
    private List<Service> services;
}
