package com.accendl.web.dto;

import java.io.Serializable;
import java.util.Date;

public class Inbound implements Serializable {
    private int id;
    private String sku;
    private String owner;
    private String skuName;
    private int inboundQty;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public int getInboundQty() {
        return inboundQty;
    }

    public void setInboundQty(int inboundQty) {
        this.inboundQty = inboundQty;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
