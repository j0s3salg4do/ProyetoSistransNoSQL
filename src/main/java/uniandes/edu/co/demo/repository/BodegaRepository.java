package uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.demo.modelo.Bodega;

import java.util.List;

public interface BodegaRepository extends MongoRepository<Bodega, String> {

    // Consultar todas las bodegas
    @Query("{}")
    List<Bodega> buscarTodasLasBodegas();

    // Consultar bodega por su nombre
    @Query("{'nombre': ?0}")
    Bodega buscarPorNombre(String nombre);

    // Eliminar una bodega por su ID
    void deleteById(String id);
}
