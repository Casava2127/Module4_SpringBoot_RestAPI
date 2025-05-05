package com.ac.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Event_Scores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EventScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long scoreId;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "min_score", nullable = false)
    private int minScore;

    @Column(name = "max_score", nullable = false)
    private int maxScore;
}
