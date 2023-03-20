package com.accendl.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MapLocation {

    private String address;
    private final double mercatorX;
    private final double mercatorY;

}
