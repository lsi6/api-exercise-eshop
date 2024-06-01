package com.example.eshop.db.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "product", schema = "eshop")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "added_at")
    private Date addedAt;

    @ManyToMany
    @JoinTable(
        name = "product_label_link",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "label_link_id"))
    private Set<ProductLabel> labels;

    /**
     * Method to get the ID.
     *
     * @return - The ID
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * Method to get the product name.
     *
     * @return - The product name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Method to set the product name.
     *
     * @param name - The product name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Method to get the product price.
     *
     * @return - The product price
     */
    public Double getPrice()
    {
        return this.price;
    }

    /**
     * Method to set the product price.
     *
     * @param price - The price to set
     */
    public void setPrice(Double price)
    {
        this.price = price;
    }

    /**
     * Method to get the 'added at' value for the product.
     *
     * @return - The date the product was added to the database
     */
    public Date getAddedAt()
    {
        return this.addedAt;
    }

    /**
     * Method to set the date the product was added to the database.
     *
     * @param addedAt - The 'addedAt' value to set
     */
    public void setAddedAt(Date addedAt)
    {
        this.addedAt = addedAt;
    }

    /**
     * Method to get the product labels
     *
     * @return - The set of product labels
     */
    public Set<ProductLabel> getLabels()
    {
        return this.labels;
    }

    /**
     * Method to set the product labels
     *
     * @param labels - The labels to set for this product
     */
    public void setLabels(Set<ProductLabel> labels)
    {
        this.labels = labels;
    }
}
