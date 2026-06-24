package com.tools.document.excel.easypoi.basic.web.model.entity;

import lombok.Data;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 14:03
 */
@Data
public class House {
    private Integer userId;

    private String postalCode;

    private String city;

    private String street;

    private String houseName;

    private String roomNumber;
    private String houseRent;
    private String detailAddress;
    private String bedroomCount;
    private Integer toiletCount;
    private String furniture;
    private Integer sellingPoint;
    private Integer serviceAmount;
    private Integer energyLevel;
    List<String> pictureDataList;
    private String paths;


}
