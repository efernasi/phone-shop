
package com.gnaderi.interview.phoneshop.outbound;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "ProductCatalog", description = "Product Catalog resource representation")
@JsonPropertyOrder(value = {"productCatalog"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCatalog implements Serializable {
    private static final long serialVersionUID = 6899822408140886331L;
    @JsonProperty("productCatalog")
    @ApiModelProperty(value = "A collection of the returned product details", required = true, dataType = "List")
    private List<ProductDetails> productCatalog;

    public ProductCatalog() {
    }

    public ProductCatalog(List<ProductDetails> productCatalog) {
        this.productCatalog = productCatalog;
    }

    public List<ProductDetails> getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(List<ProductDetails> productCatalog) {
        this.productCatalog = productCatalog;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
