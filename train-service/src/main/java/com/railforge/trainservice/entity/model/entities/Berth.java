package com.railforge.trainservice.entity.model.entities;

import com.railforge.trainservice.model.enums.BerthType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "berths")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Berth {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "berth_sequence_id")
	@SequenceGenerator(name = "berth_sequence_id", sequenceName = "berth_sequence_id", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;

    @Column(nullable = false)
    private String berthNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BerthType berthType;
}
