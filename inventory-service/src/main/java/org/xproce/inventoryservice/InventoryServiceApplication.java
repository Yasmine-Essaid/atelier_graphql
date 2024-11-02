package org.xproce.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.xproce.inventoryservice.entities.Creator;
import org.xproce.inventoryservice.entities.Video;
import org.xproce.inventoryservice.repositories.CreatorRepository;
import org.xproce.inventoryservice.repositories.VideoRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository) {
        return args -> {
            // Création de quelques créateurs
            List<Creator> creators = List.of(
                    Creator.builder().id(1L).name("John Doe").email("john@example.com").build(),
                    Creator.builder().id(2L).name("Jane Smith").email("jane@example.com").build()
            );
            creatorRepository.saveAll(creators);

            // Création de quelques vidéos associées aux créateurs
            List<Video> videos = List.of(
                    Video.builder().id(1L).name("Video 1").url("http://example.com/video1")
                            .description("First video").datePublication(new Date())
                            .creator(creators.get(0)).build(),
                    Video.builder().id(2L).name("Video 2").url("http://example.com/video2")
                            .description("Second video").datePublication(new Date())
                            .creator(creators.get(1)).build()
            );
            videoRepository.saveAll(videos);
        };
    }
}
