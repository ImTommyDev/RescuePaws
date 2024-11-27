package tommy.rescuePaws.Servicios;

import tommy.rescuePaws.Entidades.Animal;
import tommy.rescuePaws.Enums.Estado;
import tommy.rescuePaws.Entidades.Raza;

import java.util.List;

public interface InterfazAnimalServicio {

    // Crear o guardar un nuevo animal
    Animal guardarAnimal(Animal animal);

    // Listar todos los animales
    List<Animal> listarTodos();

    // Buscar animal por ID
    Animal buscarPorId(Integer id);

    // Buscar animales por estado (Ej: EN_REFUGIO, EN_ADOPCION, ADOPTADO)
    List<Animal> buscarPorEstado(Estado estado);

    // Buscar animales por ID raza
    List<Animal> buscarPorIdRaza(Integer idRaza);

    // Buscar animales por nombre raza
    List<Animal> buscarPorNombreRaza(String nombreRaza);

    // Buscar animales por especie
    List<Animal> buscarPorEspecie(String especie);

    // Actualizar datos de un animal
    Animal actualizarAnimal(Integer id, Animal animal);

    // Eliminar un animal por su ID
    void eliminarPorId(Integer id);

    // Buscar por nombre del animal (similar a búsqueda por texto)
    List<Animal> buscarPorNombre(String nombre);

    // Buscar por rango de edad
    List<Animal> buscarPorRangoDeEdad(Integer edadMinima, Integer edadMaxima);

    // Buscar animales que llevan cierto tiempo en el refugio (por fecha de ingreso)
    List<Animal> buscarPorTiempoEnRefugio(Long diasMinimos);
}
