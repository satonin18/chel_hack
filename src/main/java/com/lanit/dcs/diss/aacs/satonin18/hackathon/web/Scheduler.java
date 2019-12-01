package com.lanit.dcs.diss.aacs.satonin18.hackathon.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.shopserver.InfoShopServer;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.ParameterService;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.ProductService;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.Validator;

@Component
public class Scheduler {
    private final ProductService productService;
    private final ParameterService parameterService;
    private final StatisticsService statisticsService;
    private final Validator validatorDto;

    @Autowired
    public Scheduler(
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

    @Scheduled(fixedRate = 5000)
    public void scheduleTaskWithFixedRate() {
        int countTryIfServerBusy = 0;
        while (countTryIfServerBusy < 3) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                String json = restTemplate.getForObject("http://chelhack.deletestaging.com/goods", String.class);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                InfoShopServer infoShopServer = objectMapper.readValue(json, InfoShopServer.class);

                if (infoShopServer.getStatus().equals("Success")) {
                    System.out.println(infoShopServer.getStatus());

                    productService.deleteAll();
                    for(int i = 0; i < 150; i++){
                        productService.saveAll(infoShopServer.getData());
                    }

                    System.out.println("OK");

                } else {
                    System.out.println("error");
                }

                System.out.println(countTryIfServerBusy);
                countTryIfServerBusy = 999;
            } catch (Exception e) {
                System.out.println("---500 error---");
                System.out.println(e);
                System.out.println(countTryIfServerBusy);
                countTryIfServerBusy++;
            }
        }

    }
}
