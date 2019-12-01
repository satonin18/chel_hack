package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.shopserver;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class InfoShopServer {
    private String status;
    private String message;
    private List<Product> data;
    private Metadata metadata;
}

