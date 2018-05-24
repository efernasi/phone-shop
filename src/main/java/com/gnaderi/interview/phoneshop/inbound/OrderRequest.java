package com.gnaderi.interview.phoneshop.inbound;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "Create Purchase OrderRequest", description = "OrderRequest Request resource representation")
@JsonIgnoreProperties(ignoreUnknown = false)
@JsonPropertyOrder(value = {"items"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRequest implements Serializable {
    private static final long serialVersionUID = 6893422408140886331L;
    @JsonProperty("items")
    @ApiModelProperty(value = "A collection of selected product Id's", required = true, dataType = "List")
    private List<Integer> items;

    public OrderRequest() {
    }

    public OrderRequest(List<Integer> items) {
        this.items = items;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
