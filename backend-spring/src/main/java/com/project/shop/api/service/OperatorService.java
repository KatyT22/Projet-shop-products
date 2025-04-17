package com.project.shop.api.service;

import com.project.shop.api.model.Operator;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OperatorService {
    public Operator getOperatorById(Long id);
    public List<Operator> getAllOperators();
    public void saveOperator(Operator operator);

}
