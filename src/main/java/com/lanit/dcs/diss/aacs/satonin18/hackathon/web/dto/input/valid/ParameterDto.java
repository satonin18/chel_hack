package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.input.valid;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@ToString
public class ParameterDto {
//    без groups = {New.class}, чтоб код был чище
//    interface New {}

    //такой id не дожен лежать в бд //проверка в контроллере/сервисе
    @NotNull
    Long id;

    @NotNull
    @Pattern(regexp = "^[^-]{1,50}-.{1,50}$") //"^"=start  "$"=end  "[^-]"=любой_символ_кромеТИРЕ  "."=любой_символ  "+"=OneOrMore "{1,50}"-min=1,max=50
    String model;

    @NotNull
    @Positive
    Integer horsepower;

    //Product должен быть в бд, и быть старше 18 лет //проверка на уровне валидации Entity
    @NotNull
    Long ownerId;
}
