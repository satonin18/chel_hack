package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Parameter;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Service("carService")
public class ParameterService {

    private final CarRepository carRepository;
    private final Validator validatorEntity;

    @Autowired
    public ParameterService(final CarRepository carRepository, final Validator validatorEntity){
        this.carRepository = carRepository;
        this.validatorEntity = validatorEntity;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAll() {
        carRepository.deleteAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCar(
             Parameter parameter
    ) throws Exception {
        if ( carRepository.existsById(parameter.getId()) ) throw new Exception();
        //(else)todo многопоточная коллиция на добавление(т.к. в SpringDataJpa есть только save NO UPDATE)

        Set<ConstraintViolation<Parameter>> validates = validatorEntity.validate(parameter);
        if(validates.size() > 0) {
            String errors = validates.stream().map(v -> v.getMessage())
                    .collect(Collectors.joining());//todo check for 2 and more
            throw new Exception(errors);
        }
        // SAVE ---------------------------------------------------------------------------------------
        carRepository.save(parameter);//can be add:@Transactional(rollbackFor = Exception.class)
    }
}
