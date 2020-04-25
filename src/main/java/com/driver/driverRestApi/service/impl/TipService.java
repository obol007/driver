package com.driver.driverRestApi.service.impl;

import com.driver.driverRestApi.dto.response.TipResponse;
import com.driver.driverRestApi.exception.ResourceNotFoundException;
import com.driver.driverRestApi.model.Tip;
import com.driver.driverRestApi.repository.impl.MySqlTipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TipService {

    MySqlTipRepository tipRepository;

    public TipService(MySqlTipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }


    public List<Tip> getTips() {
        return tipRepository.findAll();
    }

    public Tip findTip(Long id) {
        return tipRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("There is no tip with id: "+id));
    }

    public Tip createTip(Tip tip) {
       return tipRepository.save(tip);
    }
}
