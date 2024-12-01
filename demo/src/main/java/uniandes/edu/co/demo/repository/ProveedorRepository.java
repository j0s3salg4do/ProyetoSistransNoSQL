package main.java.uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.demo.modelo.Proveedor;

import java.util.List;

public interface ProveedorRepository extends MongoRepository<Proveedor, String> {

    // Consultar todos los proveedores
    @Query("{}")
    List<Proveedor> buscarTodosLosProveedores();

    // Consultar proveedor por su NIT
    @Query("{'nit': ?0}")
    Proveedor buscarPorNit(String nit);

    // Eliminar un proveedor por su ID
    void deleteById(String id);
}
