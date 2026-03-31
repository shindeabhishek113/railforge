package com.railforge.trainservice.entity.model.entities;

import java.math.BigDecimal;
import java.util.List;

import com.railforge.trainservice.model.enums.CoachType;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coaches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @Column(nullable = false)
    private String coachNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CoachType coachType;

    @Column(nullable = false)
    private Integer totalBerths;

    @Column(nullable = false)
    private BigDecimal fareMultiplier;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<Berth> berths;
}