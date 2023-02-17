package com.accendl.myweibo.controller;

import com.accendl.myweibo.dto.Inbound;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/inbound")
public class InboundController {

    @GetMapping
    public String list() {
        return "inbound";
    }

    @ModelAttribute
    public List<Inbound> inboundList(){
        List<Inbound> inboundList = new ArrayList<>();
        for (int i=0;i<21;i++){
            Inbound inbound = new Inbound();
            inbound.setId(1);
            inbound.setSku("sku-1");
            inbound.setOwner("三星");
            inbound.setSkuName("平板电视机");
            inbound.setInboundQty(100);
            inbound.setCreateTime(Calendar.getInstance().getTime());
            inbound.setId(i+1);
            inboundList.add(inbound);
        }
//        return inboundList.stream().filter(item->(item.getId()==id)).toList();
        return  inboundList;
    }
}