package com.railforge.trainservice.entity.model.entitie;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.railforge.trainservice.model.enums.DayOfWeek;
import com.railforge.trainservice.model.enums.TrainStatus;
import com.railforge.trainservice.model.enums.TrainType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trains")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String trainNumber;

    @Column(nullable = false)
    private String trainName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrainType trainType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrainStatus status;

    @Column(nullable = false)
    private String originStation;

    @Column(nullable = false)
    private String destinationStation;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "train_running_days",
            joinColumns = @JoinColumn(name = "train_id"))
    @Column(name = "day")
    private List<DayOfWeek> runningDays;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<Coach> coaches;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}