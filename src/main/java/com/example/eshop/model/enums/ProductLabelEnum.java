package com.example.eshop.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum representing the possible values for product labels.
 */
public enum ProductLabelEnum
{
    DRINK("drink"),
    FOOD("food"),
    CLOTHES("clothes"),
    LIMITED("limited");

    /**
     * Map for looking up ProductLabels by their string labels
     */
    private static final Map<String, ProductLabelEnum> BY_LABEL = new HashMap<>();

    static
    {
        // Initialise the Map
        for(ProductLabelEnum productLabelEnum : values())
        {
            BY_LABEL.put(productLabelEnum.label, productLabelEnum);
        }
    }

    /**
     * The label string
     */
    private final String label;

    /**
     * Enum constructor
     *
     * @param label - The label to set
     */
    ProductLabelEnum(final String label)
    {
        this.label = label;
    }

    /**
     * Method to get a ProductLabel enum value by its String label value
     *
     * @param label - The label string to look up
     * @return - The found ProductLabel or null if not found.
     */
    public static ProductLabelEnum getByLabel(String label)
    {
        return BY_LABEL.get(label);
    }
}
