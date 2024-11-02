package org.xproce.inventoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.inventoryservice.entities.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
