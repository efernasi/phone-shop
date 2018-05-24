package com.gnaderi.interview.phoneshop.outbound;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value = "invoice", description = "Invoice resource representation")
@JsonPropertyOrder(value = {"InvoiceNumber", "date", "dueDate", "amount", "tax", "total", "items"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Invoice implements Serializable {
    private static final long serialVersionUID = 6899822408140886331L;
    @JsonProperty("InvoiceNumber")
    private Integer number;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("dueDate")
    private LocalDate dueDate;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("tax")
    private Double tax=0.0;
    @JsonProperty("total")
    private Double total=0.0;
    @JsonProperty("items")
    @ApiModelProperty(value = "A collection of the returned items details", required = true, dataType = "List")
    private List<Item> items;
    @JsonIgnore
    private List<String> errors = new ArrayList<>();

    public Invoice() {
    }

    public Invoice(Integer number, LocalDate date, LocalDate dueDate, Double amount, Double tax, Double total, List<Item> items) {
        this.number = number;
        this.date = date;
        this.dueDate = dueDate;
        this.amount = amount;
        this.tax = tax;
        this.total = total;
        this.items = items;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public String getErrors() {
        StringBuilder sb = new StringBuilder();
        this.errors.forEach(e -> sb.append(e).append("\n"));
        return sb.toString();
    }

    public boolean hasError() {
        return errors.size() > 0;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}