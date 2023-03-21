package com.accendl.food.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuqingjian
 * @since 2023-03-18
 */
@TableName("food_facility")
public class FoodFacility implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getCnn() {
        return cnn;
    }

    public void setCnn(String cnn) {
        this.cnn = cnn;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlockLot() {
        return blockLot;
    }

    public void setBlockLot(String blockLot) {
        this.blockLot = blockLot;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(String foodItems) {
        this.foodItems = foodItems;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getDayshours() {
        return dayshours;
    }

    public void setDayshours(String dayshours) {
        this.dayshours = dayshours;
    }

    public Integer getNoiSent() {
        return noiSent;
    }

    public void setNoiSent(Integer noiSent) {
        this.noiSent = noiSent;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public Integer getPriorPermit() {
        return priorPermit;
    }

    public void setPriorPermit(Integer priorPermit) {
        this.priorPermit = priorPermit;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getFirePreventionDistricts() {
        return firePreventionDistricts;
    }

    public void setFirePreventionDistricts(Integer firePreventionDistricts) {
        this.firePreventionDistricts = firePreventionDistricts;
    }

    public Integer getPoliceDistricts() {
        return policeDistricts;
    }

    public void setPoliceDistricts(Integer policeDistricts) {
        this.policeDistricts = policeDistricts;
    }

    public Integer getSupervisorDistricts() {
        return supervisorDistricts;
    }

    public void setSupervisorDistricts(Integer supervisorDistricts) {
        this.supervisorDistricts = supervisorDistricts;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getNeighborhoodsOld() {
        return neighborhoodsOld;
    }

    public void setNeighborhoodsOld(Integer neighborhoodsOld) {
        this.neighborhoodsOld = neighborhoodsOld;
    }

    @Override
    public String toString() {
        return "FoodFacility{" +
        "id = " + id +
        ", locationId = " + locationId +
        ", applicant = " + applicant +
        ", facilityType = " + facilityType +
        ", cnn = " + cnn +
        ", locationDescription = " + locationDescription +
        ", address = " + address +
        ", blockLot = " + blockLot +
        ", block = " + block +
        ", lot = " + lot +
        ", permit = " + permit +
        ", status = " + status +
        ", foodItems = " + foodItems +
        ", x = " + x +
        ", y = " + y +
        ", latitude = " + latitude +
        ", longitude = " + longitude +
        ", schedule = " + schedule +
        ", dayshours = " + dayshours +
        ", noiSent = " + noiSent +
        ", approved = " + approved +
        ", received = " + received +
        ", priorPermit = " + priorPermit +
        ", expirationDate = " + expirationDate +
        ", location = " + location +
        ", firePreventionDistricts = " + firePreventionDistricts +
        ", policeDistricts = " + policeDistricts +
        ", supervisorDistricts = " + supervisorDistricts +
        ", zipCode = " + zipCode +
        ", neighborhoodsOld = " + neighborhoodsOld +
        "}";
    }
}
