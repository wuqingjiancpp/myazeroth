package com.accendl.web.baiduMap;

import java.util.List;

/**
 * {
 *     "status": 0,
 *     "result": [
 *         {
 *             "x": -13160213.986434427,
 *             "y": 4081859.5950848239
 *         }
 *     ]
 * }
 *
 * @param status
 * @param message
 * @param result
 */
public record BaiduMapLocations (int status, String message, List<Address.Point> result){


}
