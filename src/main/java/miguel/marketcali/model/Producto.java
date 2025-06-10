package miguel.marketcali.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 50)
    private String marca;

    @Column(nullable = false)
    private double precio;

    private int cantidad;

    @Column(length = 50)
    private String categoria;

    // Required no-arg constructor
    public Producto() {
    }

    public Producto(String nombre, String marca, double precio, int cantidad, String categoria) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}