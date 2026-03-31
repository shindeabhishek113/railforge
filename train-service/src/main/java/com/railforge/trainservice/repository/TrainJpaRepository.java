package com.railforge.trainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.railforge.trainservice.entity.model.entities.Train;

public interface TrainJpaRepository extends JpaRepository<Train, Long> {

}
