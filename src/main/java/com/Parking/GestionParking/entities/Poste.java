package com.Parking.GestionParking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Poste  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idPoste ;
    private  String title;
    private  String description;
    private LocalDate date ;
    private  String userName;
    @Enumerated(EnumType.STRING)
    @OneToMany(mappedBy = "poste" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comments> comments;
    @Enumerated(EnumType.STRING)
    @ManyToOne
    private  User user ;
}
