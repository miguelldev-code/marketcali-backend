package miguel.marketcali.controller;

import miguel.marketcali.dto.ProductoDTO;
import miguel.marketcali.model.Producto;
import miguel.marketcali.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // GET: Listar todos (público)
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    // GET: Buscar por ID (público)
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    // POST: Crear (solo ADMIN)
    @PostMapping
    public ResponseEntity<Producto> crearProducto(
            @Valid @RequestBody ProductoDTO productoDTO) {
        return new ResponseEntity<>(
                productoService.crearProducto(productoDTO),
                HttpStatus.CREATED
        );
    }

    // PUT: Actualizar (solo ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(
                productoService.actualizarProducto(id, productoDTO)
        );
    }

    // DELETE: Eliminar (solo ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}