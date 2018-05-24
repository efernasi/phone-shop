
package com.gnaderi.interview.phoneshop.outbound;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gnaderi.interview.phoneshop.entity.Product;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@ApiModel(value = "Item", description = "Item resource representation")
@JsonPropertyOrder(value = {"id","productName", "desc", "category", "price", "quantity"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDetails implements Serializable {
    private static final long serialVersionUID = 6899822408140886331L;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("category")
    private String category;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("quantity")
    private Integer quantity;

    public ProductDetails() {

    }
    public ProductDetails(Product product) {
        this.id=product.getId();
        this.productName = product.getName();
        this.desc = product.getDesc();
        this.category = product.getProductType().getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }
    public ProductDetails(String productName, String desc, String category, Double price, Integer quantity) {
        this.productName = productName;
        this.desc = desc;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDetails(Integer id, String productName, String desc, String category, Double price, Integer quantity) {
        this.id=id;
        this.productName = productName;
        this.desc = desc;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
