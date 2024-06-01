package com.example.eshop.db.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "product_label", schema = "eshop")
public class ProductLabel
{
    @Id
    private Integer id;

    @Column(name = "label_text")
    private String labelText;

    @ManyToMany(mappedBy = "labels")
    private Set<Product> products;

    /**
     * Method to get the ID of the product label
     *
     * @return - The ID of the product label
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * Method to set the ID for the product label
     *
     * @param id - The ID to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * Method to get the label text
     *
     * @return - The label text
     */
    public String getLabelText()
    {
        return this.labelText;
    }

    /**
     * Method to set the label text
     *
     * @param labelText - The label text to set
     */
    public void setLabelText(String labelText)
    {
        this.labelText = labelText;
    }

    /**
     * Method to get the products using this label
     *
     * @return - The list of products using this label
     */
    public Set<Product> getProducts()
    {
        return this.products;
    }
}
