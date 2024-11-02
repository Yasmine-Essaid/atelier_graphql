package org.xproce.inventoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.inventoryservice.entities.Creator;

public interface CreatorRepository extends JpaRepository<Creator, Long> {
}
