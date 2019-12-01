package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = { DriverCarHave18AgeValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Have18Age {
    String message() default "Invalid value, Have18Age";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
