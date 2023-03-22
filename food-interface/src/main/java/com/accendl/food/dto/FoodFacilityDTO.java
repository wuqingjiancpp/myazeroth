package com.accendl.food.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuqingjian
 * @since 2023-03-18
 */
@Data
@Builder
public class FoodFacilityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer locationId;

    private String applicant;

    private String facilityType;

    private String cnn;

    private String locationDescription;

    private String address;

    private String blockLot;

    private String block;

    private String lot;

    private String permit;

    private String status;

    private String foodItems;

    private BigDecimal x;

    private BigDecimal y;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String schedule;

    private String dayshours;

    private Integer noiSent;

    private String approved;

    private String received;

    private Integer priorPermit;

    private String expirationDate;

    private String location;

    private Integer firePreventionDistricts;

    private Integer policeDistricts;

    private Integer supervisorDistricts;

    private String zipCode;

    private Integer neighborhoodsOld;


}
