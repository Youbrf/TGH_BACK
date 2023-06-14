package be.icc.tgh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;

    private Integer duree;

    private double prix;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private CategorieService categorie;

    public Service(Object id, String nom, int duree, int prix, CategorieService categorie) {
        this.nom=nom;
        this.duree=duree;
        this.prix=prix;
        this.categorie=categorie;
    }
}
