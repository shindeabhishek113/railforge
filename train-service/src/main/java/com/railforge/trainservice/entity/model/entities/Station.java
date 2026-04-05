package com.railforge.trainservice.entity.model.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Station {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "station_sequence_id")
	@SequenceGenerator(name = "station_sequence_id", sequenceName = "station_sequence_id", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String stationCode;

    @Column(nullable = false)
    private String stationName;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @CreationTimestamp
    private LocalDateTime createdAt;
}