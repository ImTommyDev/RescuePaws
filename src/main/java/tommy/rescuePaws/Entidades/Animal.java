package tommy.rescuePaws.Entidades;

import jakarta.persistence.*;
import lombok.*;
import tommy.rescuePaws.Enums.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnimal;

    private String nombre;
    private Integer edad;
    private String color;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @ManyToOne
    private Especie especie; // Relación con la entidad Especie

    @ManyToOne
    private Raza raza; // Relación con la entidad Raza

    @Enumerated(EnumType.STRING)
    private Tamano tamano; // Enum

    @Enumerated(EnumType.STRING)
    private Comportamiento comportamiento; // Enum

    private LocalDate fechaIngreso;
    private String historiaRescate;

    @Enumerated(EnumType.STRING)
    private Estado estado; // Enum

    private Boolean esterilizado;
    private LocalDate fechaEsterilizacion;

    @ManyToMany
    @JoinTable(
            name = "animal_vacunas",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "vacuna_id")
    )
    private List<Vacuna> vacunas; // Relación con la entidad Vacuna

    private String historialMedico;
    private Double peso;

    @ElementCollection
    @CollectionTable(name = "animal_fotos", joinColumns = @JoinColumn(name = "animal_id"))
    @Column(name = "foto")
    private List<byte[]> fotos; // Almaceno directamente las fotos como bytes, en el caos de que
    //fuese una aplicación más grande debería implementar una funcionalidad de almacemar las fotos en
    //un servicio externo (como AWS S3, Firebase o algún servidor propio) y me guardaría la url de cada foto
}
