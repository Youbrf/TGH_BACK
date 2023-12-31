package be.icc.tgh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nom;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    @NotNull
    private Integer duree;
    @NotNull
    private double prix;
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    @NotNull
    private CategorieService categorie;
    @ManyToOne
    @JoinColumn(name = "id_promotion")
    private Promotion promotion;

    public Service(String nom, String description, int duree, int prix, CategorieService categorie, Promotion promotion) {
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.prix = prix;
        this.categorie = categorie;
        this.promotion = promotion;
    }
    public Service(String nom, String description, int duree, int prix, CategorieService categorie) {
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.prix = prix;
        this.categorie = categorie;
    }
}
