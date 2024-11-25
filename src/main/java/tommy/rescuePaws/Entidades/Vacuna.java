package tommy.rescuePaws.Entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVacuna;

    private String nombreVacuna; // Ejemplo: "Rabia", "Moquillo", etc.
}