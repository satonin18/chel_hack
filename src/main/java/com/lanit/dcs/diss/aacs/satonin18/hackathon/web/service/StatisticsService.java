package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.output.StatisticsDto;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.repository.CarRepository;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("statisticsService")
public class StatisticsService {

    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private CarRepository carRepository;

    public StatisticsDto getStatisticsDto() throws Exception {
        Long personcount = personRepository.count();
        Long carcount = carRepository.count();
//        Long uniquevendorcount = carRepository.countDistinctVendorIgnorCase();

        if(personcount == null || carcount == null
//                || uniquevendorcount == null
        )
            throw new Exception();

        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setPersoncount(personcount);
        statisticsDto.setCarcount(carcount);
//        statisticsDto.setUniquevendorcount(uniquevendorcount);
        return statisticsDto;
    }
}
