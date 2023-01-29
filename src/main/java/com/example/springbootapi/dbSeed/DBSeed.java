package com.example.springbootapi.dbSeed;

import com.example.springbootapi.item.Item;
import com.example.springbootapi.item.ItemRepository;
import com.example.springbootapi.store.Store;
import com.example.springbootapi.store.StoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DBSeed {

    @Bean
    CommandLineRunner commandLineRunner(ItemRepository itemRepository, StoreRepository storeRepository) {
        return args -> {
            Item laptopStandSilver = new Item("Laptop Stand", "silver", 39.99);
            Item laptopStandBlack = new Item("Laptop Stand", "black", 39.99);
            Item wirelessMouseBlack = new Item("Wireless Mouse", "black", 28.99);
            Item wirelessMouseWhite = new Item("Wireless Mouse", "white", 28.99);

            Store manchester = new Store("Manchester", "55-57 Bridge St, Manchester M3 3BQ");
            Store hull = new Store("Hull", "1-73 Humber St, Hull HU1 1TU");

            manchester.addItem(laptopStandBlack);
            manchester.addItem(wirelessMouseBlack);
            manchester.addItem(laptopStandSilver);
            hull.addItem(wirelessMouseWhite);
            hull.addItem(wirelessMouseBlack);
            hull.addItem(laptopStandSilver);


            itemRepository.saveAll(
                    List.of(laptopStandSilver, laptopStandBlack, wirelessMouseBlack, wirelessMouseWhite)
            );

            storeRepository.saveAll(
                    List.of(manchester, hull)
            );
        };

        
    }
}
