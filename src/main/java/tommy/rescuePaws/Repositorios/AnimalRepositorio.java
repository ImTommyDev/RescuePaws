package tommy.rescuePaws.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import tommy.rescuePaws.Entidades.Animal;
import tommy.rescuePaws.Entidades.Raza;
import tommy.rescuePaws.Enums.Estado;

import java.time.LocalDate;
import java.util.List;

//Capa de acceso a datos
public interface AnimalRepositorio extends JpaRepository<Animal, Integer> {
    // Buscar animales por estado
    List<Animal> findByEstado(Estado estado);

    // Buscar animales por raza
    List<Animal> findByRaza(Raza raza);

    // Buscar animales directamente por ID de la raza
    List<Animal> findByRazaId(Integer idRaza);

    // Buscar animales por el nombre de la raza
    List<Animal> findByRazaNombreIgnoreCase(String nombre);

    // Buscar animales por nombre de especie (IgnoreCase para no tener en cuenta las mayúsculas o minúsculas)
    List<Animal> findByEspecieNombreIgnoreCase(String nombre);

    // Buscar animales por nombre
    List<Animal> findByNombreContainingIgnoreCase(String nombre);

    // Buscar animales por rango de edad
    List<Animal> findByEdadBetween(Integer edadMinima, Integer edadMaxima);

    // Buscar animales por fecha de ingreso antes de una fecha específica
    //La consulta que ará sería: SELECT * FROM tabla WHERE fecha_ingreso < ?
    List<Animal> findByFechaIngresoBefore(LocalDate fechaLimite);
}
