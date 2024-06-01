package com.example.eshop.exception;

/**
 * Exception to be thrown when a resource could not be found.
 */
public class NotFoundException extends Exception
{
    /**
     * The type (or name) of the resource not found.
     */
    private final String type;

    /**
     * The ID of the resource not found.
     */
    private final String id;

    /**
     * Constructor
     *
     * @param type - The type of resource not found
     * @param id - The ID of the resource not found
     */
    public NotFoundException(final String type, final String id)
    {
        super();
        this.type = type;
        this.id = id;
    }

    /**
     * Method to get the type.
     *
     * @return - The type of the resource not found
     */
    public String getType()
    {
        return this.type;
    }

    /**
     * Method to get the ID.
     *
     * @return - The ID of the resource not found
     */
    public String getId()
    {
        return this.id;
    }
}
