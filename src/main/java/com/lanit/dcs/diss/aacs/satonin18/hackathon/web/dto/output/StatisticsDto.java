package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDto {
    Long personcount;
    Long carcount;
    Long uniquevendorcount;
}
