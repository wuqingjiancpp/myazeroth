package com.accendl.web.test;

import com.accendl.web.service.FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class TestFoodService {

    @Autowired
    private FoodService foodService;

    @Test
    public void testBatchConvert() throws Exception {
        boolean flag = foodService.batchConvertMapLocation();
        Assert.isTrue(flag, "转换失败");
    }
}
