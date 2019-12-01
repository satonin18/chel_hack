package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.controller.rest_service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.output.StatisticsDto;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Product;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.ParameterService;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.ProductService;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.List;

@RestController //todo check binding with other anotations (=analog /*@ResponseBody*/, but not @RequestBody)
@Controller("restServiceController")
public class RestServiceController {

	private final ProductService productService;
	private final ParameterService parameterService;
	private final StatisticsService statisticsService;
	private final Validator validatorDto;

	@Autowired
	public RestServiceController(
			final ProductService productService,
			final ParameterService parameterService,
			final StatisticsService statisticsService,
			final Validator validatorDto
	) {
		this.productService = productService;
		this.parameterService = parameterService;
		this.statisticsService = statisticsService;
		this.validatorDto = validatorDto;
	}

//	@RequestMapping(value = "/product", method = RequestMethod.POST)
//	public ResponseEntity save_person(
//			@Valid @RequestBody ProductDto shopserver,
//			BindingResult bindingResult
//	) {
//		try {
//			// VALIDATE DTO -----------------------------------------------------
//			if (bindingResult.hasErrors()) throw new Exception();
//			// MAPPING -----------------------------------------------------
//			Product product = new Product();
//			ModelMapper mapper = new ModelMapper();
//			mapper.map(shopserver, product);
//			// ----------------------------------------------------------------------
//			productService.saveProduct(product); // -> validation entity
//
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e){
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	}
//
//	@RequestMapping(value = "/car", method = RequestMethod.POST)
//	public ResponseEntity save_car(
//			@Valid @RequestBody ParameterDto shopserver,
//			BindingResult bindingResult
//	) {
//		try {
//			// VALIDATE DTO ----------------------------------------------------------------------
//			if (bindingResult.hasErrors()) throw new Exception();
//			// MAPPING todo replace on custom Mapper -----------------------------------------------
//			Parameter car = new Parameter();
//			car.setId(shopserver.getId());
//			car.setHorsepower(shopserver.getHorsepower());
//			String[] fullName = shopserver.getModel().split("-",2);
//			car.setVendor(fullName[0]);
//			car.setModel(fullName[1]);
//			Product ownerPerson = productService.findById(shopserver.getOwnerId()).orElseThrow( () -> new Exception() );
//			car.setProduct(ownerPerson);
//			// VALIDATE ENTITY -----------------------------------------------------------------
//			parameterService.saveCar(car); // -> validation entity
//
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e){
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	}

	//todo can return PersonWithCarsDto from mapping
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ResponseEntity<Product> get_personwithcars(
			Long personid
	) {
		try {
	        if(personid == null) throw new Exception();

			Product product = productService.getPerson(personid);

			if(product == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<Product>(
						product,
					HttpStatus.OK
				);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	//todo can return PersonWithCarsDto from mapping
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> get_personwithcars() {
		try {
			List<Product> products = productService.findAll();

			if(products.size() == 0)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<List<Product>>(
						products,
					HttpStatus.OK
				);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ResponseEntity<StatisticsDto> statistics() {
		try {
			StatisticsDto statisticsDto = statisticsService.getStatisticsDto();

			return new ResponseEntity<StatisticsDto>(
					statisticsDto,
					HttpStatus.OK
			);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.OK);
		}
	}

	//todo no rest, GET-reguest = edit state
	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public ResponseEntity clear() {
		try {
			parameterService.deleteAll();
			productService.deleteAll();
		} finally {
			return new ResponseEntity(HttpStatus.OK);
		}
	}

}