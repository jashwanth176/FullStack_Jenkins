package com.klef.dev.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "charity_id")
    private Charity charity;

    @Column(nullable = false, length = 120)
    private String donorName;

    @Column(nullable = false, length = 200)
    private String donorEmail;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime donatedAt = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Charity getCharity() { return charity; }
    public void setCharity(Charity charity) { this.charity = charity; }

    public String getDonorName() { return donorName; }
    public void setDonorName(String donorName) { this.donorName = donorName; }

    public String getDonorEmail() { return donorEmail; }
    public void setDonorEmail(String donorEmail) { this.donorEmail = donorEmail; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDateTime getDonatedAt() { return donatedAt; }
    public void setDonatedAt(LocalDateTime donatedAt) { this.donatedAt = donatedAt; }
}
