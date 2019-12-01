package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
//import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.valid.NotExistCarWithTheId;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.valid.Have18Age;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor

@Entity
@Table(name = "parameters")

//@JsonIgnoreProperties({""})
public class Parameter {



//    @NotExistCarWithTheId //todo не рекомендуют делать запрос в бд через анотацию, или как сделать её на https://habr.com/ru/post/424819/
    @NotNull

    @Id
    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;



    @NotNull
//    @Pattern(regexp = "^[^-]{1,50}$") //"^"=start  "$"=end  "[^-]"=любой_символ_кромеТИРЕ  "."=любой_символ  "+"=OneOrMore "{1,50}"-min=1,max=50



    @Column(name = "title", nullable = false, length = 50)
    String title;



    @NotNull
//    @Size(min = 1, max = 50)

//    @JsonIgnore

    @Column(name = "value", nullable = false, length = 50)
    String value;



//    @JsonProperty("model")
//    String getModel(){
//        return String.join("-",vendor, model);
//    }



//    @NotNull
//    @Positive
//
//    @Column(name = "horsepower", nullable = false)
//    Integer horsepower;



//    @Have18Age //include test on //@NotNull

    @JsonIgnore

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;



//    @Column(name = "owner_id", insertable = false, updatable = false, nullable = false
//    Long ownerId;
//    @JsonProperty("ownerId")
//    Long getOwnerId(){
//        return product.getId();
//    }
}