package com.accendl.web.baiduMap;

import lombok.Data;

@Data
public class Address {
    private String address;
    private AddressDetail address_detail;
    private final Point point;

    @Data
    public static class AddressDetail {
        private String province;
        private String city;
        private String district;
        private String street;
        private String street_number;
        private String city_code;
        private String adcode;

    }

    @Data
    public static class Point {
        private double x;
        private double y;
    }
}
