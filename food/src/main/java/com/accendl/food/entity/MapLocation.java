package com.accendl.food.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuqingjian
 * @since 2023-03-22
 */
@TableName("map_location")
public class MapLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private BigDecimal mercatorX;

    private BigDecimal mercatorY;

    public MapLocation(Integer id, BigDecimal mercatorX, BigDecimal mercatorY) {
        this.id = id;
        this.mercatorX = mercatorX;
        this.mercatorY = mercatorY;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMercatorX() {
        return mercatorX;
    }

    public void setMercatorX(BigDecimal mercatorX) {
        this.mercatorX = mercatorX;
    }

    public BigDecimal getMercatorY() {
        return mercatorY;
    }

    public void setMercatorY(BigDecimal mercatorY) {
        this.mercatorY = mercatorY;
    }

    @Override
    public String toString() {
        return "MapLocation{" +
        "id = " + id +
        ", mercatorX = " + mercatorX +
        ", mercatorY = " + mercatorY +
        "}";
    }
}
