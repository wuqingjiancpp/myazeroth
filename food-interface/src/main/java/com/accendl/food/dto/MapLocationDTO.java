package com.accendl.food.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class MapLocationDTO implements Serializable {
    private Integer id;

    private BigDecimal mercatorX;

    private BigDecimal mercatorY;
}
