package com.example.springbootapi.item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ItemConfig {

    @Bean
    CommandLineRunner commandLineRunner(ItemRepository itemRepository) {
        return args -> {
            Item laptopStandSilver = new Item("Laptop Stand", "silver", 39.99, 3);
            Item laptopStandBlack = new Item("Laptop Stand", "black", 39.99, 1);
            Item wirelessMouseBlack = new Item("Wireless Mouse", "black", 28.99, 3);
            Item wirelessMouseWhite = new Item("Wireless Mouse", "black", 28.99, 0);

            itemRepository.saveAll(
                    List.of(laptopStandSilver, laptopStandBlack, wirelessMouseBlack, wirelessMouseWhite)
            );
        };

        
    }
}
