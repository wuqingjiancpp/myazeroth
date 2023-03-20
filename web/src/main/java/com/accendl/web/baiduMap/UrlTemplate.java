package com.accendl.web.baiduMap;

/**
 * https://lbsyun.baidu.com/index.php?title=webapi/ip-api
 */
public class UrlTemplate {

    /**
     * https://api.map.baidu.com/location/ip?ak=HBYf9QdcggsBd3hrsznVbYE6FpjvpuxT&ip=101.224.96.58&coor=bd09ll
     */
    public static final String GET_LOCATION_BY_IP = "https://api.map.baidu.com/location/ip?" +
            "ak="+Constants.AK+"&ip=${ip}&";

    /**
     * https://api.map.baidu.com/geoconv/v1/?coords=114.21892734521,29.575429778924&from=1&to=6&ak=HBYf9QdcggsBd3hrsznVbYE6FpjvpuxT
     */
    public static final String CONVERT_LOCATION = "https://api.map.baidu.com/geoconv/v1/?coords=${coords}&" +
            "from=1&to=6&ak="+Constants.AK;

}
