package com.everyonegarden.garden;

import com.everyonegarden.garden.model.GardenImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenImageRepository extends JpaRepository<GardenImage, Long> {}