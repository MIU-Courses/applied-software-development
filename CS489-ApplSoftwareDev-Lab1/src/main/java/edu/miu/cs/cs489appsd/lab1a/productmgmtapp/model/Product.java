package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonPropertyOrder({"productId", "name", "dateSupplied", "quantityInStock", "unitPrice"})
public class Product {
    @JacksonXmlProperty(isAttribute = true)
    private String productId;
    @JacksonXmlProperty(isAttribute = true)
    private String name;
    @JacksonXmlProperty(isAttribute = true)
    private LocalDate dateSupplied;
    @JacksonXmlProperty(isAttribute = true)
    private Integer quantityInStock;
    @JacksonXmlProperty(isAttribute = true)
    private BigDecimal unitPrice;

    public Product() {
    }

    public Product(String productId, String name, LocalDate dateSupplied, Integer quantityInStock) {
        this.productId = productId;
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
    }

    public Product(String productId, String name, LocalDate dateSupplied, Integer quantityInStock, BigDecimal unitPrice) {
        this.productId = productId;
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateSupplied() {
        return dateSupplied;
    }

    public void setDateSupplied(LocalDate dateSupplied) {
        this.dateSupplied = dateSupplied;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
