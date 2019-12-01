package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.input.valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.helper.PropertiesApp;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
public class ProductDto {
//    без groups = {New.class}, чтоб код был чище
//    interface New {
//    }



    //такой id не дожен лежать в бд //проверка в контроллере/сервисе
    @NotNull
    Long id;



    @NotNull
    @Size(max = 100)
    String name;



    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PropertiesApp.DATA_FORMAT_BIRTHDATE)
    @JsonDeserialize(using = LocalDateDeserializer.class)

    @NotNull //can be remove
    @Past

    LocalDate birthdate;

}
