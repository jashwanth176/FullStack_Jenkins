package com.klef.dev.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "charity")
public class Charity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private Double goalAmount;

    @Column(nullable = false)
    private Double collectedAmount = 0.0;

    @Column(nullable = false, length = 255)
    private String contactEmail;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getGoalAmount() { return goalAmount; }
    public void setGoalAmount(Double goalAmount) { this.goalAmount = goalAmount; }

    public Double getCollectedAmount() { return collectedAmount; }
    public void setCollectedAmount(Double collectedAmount) { this.collectedAmount = collectedAmount; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
