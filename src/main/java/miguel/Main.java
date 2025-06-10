package miguel;

import miguel.marketcali.model.Producto;
import miguel.marketcali.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductoRepository repository) {
        return (args) -> {
            // Create product
            Producto nuevo = new Producto("Laptop", "Dell", 2500000, 10, "Tecnología");
            repository.save(nuevo);
            System.out.println("Producto creado");

            // Query product
            Producto consultado = repository.findById(1L).orElse(null);
            if (consultado != null) {
                System.out.println("Producto encontrado: " + consultado.getNombre());

                // Update product
                consultado.setPrecio(2300000);
                repository.save(consultado);
                System.out.println("Producto actualizado");

                // Delete product
                repository.deleteById(1L);
                System.out.println("Producto eliminado");
            } else {
                System.out.println("Producto no encontrado");
            }
        };
    }
}