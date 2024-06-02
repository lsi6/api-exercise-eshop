package com.example.eshop.db.repos;

import com.example.eshop.db.entities.ProductLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for the product label table
 */
@Repository
public interface LabelRepo extends JpaRepository<ProductLabel, Integer>
{
    @Query("SELECT l from ProductLabel l WHERE l.labelText=?1")
    ProductLabel findLabelByText(String labelText);
}
