package com.gnaderi.interview.phoneshop.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PRODUCT_TYPE")
public class ProductType {
    private Integer id;
    private String name;
    private String desc;

    public ProductType() {
    }

    public ProductType(String name, String desc) {
        this.name = name;
        this.desc = desc;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType that = (ProductType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, desc);
    }
}
