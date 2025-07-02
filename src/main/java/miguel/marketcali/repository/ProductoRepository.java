package miguel.marketcali.repository;

import miguel.marketcali.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // NO IMPLEMENTADO EN REACT
    // Filtros de categorias y marcas
    @Query("SELECT DISTINCT p.categoria FROM Producto p WHERE p.categoria IS NOT NULL")
    List<String> findDistinctCategorias();

    @Query("SELECT DISTINCT p.marca FROM Producto p WHERE p.marca IS NOT NULL")
    List<String> findDistinctMarcas();
}