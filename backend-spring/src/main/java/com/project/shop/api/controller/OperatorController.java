package com.alten.shop.api.controller;

import com.alten.shop.api.model.Operator;
import com.alten.shop.api.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class OperatorController {
    @Autowired
    OperatorService operatorService;

    @GetMapping("/operators")
    public ResponseEntity<List<Operator>> getAllOperators(){
        try {
            List<Operator> operators = operatorService.getAllOperators();
            if(!operators.isEmpty()) {
                return new ResponseEntity<>(operators, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/operator/{id}")
    public ResponseEntity<Operator> getOperatorById(@PathVariable("id") Long id){
        try {
            Operator operator = operatorService.getOperatorById(id);
            if(operator != null){
                return new ResponseEntity<>(operator, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/operator/new")
    public ResponseEntity<Operator> createNewOperator(@RequestBody Operator operator){
        try{
            operatorService.saveOperator(operator);
            return new ResponseEntity<>(operator, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
