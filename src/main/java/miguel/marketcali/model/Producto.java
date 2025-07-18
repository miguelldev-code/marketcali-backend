package miguel.marketcali.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Data // Genera getters, setters, toString, etc.
@Table(name = "productos")
public class Producto {

    // Getters and setters
    @Setter
    @Getter
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_barras", unique = true, length = 50)
    private String codigoBarras;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 50)
    private String marca;

    @Column(nullable = false)
    private BigDecimal precio;

    private int cantidad;

    @Column(length = 50)
    private String categoria;

    // FALTA IMPLEMENTAR ESTOS ATRIBUTOS AL SERVICE
    @Column(length = 500)
    private String descripcion;

    @Column(length = 255)
    private String imagen;

    // Constructor no-arg
    public Producto() {
    }

    // Constructor arg
    public Producto(String nombre, String marca, BigDecimal precio, int cantidad, String categoria) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

}