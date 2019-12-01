package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Product;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service("personService")
public class ProductService {

    private final PersonRepository personRepository;
    private final Validator validatorEntity;

    @Autowired
    public ProductService(final PersonRepository personRepository, final Validator validatorEntity) {
        this.personRepository = personRepository;
        this.validatorEntity = validatorEntity;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(){ personRepository.deleteAll(); }

    @Transactional(rollbackFor = Exception.class)
    public void saveProduct(
            Product product
    ) throws Exception {
        // VALIDATE DTO ------------------------------------
        Set<ConstraintViolation<Product>> validates = validatorEntity.validate(product);
        if(validates.size() > 0) {
            String errors = validates.stream().map(v -> v.getMessage())
                    .collect(Collectors.joining());//todo check for 2 and more
            throw new Exception(errors);
        }
        if ( personRepository.existsById(product.getId()) ) throw new Exception();
        //todo многопоточная коллизия на добавление(+ в SpringDataJpa есть только save, NO UPDATE)
        // SAVE ------------------------------------
        personRepository.save(product);//can be add:@Transactional(rollbackFor = Exception.class)
    }

    public Product getPerson(Long personid) {
        if ( ! personRepository.existsById(personid) )
            return null;
        else
            return personRepository.findById(personid).get();
    }

    public Optional<Product> findById(Long ownerId) {
        return personRepository.findById(ownerId);
    }

    public List<Product> findAll() {
        return personRepository.findAll();
    }

    public List<Product> saveAll(List<Product> products) {
        return personRepository.saveAll(products);
    }
}
