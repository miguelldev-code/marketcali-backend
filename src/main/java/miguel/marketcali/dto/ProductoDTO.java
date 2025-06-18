package miguel.marketcali.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductoDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @Size(max = 50, message = "La marca no puede exceder 50 caracteres")
    private String marca;

    @DecimalMin(value = "0.01", message = "El precio mínimo es 0.01")
    private double precio;

    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private int cantidad;

    @Size(max = 50, message = "La categoría no puede exceder 50 caracteres")
    private String categoria;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @Size(max = 255, message = "La URL de la imagen no puede exceder 255 caracteres")
    private String imagen;
}