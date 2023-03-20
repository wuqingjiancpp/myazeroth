package com.accendl.web.baiduMap;

import lombok.Data;

import java.util.List;

/**
 * {
 *     "address": "CN|上海市|上海市|None|None|100|100",
 *     "content": {
 *         "address_detail": {
 *             "province": "上海市",
 *             "city": "上海市",
 *             "district": "",
 *             "street": "",
 *             "street_number": "",
 *             "city_code": 289,
 *             "adcode": "310100"
 *         },
 *         "address": "上海市",
 *         "point": {
 *             "x": "13523298.87",
 *             "y": "3641065.98"
 *         }
 *     },
 *     "status": 0
 * }
 */
public record BaiduMapLocation(int status, String message, String address, Address content) {

}
