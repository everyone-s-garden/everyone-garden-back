package com.everyonegarden.garden.model;

import com.everyonegarden.garden.model.GardenPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenPostRepository extends JpaRepository<GardenPost, Long> {}