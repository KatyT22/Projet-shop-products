package com.alten.shop.api.service;

import com.alten.shop.api.model.Operator;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OperatorService {
    public Operator getOperatorById(Long id);
    public List<Operator> getAllOperators();
    public void saveOperator(Operator operator);

}
