package be.icc.tgh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int rating;
    @Column(columnDefinition = "LONGTEXT")
    @NotNull
    private String comment;
    @ManyToOne
    @NotNull
    private User user;
    @OneToOne
    private Reservation reservation;
}
