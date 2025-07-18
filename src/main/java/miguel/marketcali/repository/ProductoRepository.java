package miguel.marketcali.repository;

import miguel.marketcali.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByCodigoBarras(String codigoBarras);

    // Añade este método
    boolean existsByCodigoBarras(String codigoBarras);
}