package com.accendl.web.baiduMap;

import com.accendl.web.dto.MapLocation;
import com.accendl.web.http.snoop.HttpSnoopClient;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.type.SimpleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WebServiceApi {

    private final HttpSnoopClient httpSnoopClient;

    public WebServiceApi(HttpSnoopClient httpSnoopClient) {
        this.httpSnoopClient = httpSnoopClient;
    }

    /**
     * https://api.map.baidu.com/location/ip?ak=HBYf9QdcggsBd3hrsznVbYE6FpjvpuxT&ip=101.224.96.58&coor=bd09ll
     */
    public MapLocation getLocationByIp(String ip) throws Exception{
        String url = UrlTemplate.GET_LOCATION_BY_IP.replace("${ip}", ip);
        String httpContent = httpSnoopClient.getHttpContent(url);
        BaiduMapLocation baiduMapLocation = JSON.parseObject(httpContent, BaiduMapLocation.class);
        if (baiduMapLocation.status() == 0){
            return MapLocation.builder()
                    .address(baiduMapLocation.address())
                    .mercatorX(baiduMapLocation.content().getPoint().getX())
                    .mercatorY(baiduMapLocation.content().getPoint().getY())
                    .build();
        }else{
            log.error(baiduMapLocation.toString());
            return null;
        }
    }

    /**
     * https://api.map.baidu.com/geoconv/v1/?coords=114.21892734521,29.575429778924&from=1&to=6&ak=HBYf9QdcggsBd3hrsznVbYE6FpjvpuxT
     */
    public List<MapLocation> convertGpsToBaiduLocation(String coords) throws Exception{
        String url = UrlTemplate.CONVERT_LOCATION.replace("${coords}", coords);
        String httpContent = httpSnoopClient.getHttpContent(url);
        BaiduMapLocations baiduMapLocations = JSON.parseObject(httpContent, BaiduMapLocations.class);
        if (baiduMapLocations.status()==0){
            List<MapLocation> mapLocationList = new ArrayList<>();
            for (Address.Point point : baiduMapLocations.result()){
                mapLocationList.add(MapLocation.builder().mercatorX(point.getX()).mercatorY(point.getY()).build());
            }
            return mapLocationList;
        }else{
            log.error(baiduMapLocations.toString());
            return null;
        }
    }

}
