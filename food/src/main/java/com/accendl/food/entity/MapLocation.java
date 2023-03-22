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
@Data
@TableName("map_location")
public class MapLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private double mercatorX;

    private double mercatorY;

    public MapLocation(Integer id, double mercatorX, double mercatorY) {
        this.id = id;
        this.mercatorX = mercatorX;
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
