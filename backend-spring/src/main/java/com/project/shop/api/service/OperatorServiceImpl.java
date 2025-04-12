package com.alten.shop.api.service;

import com.alten.shop.api.model.Operator;
import com.alten.shop.api.repository.OperatorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperatorServiceImpl implements OperatorService{

    @Autowired
    OperatorRepository operatorRepository;

    @Override
    public Operator getOperatorById(Long id) {
        Optional<Operator> operator = operatorRepository.findById(id);
        return operator.orElse(null);
    }

    @Override
    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }

    @Override
    public void saveOperator(Operator operator) {
        operatorRepository.save(operator);
    }
}
