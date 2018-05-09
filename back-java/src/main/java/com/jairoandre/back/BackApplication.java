package com.jairoandre.back;

import com.jairoandre.back.entity.ItemEntity;
import com.jairoandre.back.repository.ItemRepository;
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
    ApplicationRunner init(ItemRepository repository) {
        return args ->
            Stream.of("1", "2", "3").forEach(name -> {
                ItemEntity item = new ItemEntity();
                item.setName("Item " + name);
                item.setDetail("Detail " + name);
                repository.save(item);
            });
    }
}
