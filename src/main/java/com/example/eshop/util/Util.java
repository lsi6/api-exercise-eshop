package com.example.eshop.util;

import com.example.eshop.exception.BadRequestException;

public class Util
{
    /**
     * Method to validate the input string ID can be converted into an int.
     *
     * @param id - The ID string to check
     * @return - The converted int ID
     * @throws BadRequestException - Thrown if the input ID string cannot be converted into an int
     */
    public static int validateId(String id) throws BadRequestException
    {
        try
        {
            return Integer.parseInt(id);
        }
        catch(NumberFormatException e)
        {
            throw new BadRequestException(String.format("Invalid ID: Could not convert ID '%s' into an int.", id));
        }
    }
}
