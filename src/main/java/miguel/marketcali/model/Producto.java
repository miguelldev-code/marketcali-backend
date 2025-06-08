package miguel.marketcali.model;

public class Producto {

    private int id;
    private String nombre;
    private String marcar;
    private double precio;
    private int cantidad;
    private String categoria;

    // constructor
    public Producto(String nombre, String marcar, double precio, int cantidad, String categoria) {
        this.nombre = nombre;
        this.marcar = marcar;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    // getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarcar() {
        return marcar;
    }

    public void setMarcar(String marcar) {
        this.marcar = marcar;
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
