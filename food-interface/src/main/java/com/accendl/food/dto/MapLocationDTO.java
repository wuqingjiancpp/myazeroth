package com.accendl.food.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MapLocationDTO implements Serializable {
    private Integer id;

    private double mercatorX;

    private double mercatorY;
}
