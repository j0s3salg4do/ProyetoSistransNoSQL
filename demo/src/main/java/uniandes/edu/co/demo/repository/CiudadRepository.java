package uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.demo.modelo.Ciudad;

import java.util.List;

public interface CiudadRepository extends MongoRepository<Ciudad, String> {

    @Query("{}")
    List<Ciudad> buscarTodasLasCiudades();
    @Query("{'codigo': ?0}")
    Ciudad buscarPorCodigo(String codigo);
    void deleteById(String id);
}
