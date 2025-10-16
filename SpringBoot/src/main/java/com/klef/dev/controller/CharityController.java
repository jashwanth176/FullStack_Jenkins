package com.klef.dev.controller;

import com.klef.dev.entity.Charity;
import com.klef.dev.entity.Donation;
import com.klef.dev.service.CharityService;
import com.klef.dev.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/charities")
@CrossOrigin(origins = "*")
public class CharityController {

    @Autowired
    private CharityService charityService;

    @Autowired
    private DonationService donationService;

    @GetMapping("/")
    public String home() {
        return "Charity Fund Collection API";
    }

    // Charity CRUD
    @PostMapping
    public ResponseEntity<Charity> create(@RequestBody Charity charity) {
        return new ResponseEntity<>(charityService.create(charity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Charity>> list() {
        return new ResponseEntity<>(charityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Charity c = charityService.getById(id);
        if (c == null) return new ResponseEntity<>("Charity not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Charity charity) {
        Charity existing = charityService.getById(id);
        if (existing == null) return new ResponseEntity<>("Charity not found", HttpStatus.NOT_FOUND);
        charity.setId(id);
        return new ResponseEntity<>(charityService.update(charity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Charity existing = charityService.getById(id);
        if (existing == null) return new ResponseEntity<>("Charity not found", HttpStatus.NOT_FOUND);
        charityService.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    // Donations
    @PostMapping("/{charityId}/donations")
    public ResponseEntity<?> donate(@PathVariable Long charityId, @RequestBody Donation donation) {
        try {
            Donation saved = donationService.donate(charityId, donation);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{charityId}/donations")
    public ResponseEntity<List<Donation>> donations(@PathVariable Long charityId) {
        return new ResponseEntity<>(donationService.listByCharity(charityId), HttpStatus.OK);
    }
}
