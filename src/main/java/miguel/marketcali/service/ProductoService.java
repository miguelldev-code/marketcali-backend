package miguel.marketcali.service;

import miguel.marketcali.dto.ProductoDTO;
import miguel.marketcali.model.Producto;
import miguel.marketcali.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Listar productos
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    // Buscar por ID
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // Crear producto
    @Transactional
    public Producto crearProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        mapearDTOaProducto(productoDTO, producto);
        return productoRepository.save(producto);
    }

    // Actualizar producto
    @Transactional
    public Producto actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto producto = buscarPorId(id);
        mapearDTOaProducto(productoDTO, producto);
        return productoRepository.save(producto);
    }

    // Eliminar producto
    @Transactional
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // Listar categorías únicas
    @Transactional(readOnly = true)
    public List<String> listarCategoriasUnicas() {
        return productoRepository.findDistinctCategorias();
    }

    // Listar marcas únicas
    @Transactional(readOnly = true)
    public List<String> listarMarcasUnicas() {
        return productoRepository.findDistinctMarcas();
    }

    // Mapear DTO a Entidad
    private void mapearDTOaProducto(ProductoDTO dto, Producto producto) {
        producto.setNombre(dto.getNombre());
        producto.setMarca(dto.getMarca());
        producto.setPrecio(dto.getPrecio());
        producto.setCantidad(dto.getCantidad());
        producto.setCategoria(dto.getCategoria());
        producto.setDescripcion(dto.getDescripcion());
        producto.setImagen(dto.getImagen());
    }
}