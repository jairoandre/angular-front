package br.com.netprecision.prova.app;

import br.com.netprecision.prova.app.entity.Product;
import br.com.netprecision.prova.app.repository.ProductRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

    @Bean
    ApplicationRunner init(ProductRepository productRepository) {
        return args ->
            Stream.of(
                createProduct("1147", "Cachorro quente", 3.00f),
                createProduct("1154", "Bauru", 2.50f),
                createProduct("1164", "Misto quente", 2.00f),
                createProduct("1155", "X-Burger", 6.00f)
            ).forEach(product -> productRepository.save(product));

    }

    private Product createProduct(String code, String name, Float price) {
        return Product.builder().code(code).name(name).price(price).build();
    }
}
