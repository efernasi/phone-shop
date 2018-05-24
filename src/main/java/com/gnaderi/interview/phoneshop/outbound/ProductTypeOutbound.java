
package com.gnaderi.interview.phoneshop.outbound;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gnaderi.interview.phoneshop.entity.ProductType;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Optional;

@ApiModel(value = "Item", description = "Item resource representation")
@JsonPropertyOrder(value = {"id", "typeName", "desc"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductTypeOutbound implements Serializable {
    private static final long serialVersionUID = 6899822408140886331L;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("typeName")
    private String typeName;
    @JsonProperty("desc")
    private String desc;


    public ProductTypeOutbound() {
    }

    public ProductTypeOutbound(ProductType productType) {
        this.id = productType.getId();
        this.typeName = productType.getName();
        this.desc = productType.getDesc();
    }

    public ProductTypeOutbound(String typeName, String desc) {
        this.typeName = typeName;
        this.desc = desc;
    }

    public ProductTypeOutbound(Integer id, String typeName, String desc) {
        this.id = id;
        this.typeName = typeName;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
