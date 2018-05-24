package com.gnaderi.interview.phoneshop.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PRODUCT")
public class Product {
    private Integer id;
    private String name;
    private String desc;
    private Integer quantity;
    private Double price;
    private ProductType ProductType;

    public Product() {
    }

    public Product(String name, String desc, Integer quantity, Double price, ProductType productType) {
        this.name = name;
        this.desc = desc;
        this.quantity = quantity;
        this.price = price;
        ProductType = productType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESC")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "QUANTITY")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "PRICE")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(desc, product.desc) &&
                Objects.equals(quantity, product.quantity) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, desc, quantity, price);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_TYPE_ID", referencedColumnName = "ID", nullable = false)
    public ProductType getProductType() {
        return ProductType;
    }

    public void setProductType(ProductType productType) {
        this.ProductType = productType;
    }
}
