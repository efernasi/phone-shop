package com.gnaderi.interview.phoneshop.outbound;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Transient;
import java.io.Serializable;

@ApiModel(value = "Item", description = "Item resource representation")
@JsonPropertyOrder(value = {"rowNumber", "product", "desc", "category", "amount", "tax", "net", "total"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable {
    private static final long serialVersionUID = 6899822408140886331L;
    @JsonProperty("rowNumber")
    private int rowNumber;
    @JsonProperty("product")
    private String productName;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("productTypeId")
    private Integer productTypeId;
    @JsonProperty("category")
    private String productType;
    @JsonProperty("amount")
    private Double amount = 0.0;
    @JsonProperty("tax")
    private Double tax = 0.0;
    @JsonProperty("total")
    private Double total = 0.0;

    public Item() {
    }

    public Item(Integer rowNumber, String productName, String desc, Integer productTypeId, String productType, Double amount) {
        this.rowNumber = rowNumber;
        this.productName = productName;
        this.desc = desc;
        this.productTypeId = productTypeId;
        this.productType = productType;
        this.amount = amount;
    }

    public Item(int rowNumber, String productName, String desc, Integer productTypeId, String productType, Double amount, Double tax, Double total) {
        this.rowNumber = rowNumber;
        this.productName = productName;
        this.desc = desc;
        this.productTypeId = productTypeId;
        this.productType = productType;
        this.amount = amount;
        this.tax = tax;
        this.total = total;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.JSON_STYLE);
        return ReflectionToStringBuilder.toStringExclude(this, "productTypeId");
//        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
