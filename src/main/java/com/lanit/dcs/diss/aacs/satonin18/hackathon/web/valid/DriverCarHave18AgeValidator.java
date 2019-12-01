package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.valid;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Product;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class DriverCarHave18AgeValidator implements ConstraintValidator<Have18Age, Product> {

    private static final int ADULT_AGE = 18;

    @Override
    public void initialize(Have18Age have18Age) {

    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
//        if (product != null) {
//            LocalDate date = product.getBirthdate();
//            return (Period.between(date, LocalDate.now())).getYears() >= ADULT_AGE;
//        } else {
            return false;
//        }
    }
}