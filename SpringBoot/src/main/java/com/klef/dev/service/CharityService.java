package com.klef.dev.service;

import com.klef.dev.entity.Charity;
import java.util.List;

public interface CharityService {
    Charity create(Charity charity);
    Charity update(Charity charity);
    void delete(Long id);
    Charity getById(Long id);
    List<Charity> getAll();
}
